package org.example.domain;

import java.util.Objects;

public class Participanti extends Entity<Long> {



    public String nume;

    public String prenume;

    public int capacitate_motor;

    public String echipa;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getCapacitate_motor() {
        return capacitate_motor;
    }

    public void setCapacitate_motor(int capacitate_motor) {
        this.capacitate_motor = capacitate_motor;
    }

    public String getEchipa() {
        return echipa;
    }

    public void setEchipa(String echipa) {
        this.echipa = echipa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participanti that = (Participanti) o;
        return capacitate_motor == that.capacitate_motor && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume) && Objects.equals(echipa, that.echipa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, capacitate_motor, echipa);
    }


}
