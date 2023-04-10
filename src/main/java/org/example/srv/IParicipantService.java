package org.example.srv;

import org.example.domain.Participant;

import java.util.Collection;
import java.util.Optional;

public interface IParicipantService extends IService<Long, Participant> {

    Collection<Participant> getParticipantsByTeam(final Long teamID);

    Optional<Participant> getParticipantByData(final Participant participant);
}


