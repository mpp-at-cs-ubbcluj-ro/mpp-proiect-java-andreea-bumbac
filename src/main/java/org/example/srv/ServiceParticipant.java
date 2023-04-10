package org.example.srv;

import org.example.domain.Echipa;
import org.example.domain.Participant;
import org.example.repo.IParticipantRepo;
import org.example.repo.IRepository;
import org.example.valid.IValidator;
import org.example.valid.ParticipantValidator;

import java.util.Collection;
import java.util.Optional;

public class ServiceParticipant implements IParicipantService {
    private final IParticipantRepo participantRepository;
    private final IValidator<Participant> validator;

    public ServiceParticipant(final IParticipantRepo participantRepository) {
        this.participantRepository = participantRepository;
        validator = new ParticipantValidator();
    }

    @Override
    public Collection<Participant> getParticipantsByTeam(final Long teamID) {
        return participantRepository.getParticipantiDupaEchipa(teamID);
    }


    @Override
    public Optional<Participant> getParticipantByData(Participant participant) {
        return participantRepository.getParticipant(participant);
    }

    @Override
    public Optional<Participant> findById(final Long id) {
        return participantRepository.get(id);
    }

    @Override
    public Collection<Participant> findAll() {
        return participantRepository.read();
    }

    @Override
    public void save(final Participant newEntity) {
        validator.validateEntity(newEntity);
        participantRepository.add(newEntity);
    }

    @Override
    public void delete(final Long id) {
        participantRepository.delete(id);
    }

    @Override
    public void update(final Participant updatedEntity) {
        validator.validateEntity(updatedEntity);
        participantRepository.update(updatedEntity);
    }
}





/*
{

    private IRepository<Long, Participant> participantDB;

    private IRepository<Long, Echipa> echipaDB;


    public ServiceParticipant(IRepository<Long, Participant> participantDB, IRepository<Long, Echipa> echipaDB) {
        this.participantDB = participantDB;
        this.echipaDB = echipaDB;
    }

    public void addParicipant(String name, String prenume, Long echipa, int motor) {
        Participant participant = new Participant( name, prenume,  motor, echipa);
        participantDB.add(participant);
    }
}*/
