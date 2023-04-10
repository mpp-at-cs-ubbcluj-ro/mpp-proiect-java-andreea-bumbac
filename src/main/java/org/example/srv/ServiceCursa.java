package org.example.srv;

import org.example.domain.Cursa;
import org.example.domain.CursaDTO;
import org.example.domain.Inscris;
import org.example.repo.ICursaRepo;
import org.example.repo.IInscrisRepo;
import org.example.valid.CursaValidator;
import org.example.valid.IValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ServiceCursa implements ICursaService {
    private final ICursaRepo raceRepository;
    private final IInscrisRepo raceEntryRepository;
    private final IValidator<Cursa> validator;

    public ServiceCursa(final ICursaRepo raceRepository, final IInscrisRepo raceEntryRepository) {
        this.raceEntryRepository = raceEntryRepository;
        this.raceRepository = raceRepository;
        validator = new CursaValidator();
    }
/*
    @Override
    public Collection<RaceDTO> getRacesWithParticipantCount() {
        Collection<RaceDTO> result = new ArrayList<>();
        Collection<Cursa> races = raceRepository.read();
        for (Cursa race : races) {
            result.add(new RaceDTO(race.getNumeCursa(), race.getCapacitateCilindrica(),
                    raceEntryRepository.getInscrisiDupaCursa(race.getID()).size()));
        }
        return result;
    }*/

    @Override
    public void saveRaceEntry(Inscris newEntity) {
        raceEntryRepository.add(newEntity);
    }

    @Override
    public Optional<Cursa> getRaceByName(String name) {
        return raceRepository.getCursaDupaNume(name);
    }

    @Override
    public Collection<Inscris> getEntriesByRace(Long raceID) {
        return raceEntryRepository.getInscrisiDupaCursa(raceID);
    }

    @Override
    public void deleteByIds(Long participantID, Long raceID) {
        raceEntryRepository.deleteByIds(participantID, raceID);
    }

    @Override
    public Collection<Cursa> getRacesByEngineCapacity(Integer engineCapacity) {
        return raceRepository.getCursaDupaMotor(engineCapacity);
    }

    @Override
    public Optional<Cursa> findById(Long id) {
        return raceRepository.get(id);
    }

    @Override
    public Collection<Cursa> findAll() {
        return raceRepository.read();
    }

    @Override
    public void save(Cursa newEntity) {
        validator.validateEntity(newEntity);
        raceRepository.add(newEntity);
    }

    @Override
    public void delete(Long id) {
        raceRepository.delete(id);
    }

    @Override
    public void update(Cursa updatedEntity) {
        validator.validateEntity(updatedEntity);
        raceRepository.update(updatedEntity);
    }

    public Collection<CursaDTO> getRacesWithParticipants() {
        Collection<CursaDTO> result = new ArrayList<>();
        Collection<Cursa> curse = raceRepository.read();
        for (Cursa cursa : curse) {
            result.add(new CursaDTO(cursa.getNumeCursa(), cursa.getCapacitateCilindrica(),
                    raceEntryRepository.getInscrisiDupaCursa(cursa.getId()).size()));
        }
        return result;
    }
}
