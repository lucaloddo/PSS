package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.entity.Domanda;
import it.work2gether.quickAnswer.exception.DomandaNotFoundException;
import it.work2gether.quickAnswer.repository.DomandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomandaServiceImpl implements DomandaService {
    @Autowired
    private DomandaRepository domandaRepository;

    @Override
    public void saveDomanda(Domanda domanda) {
        domandaRepository.save(domanda);
    }

    @Override
    public List<Domanda> getElencoDomandeDisponibiliButAsker(Integer idAsker) {
        return domandaRepository.getElencoDomandeDisponibiliButAsker(idAsker);
    }

    @Override
    public List<Domanda> getElencoDomandeByAsker(Integer idAsker) {
        return domandaRepository.getElencoDomandeByAsker(idAsker);
    }


    @Override
    public Domanda findById(Integer id) throws DomandaNotFoundException {
        Optional<Domanda> domanda = domandaRepository.findById(id);
        if (domanda.isEmpty()) {
            throw new DomandaNotFoundException();
        }
        return domanda.get();
    }
}
