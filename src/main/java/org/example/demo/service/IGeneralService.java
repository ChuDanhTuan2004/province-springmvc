package org.example.demo.service;

import org.example.demo.model.Province;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);

    Iterable<Province> findByName(String name);
}

