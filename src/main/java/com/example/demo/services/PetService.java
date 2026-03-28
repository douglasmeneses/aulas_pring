package com.example.demo.services;

import com.example.demo.models.entities.Pet;
import com.example.demo.repositories.PetRepository;
import com.example.demo.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final UsuarioService usuarioService;

    public List<Pet> listarPets(Long donoId) {
        Optional<Long> donoIdOptional = Optional.ofNullable(donoId);
        if (donoIdOptional.isEmpty()) {
            return petRepository.findAll();
        } else {
            usuarioService.obtemUsuarioPorId(donoId);
            return petRepository.findByDonoId(donoId);
        }
    }
}
