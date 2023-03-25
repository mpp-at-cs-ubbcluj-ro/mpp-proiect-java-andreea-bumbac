package org.example.domain;
import org.example.domain.Participant;
import org.example.domain.Cursa;
import java.util.Objects;

public class Inscris extends Entity<Long>{


    private final Participant participant;
    private final Cursa cursa;

    public Inscris(Long id,Participant participant, Cursa cursa) {
        setId(id);
        this.participant = participant;
        this.cursa = cursa;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Cursa getCursa() {
        return cursa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscris inscris = (Inscris) o;
        return getParticipant().equals(inscris.getParticipant()) && getCursa().equals(inscris.getCursa());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParticipant(), getCursa());
    }
}
