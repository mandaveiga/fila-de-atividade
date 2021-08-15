package me.mandaveiga.mult.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    Optional<T> save(T body);

    List<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> update(T body);

    Optional<Boolean> deleteById(Long id);

    Optional<Boolean> deleteAll();
}
