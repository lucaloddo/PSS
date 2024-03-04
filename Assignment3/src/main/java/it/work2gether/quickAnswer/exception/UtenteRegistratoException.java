package it.work2gether.quickAnswer.exception;

public class UtenteRegistratoException extends Exception {
    @Override
    public String getMessage() {
        return "Utente registrato";
    }
}
