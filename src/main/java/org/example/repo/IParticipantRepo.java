package org.example.repo;

import org.example.domain.Participant;

import java.util.Collection;
import java.util.Optional;

public interface IParticipantRepo extends IRepository<Long, Participant> {
    Collection<Participant> getParticipantiDupaEchipa(final Long teamID);

    Optional<Participant> getParticipant(Participant participant);


}
