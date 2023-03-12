package org.example.domain;

import java.util.Objects;

public class Cursa extends Entity<Long> {
    public String numeCursa;

    public int nrParticipanti;

    public int capacitateCilindrica;

    public Cursa(Long id, String numeCursa, int nrParticipanti, int capacitateCilindrica) {
        setId(id);
        this.numeCursa = numeCursa;
        this.nrParticipanti = nrParticipanti;
        this.capacitateCilindrica = capacitateCilindrica;
    }

    public String getNumeCursa() {
        return numeCursa;
    }

    public void setNumeCursa(String numeCursa) {
        this.numeCursa = numeCursa;
    }

    public int getNrParticipanti() {
        return nrParticipanti;
    }

    public void setNrParticipanti(int nrParticipanti) {
        this.nrParticipanti = nrParticipanti;
    }

    public int getCapacitateCilindrica() {
        return capacitateCilindrica;
    }

    public void setCapacitateCilindrica(int capacitateCilindrica) {
        this.capacitateCilindrica = capacitateCilindrica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cursa curse = (Cursa) o;
        return nrParticipanti == curse.nrParticipanti && capacitateCilindrica == curse.capacitateCilindrica && Objects.equals(numeCursa, curse.numeCursa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeCursa, nrParticipanti, capacitateCilindrica);
    }
}
