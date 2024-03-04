package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.entity.Domanda;
import it.work2gether.quickAnswer.exception.DomandaNotFoundException;

import java.util.List;

public interface DomandaService {
    void saveDomanda(Domanda domanda);

    List<Domanda> getElencoDomandeDisponibiliButAsker(Integer idAsker);

    List<Domanda> getElencoDomandeByAsker(Integer idAsker);

    Domanda findById(Integer id) throws DomandaNotFoundException;
}
