package com.example.demo.services;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario criar(Usuario usuario) {
        normalizarCampos(usuario);

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ja existe usuario com este email");
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarPorPais(String pais) {
        return usuarioRepository.findByPais(pais);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return buscarEntidadePorId(id);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario dadosAtualizados) {
        Usuario usuario = buscarEntidadePorId(id);
        normalizarCampos(dadosAtualizados);

        if (usuarioRepository.existsByEmailAndIdNot(dadosAtualizados.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ja existe usuario com este email");
        }

        usuario.setNome(dadosAtualizados.getNome());
        usuario.setEmail(dadosAtualizados.getEmail());

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void remover(Long id) {
        Usuario usuario = buscarEntidadePorId(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario buscarEntidadePorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
    }

    private void normalizarCampos(Usuario usuario) {
        if (usuario.getNome() != null) {
            usuario.setNome(usuario.getNome().trim());
        }
        if (usuario.getEmail() != null) {
            usuario.setEmail(usuario.getEmail().trim().toLowerCase());
        }
    }
}

