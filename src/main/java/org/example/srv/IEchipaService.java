package org.example.srv;

import org.example.domain.Echipa;

import java.util.Optional;

public interface IEchipaService extends IService<Long, Echipa> {
    Long getTeamByName(String teamName);
}
