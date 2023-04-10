package org.example.srv;

import org.example.domain.Cursa;
import org.example.domain.Inscris;

import java.util.Collection;
import java.util.Optional;

public interface ICursaService extends IService<Long, Cursa> {
    Optional<Cursa> getRaceByName(String name);

    Collection<Inscris> getEntriesByRace(Long raceID);

    void deleteByIds(Long participantID, Long raceID);

    Collection<Cursa> getRacesByEngineCapacity(Integer engineCapacity);

    //Collection<RaceDTO> getRacesWithParticipantCount();

    void saveRaceEntry(Inscris newEntity);

}
