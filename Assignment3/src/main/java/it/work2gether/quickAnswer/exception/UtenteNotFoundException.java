package it.work2gether.quickAnswer.exception;

public class UtenteNotFoundException extends Exception {
    public String getMessage() {
        return "Utente non trovato";
    }
}
