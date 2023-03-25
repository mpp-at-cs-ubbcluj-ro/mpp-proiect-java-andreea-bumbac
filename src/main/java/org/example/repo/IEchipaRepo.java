package org.example.repo;

import org.example.domain.Echipa;

import java.util.Optional;

public interface IEchipaRepo extends IRepository<Long, Echipa> {
    Optional<Echipa> getEchipaDupaNume(String teamName);
}

