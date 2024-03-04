package it.rzl.app;

public class StudentNotFoundException extends RuntimeException {

    StudentNotFoundException(Integer matricola) {
        super("Impossibile trovare studente con questa matricola: " + matricola);
    }

}