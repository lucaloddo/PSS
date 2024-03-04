package it.work2gether.quickAnswer.exception;

public class RispostaNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Risposta non trovata";
    }
}
