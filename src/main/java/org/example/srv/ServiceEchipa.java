package org.example.srv;

import org.example.domain.Echipa;
import org.example.repo.IEchipaRepo;
import org.example.valid.EchipaValidator;
import org.example.valid.IValidator;

import java.util.Collection;
import java.util.Optional;

public class ServiceEchipa implements IEchipaService {
    private final IEchipaRepo teamRepository;
    private final IValidator<Echipa> validator;

    public ServiceEchipa(final IEchipaRepo teamRepository) {
        this.teamRepository = teamRepository;
        validator = new EchipaValidator();
    }

    @Override
    public Optional<Echipa> findById(Long id) {
        return teamRepository.get(id);
    }

    @Override
    public Collection<Echipa> findAll() {
        return teamRepository.read();
    }

    @Override
    public void save(Echipa newEntity) {
        validator.validateEntity(newEntity);
        teamRepository.add(newEntity);
    }

    @Override
    public void delete(Long id) {
        teamRepository.delete(id);
    }

    @Override
    public void update(Echipa updatedEntity) {
        teamRepository.update(updatedEntity);
    }

    @Override
    public Long getTeamByName(String teamName) {
        return teamRepository.getEchipaDupaNume(teamName);
    }
}