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
}
