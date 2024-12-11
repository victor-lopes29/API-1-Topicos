package com.farmlogistic.farmlogistic.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmlogistic.farmlogistic.model.Animal;
import com.farmlogistic.farmlogistic.repository.AnimalRepository;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        System.out.println(animal);
        Animal savedAnimal = animalRepository.save(animal);
        return ResponseEntity.status(201).body(savedAnimal);
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable UUID id) {
        return animalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable UUID id, @RequestBody Animal updatedAnimal) {

        return animalRepository.findById(id).map(animal -> {
            animal.setPeso(updatedAnimal.getPeso());
            animal.setRaca(updatedAnimal.getRaca());
            animal.setTipoAlimentacao(updatedAnimal.getTipoAlimentacao());
            animal.setIdade(updatedAnimal.getIdade());
            animal.setEstadoSaude(updatedAnimal.getEstadoSaude());
            Animal savedAnimal = animalRepository.save(animal);
            return ResponseEntity.ok(savedAnimal);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable String id) {
        var animal = animalRepository.findById(UUID.fromString(id));

        if (animal.isPresent()) {
            animalRepository.delete(animal.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
