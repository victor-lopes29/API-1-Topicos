package com.farmlogistic.farmlogistic.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.farmlogistic.farmlogistic.model.Person;
import com.farmlogistic.farmlogistic.repository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Page<Person> findAll(Pageable pageable) {
        return this.personRepository.findAll(pageable);
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    public boolean existsByIdAndEmail(UUID id, String email) {
        return personRepository.existsByIdAndEmail(id, email);
    }

    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    public boolean existsByEmailAndPassword(String email, String password) {
        return personRepository.existsByEmailAndPassword(email, password);
    }

    public Optional<Person> findById(UUID id) {
        return personRepository.findById(id);
    }

    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Transactional
    public void delete(Person person) {
        this.personRepository.delete(person);
    }

    public Optional<Person> findByEmailAndPassword(String email, String password) {
        return personRepository.findByEmailAndPassword(email, password);
    }

    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }
}
