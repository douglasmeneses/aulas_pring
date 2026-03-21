package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String pais;

    public Usuario(String nome, String email, String senha, String pais){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pais = pais;
    }
}
