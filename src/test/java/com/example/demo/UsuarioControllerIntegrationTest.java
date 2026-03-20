package com.example.demo;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UsuarioControllerIntegrationTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    void deveCriarBuscarAtualizarEDeletarUsuario() throws Exception {
        String email = "user-" + UUID.randomUUID() + "@demo.com";

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Maria Silva");
        novoUsuario.setEmail(email);

        Usuario criado = usuarioService.criar(novoUsuario);
        Usuario encontrado = usuarioService.buscarPorId(criado.getId());

        Usuario dadosAtualizacao = new Usuario();
        dadosAtualizacao.setNome("Maria Souza");
        dadosAtualizacao.setEmail(email);
        Usuario atualizado = usuarioService.atualizar(criado.getId(), dadosAtualizacao);

        assertEquals("Maria Silva", encontrado.getNome());
        assertEquals("Maria Souza", atualizado.getNome());

        usuarioService.remover(criado.getId());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> usuarioService.buscarPorId(criado.getId()));
        assertEquals(404, ex.getStatusCode().value());
    }

    @Test
    void deveRetornarConflitoAoCriarEmailDuplicado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Joao");
        usuario.setEmail("joao@demo.com");
        usuarioRepository.save(usuario);

        Usuario duplicado = new Usuario();
        duplicado.setNome("Joao 2");
        duplicado.setEmail("joao@demo.com");

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> usuarioService.criar(duplicado));
        assertEquals(409, ex.getStatusCode().value());
    }
}


