package com.example.demo.services;

import com.example.demo.models.dto.UsuarioDTO;
import com.example.demo.models.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario criaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getSenha(),
                usuarioDTO.getPais());
        usuarioRepository.save(usuario); //INSERT INTO usuarios...
        log.info("Usuário de id {} criado com sucesso", usuario.getId());
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtemUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            String mensagem = "Usuário de id " + id + " não encontrado";
            log.error(mensagem);
            throw new RuntimeException(mensagem);
        } else {
            log.info("Usuário de id {} encontrado", id);
            return usuarioOpt.get();
        }
    }

    public void removeUsuarioPorId(Long id) {
        Usuario usuario = obtemUsuarioPorId(id);
        usuarioRepository.delete(usuario);
        log.info("Usuário de id {} removido com sucesso", id);
    }

    public Usuario atualizaUsuario(Long id, UsuarioDTO dadosParaAtualizar) {
        Usuario usuarioExistente = obtemUsuarioPorId(id);
        if (dadosParaAtualizar.getNome() != null && !dadosParaAtualizar.getNome().equals(usuarioExistente.getNome())) {
            usuarioExistente.setNome(dadosParaAtualizar.getNome());
        }
        if (dadosParaAtualizar.getEmail() != null && !dadosParaAtualizar.getEmail().equals(usuarioExistente.getEmail())) {
            usuarioExistente.setEmail(dadosParaAtualizar.getEmail());
        }
        if (dadosParaAtualizar.getSenha() != null && !dadosParaAtualizar.getSenha().equals(usuarioExistente.getSenha())) {
            usuarioExistente.setSenha(dadosParaAtualizar.getSenha());
        }
        if (dadosParaAtualizar.getPais() != null && !dadosParaAtualizar.getPais().equals(usuarioExistente.getPais())) {
            usuarioExistente.setPais(dadosParaAtualizar.getPais());
        }
        usuarioRepository.save(usuarioExistente);
        log.info("Usuário de id {} atualizado com sucesso", id);
        return usuarioExistente;
    }
}
