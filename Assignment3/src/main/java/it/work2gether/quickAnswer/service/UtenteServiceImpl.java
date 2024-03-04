package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.dto.ProfiloPubblicoDto;
import it.work2gether.quickAnswer.entity.Utente;
import it.work2gether.quickAnswer.exception.SaldoNotEnoughException;
import it.work2gether.quickAnswer.exception.UtenteNotFoundException;
import it.work2gether.quickAnswer.exception.UtenteRegistratoException;
import it.work2gether.quickAnswer.mapper.ProfiloPubblicoMapper;
import it.work2gether.quickAnswer.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    @Override
    public Utente findById(Integer id) throws UtenteNotFoundException {
        Optional<Utente> utente = utenteRepository.findById(id);

        if (utente.isEmpty()) {
            throw new UtenteNotFoundException();
        }

        return utente.get();
    }

    @Override
    public void saveUtente(Utente utente) throws UtenteRegistratoException {
        if (findByEmail(utente.getEmail()) != null) {
            throw new UtenteRegistratoException();
        }
        utenteRepository.save(utente);
    }

    @Override
    public void updateUtente(Utente utente) {
        //utenteRepository.delete(utenteOld);
        utenteRepository.save(utente);
    }

    @Override
    public void changeSaldoById(Integer idUtente, Double amount) throws UtenteNotFoundException, SaldoNotEnoughException {
        Utente utente = findById(idUtente);

        if (utente.getSaldo() + amount < 0) {
            throw new SaldoNotEnoughException();
        }
        utente.setSaldo(utente.getSaldo() + amount);
        utenteRepository.save(utente);
    }

    @Override
    public void changeSaldo(Utente utente, Double amount) throws SaldoNotEnoughException {

        if (utente.getSaldo() + amount < 0) {
            throw new SaldoNotEnoughException();
        }
        utente.setSaldo(utente.getSaldo() + amount);
        utenteRepository.save(utente);
    }


    @Override
    public ProfiloPubblicoDto publicFindByNickname(String nickname) throws UtenteNotFoundException {
        Utente utente = utenteRepository.findByNickname(nickname);
        if (utente == null) {
            throw new UtenteNotFoundException();
        }
        ProfiloPubblicoMapper profiloPubblicoMapper = new ProfiloPubblicoMapper();

        return profiloPubblicoMapper.toDto(utente);
    }

    @Override
    public ProfiloPubblicoDto publicFindByEmail(String email) throws UtenteNotFoundException {
        Utente utente = utenteRepository.findByEmail(email);
        if (utente == null) {
            throw new UtenteNotFoundException();
        }
        ProfiloPubblicoMapper profiloPubblicoMapper = new ProfiloPubblicoMapper();

        return profiloPubblicoMapper.toDto(utente);
    }

    @Override
    public void updateRatingUtente(Integer idUtente, Double rating) {
        utenteRepository.updateRatingUtente(idUtente, rating);
    }
}
