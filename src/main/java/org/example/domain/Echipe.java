package org.example.domain;

import java.util.Objects;

public class Echipe extends Entity<Long> {


    public String nume_echipa;


    public String getNume_echipa() {
        return nume_echipa;
    }

    public void setNume_echipa(String nume_echipa) {
        this.nume_echipa = nume_echipa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Echipe echipe = (Echipe) o;
        return Objects.equals(nume_echipa, echipe.nume_echipa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume_echipa);
    }

}
