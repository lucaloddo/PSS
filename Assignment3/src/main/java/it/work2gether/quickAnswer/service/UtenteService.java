package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.dto.ProfiloPubblicoDto;
import it.work2gether.quickAnswer.entity.Utente;
import it.work2gether.quickAnswer.exception.SaldoNotEnoughException;
import it.work2gether.quickAnswer.exception.UtenteNotFoundException;
import it.work2gether.quickAnswer.exception.UtenteRegistratoException;

public interface UtenteService {
    Utente findByEmail(String email);

    Utente findById(Integer id) throws UtenteNotFoundException;

    void saveUtente(Utente utente) throws UtenteRegistratoException;

    void updateUtente(Utente utente);

    void changeSaldoById(Integer idUtente, Double amount) throws UtenteNotFoundException, SaldoNotEnoughException;

    void changeSaldo(Utente utente, Double amount) throws SaldoNotEnoughException;

    ProfiloPubblicoDto publicFindByNickname(String nickname) throws UtenteNotFoundException;

    ProfiloPubblicoDto publicFindByEmail(String email) throws UtenteNotFoundException;

    void updateRatingUtente(Integer idUtente, Double rating);
}
