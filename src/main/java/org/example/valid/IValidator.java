package org.example.valid;

import org.example.domain.Entity;

public interface IValidator<T extends Entity<?>> {
    void validateEntity(T entity);
}
