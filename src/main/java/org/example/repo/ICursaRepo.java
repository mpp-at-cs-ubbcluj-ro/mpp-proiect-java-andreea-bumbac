package org.example.repo;

import org.example.domain.Cursa;

import java.util.Collection;
import java.util.Optional;

public interface ICursaRepo extends IRepository<Long, Cursa> {
    Collection<Cursa> getCursaDupaMotor(Integer engineCapacity);

    Optional<Cursa> getCursaDupaNume(String name);
}
