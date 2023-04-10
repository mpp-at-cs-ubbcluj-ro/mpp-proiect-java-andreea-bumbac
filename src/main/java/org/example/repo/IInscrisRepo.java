package org.example.repo;

import org.example.domain.CursaDTO;
import org.example.domain.Inscris;

import java.util.Collection;

public interface IInscrisRepo extends IRepository<Long, Inscris> {
    Collection<Inscris> getInscrisiDupaCursa(Long raceID);


    void deleteByIds(Long participantID, Long raceID);
}
