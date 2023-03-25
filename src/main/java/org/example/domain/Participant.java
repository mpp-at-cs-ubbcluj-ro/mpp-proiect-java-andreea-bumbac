package org.example.domain;

import java.util.Objects;

public class Participant extends Entity<Long> {
    public String nume;

    public String prenume;

    public int capacitateMotor;

    public Long idEchipa;

    public Participant(Long id, String nume, String prenume, int capacitateMotor, Long echipa) {
        setId(id);
        this.nume = nume;
        this.prenume = prenume;
        this.capacitateMotor = capacitateMotor;
        this.idEchipa = echipa;
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

    public Long getEchipa() {
        return idEchipa;
    }

    public void setEchipa(Long echipa) {
        this.idEchipa = echipa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return capacitateMotor == that.capacitateMotor && Objects.equals(nume, that.nume) && Objects.equals(prenume, that.prenume) && Objects.equals(idEchipa, that.idEchipa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, capacitateMotor, idEchipa);
    }


}
