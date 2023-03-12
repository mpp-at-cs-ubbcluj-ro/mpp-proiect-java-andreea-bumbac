package org.example.domain;

import java.util.Objects;

public class Echipa extends Entity<Long> {


    public String numeEchipa;

    public Echipa(Long id, String numeEchipa) {
        setId(id);
        this.numeEchipa = numeEchipa;
    }

    public String getNumeEchipa() {
        return numeEchipa;
    }

    public void setNumeEchipa(String numeEchipa) {
        this.numeEchipa = numeEchipa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Echipa echipe = (Echipa) o;
        return Objects.equals(numeEchipa, echipe.numeEchipa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeEchipa);
    }

}
