package it.work2gether.quickAnswer.exception;

public class PreventivoNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "Preventivo non trovato";
    }
}
