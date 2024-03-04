package it.work2gether.quickAnswer.exception;

public class DomandaNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Domanda non trovata";
    }
}
