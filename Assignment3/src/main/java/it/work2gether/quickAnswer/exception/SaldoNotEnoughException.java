package it.work2gether.quickAnswer.exception;

public class SaldoNotEnoughException extends Exception {
    @Override
    public String getMessage() {
        return "Saldo non sufficiente";
    }
}
