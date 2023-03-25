package org.example.repo;

import org.example.domain.Cursa;

import java.util.Optional;

public interface ICursaRepo extends IRepository<Long, Cursa> {
    Optional<Cursa> getCursaDupaNume(String name);
}
