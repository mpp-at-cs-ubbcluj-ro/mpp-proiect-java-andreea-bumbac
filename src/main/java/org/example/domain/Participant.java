package org.example.domain;

import java.util.Objects;

public class Participant extends Entity<Long> {



    public String nume;

    public String prenume;

    public int capacitateMotor;

    public String echipa;

    public Participant(Long id, String nume, String prenume, int capacitateMotor, String echipa) {
        setId(id);
        this.nume = nume;
        this.prenume = prenume;
        this.capacitateMotor = capacitateMotor;
        this.echipa = echipa;
    }

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

    public int getCapacitateMotor() {
        return capacitateMotor;
    }

    public void setCapacitateMotor(int capacitateMotor) {
        this.capacitateMotor = capacitateMotor;
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
        Participant that = (Participant) o;
        return capacitateMotor == that.capacitateMotor && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume) && Objects.equals(echipa, that.echipa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, capacitateMotor, echipa);
    }


}
