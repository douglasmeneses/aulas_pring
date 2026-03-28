package com.example.demo.controllers;

import com.example.demo.models.entities.Pet;
import com.example.demo.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping
    public ResponseEntity<?> listarPets(@RequestParam(required= false) Long donoId) {
        List<Pet> pets = petService.listarPets(donoId);
        return ResponseEntity.status(200).body(pets);
    }

}
