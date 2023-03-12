package org.example.repo;

import org.example.domain.Entity;

import java.util.Optional;

public interface Repo0 <ID, E extends Entity<ID>>{

    Optional<E> findOne(ID id);

    Iterable<E> findAll();

    void save(E entity);

    void delete(ID id);

    void update(E entity);
}
