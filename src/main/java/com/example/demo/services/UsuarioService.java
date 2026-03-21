package com.example.demo.services;

import com.example.demo.models.dto.UsuarioDTO;
import com.example.demo.models.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario criaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getSenha(),
                usuarioDTO.getPais());
        usuarioRepository.save(usuario); //INSERT INTO usuarios...
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
