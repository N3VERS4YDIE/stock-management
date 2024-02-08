package com.stock.management.web.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class WebController<T, R extends CrudRepository<T, Integer>> {

    @Autowired
    private R repository;

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<>((List<T>) repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<T> getById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @PostMapping
    public T save(@RequestBody T entity) {
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
