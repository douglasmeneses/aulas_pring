package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private String pais;
}
