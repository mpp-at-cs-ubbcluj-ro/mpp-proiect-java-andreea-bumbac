package org.example.domain;

import java.util.Objects;

public class Curse extends Entity<Long> {



    public String nume_cursa;

    public int nr_participanti;

    public int capacitate_cilindrica;

    public String getNume_cursa() {
        return nume_cursa;
    }

    public void setNume_cursa(String nume_cursa) {
        this.nume_cursa = nume_cursa;
    }

    public int getNr_participanti() {
        return nr_participanti;
    }

    public void setNr_participanti(int nr_participanti) {
        this.nr_participanti = nr_participanti;
    }

    public int getCapacitate_cilindrica() {
        return capacitate_cilindrica;
    }

    public void setCapacitate_cilindrica(int capacitate_cilindrica) {
        this.capacitate_cilindrica = capacitate_cilindrica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curse curse = (Curse) o;
        return nr_participanti == curse.nr_participanti && capacitate_cilindrica == curse.capacitate_cilindrica && Objects.equals(nume_cursa, curse.nume_cursa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume_cursa, nr_participanti, capacitate_cilindrica);
    }
}
