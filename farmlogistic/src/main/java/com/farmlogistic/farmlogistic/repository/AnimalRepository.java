package com.farmlogistic.farmlogistic.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmlogistic.farmlogistic.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
        public List<Animal> findByRaca(String raca);
}
