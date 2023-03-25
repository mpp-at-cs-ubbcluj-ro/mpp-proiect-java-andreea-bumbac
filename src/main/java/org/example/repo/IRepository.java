package org.example.repo;

import org.example.domain.Entity;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<ID, T extends Entity<ID>> {
    Optional<T> get(final ID id);

    Collection<T> read();

    void add(final T newEntity);

    void delete(final ID id);

    void update(final T updatedEntity);
}