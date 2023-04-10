package org.example.srv;

import org.example.domain.Entity;

import java.util.Collection;
import java.util.Optional;

public interface IService<ID, T extends Entity<ID>> {

    Optional<T> findById(final ID id);

    Collection<T> findAll();

    void save(final T newEntity);

    void delete(final ID id);

    void update(final T updatedEntity);
}


