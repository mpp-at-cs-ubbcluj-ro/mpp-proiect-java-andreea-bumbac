package org.example.domain;

public class CursaDTO {
    private final String name;
    private final Integer engineCapacity;
    private final Integer participants;

    public CursaDTO(String name, Integer engineCapacity, Integer participants) {
        this.name = name;
        this.engineCapacity = engineCapacity;
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public Integer getParticipants() {
        return participants;
    }
}
