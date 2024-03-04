package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.entity.Preventivo;
import it.work2gether.quickAnswer.exception.PreventivoNotFoundException;
import it.work2gether.quickAnswer.repository.PreventivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreventivoServiceImpl implements PreventivoService {
    @Autowired
    private PreventivoRepository preventivoRepository;

    @Override
    public Preventivo findById(Integer idPreventivo) throws PreventivoNotFoundException {
        Optional<Preventivo> preventivo = preventivoRepository.findById(idPreventivo);

        if (preventivo.isEmpty()) {
            throw new PreventivoNotFoundException();
        }
        return preventivo.get();
    }

    @Override
    public void savePreventivo(Preventivo preventivo) {
        preventivoRepository.save(preventivo);
    }

    @Override
    public void acceptPreventivo(Integer idPreventivo) {
        preventivoRepository.accettaPreventivo(idPreventivo);
    }

    @Override
    public List<Preventivo> findPreventiviByResponder(Integer idResponder) {
        return preventivoRepository.findPreventiviByResponder(idResponder);
    }

    @Override
    public List<Preventivo> findPreventiviByDomanda(Integer idDomanda) {
        return preventivoRepository.findPreventiviByDomanda(idDomanda);
    }

    @Override
    public Preventivo findPreventivoAccettatoByDomanda(Integer idDomanda) {
        return preventivoRepository.findPreventivoAccettatoByDomanda(idDomanda).orElse(null);
    }

    @Override
    public void updateRispostaInPreventivo(Integer idPreventivo, Integer idRisposta) {
        preventivoRepository.updateRispostaInPreventivo(idPreventivo, idRisposta);
    }


}
