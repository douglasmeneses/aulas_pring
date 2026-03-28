package com.example.demo.controllers;

import com.example.demo.models.dto.UsuarioDTO;
import com.example.demo.models.entities.Usuario;
import com.example.demo.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> criaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuarioCriado = usuarioService.criaUsuario(usuarioDTO);
        return ResponseEntity.status(201).body(usuarioCriado);
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(200).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontraUsuario(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.obtemUsuarioPorId(id);
            return ResponseEntity.status(200).body(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaUsuario(@PathVariable Long id) {
        try {
            usuarioService.removeUsuarioPorId(id);
            return ResponseEntity.status(204).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaUsuario(@PathVariable Long id,
                                             @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizaUsuario(id, usuarioDTO);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
