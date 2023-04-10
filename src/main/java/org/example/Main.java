package org.example;

import org.example.domain.Participant;
import org.example.repo.ParticipantRepoDB;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        final Properties properties = new Properties();
        try {
            properties.load(new FileReader("bd.config"));
            ParticipantRepoDB participantDBRepository = new ParticipantRepoDB(properties);
            participantDBRepository.add(new Participant( "Pop", "Alin", 300, 3l));
            Collection<Participant> participants = participantDBRepository.read();
            for (Participant p : participants) {
                System.out.println(p);
            }
        } catch (IOException ioException) {
            System.out.println("Cannot find bd.properties " + ioException.getMessage());
        }
    }
}