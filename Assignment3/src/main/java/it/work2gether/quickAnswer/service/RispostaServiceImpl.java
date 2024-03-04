package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.entity.*;
import it.work2gether.quickAnswer.exception.RispostaNotFoundException;
import it.work2gether.quickAnswer.repository.RispostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RispostaServiceImpl implements RispostaService {
    @Autowired
    private RispostaRepository<Risposta> rispostaRepository;

    @Autowired
    private RispostaRepository<RispostaTesto> rispostaTestoRepository;

    @Autowired
    private RispostaRepository<RispostaVideo> rispostaVideoRepository;

    @Override
    public Map<Domanda, Risposta> findRisposteDomandeByIdResponder(Integer idResponder) {
        List<Object[]> prove = rispostaRepository.findRisposteDomandeByIdResponder(idResponder);
        Map<Domanda, Risposta> map = new HashMap<>();
        for (Object[] prova : prove) {
            Domanda domanda = (Domanda) prova[0];
            Risposta risposta1 = (Risposta) prova[1];
            map.put(domanda, risposta1);
        }
        return map;
    }

    @Override
    public List<Risposta> findRisposteByAskerResponder(Integer idAsker, Integer idResponder) {
        return rispostaRepository.findRisposteByAskerResponder(idAsker, idResponder);
    }

    @Override
    public List<Risposta> findRisposteByAskerResponderUntil(Integer idAsker, Integer idResponder,
                                                            LocalDateTime untilTime) {
        return rispostaRepository.findRisposteByAskerResponderUntil(idAsker, idResponder, untilTime);
    }

    @Override
    public Risposta findRispostaById(Integer id) throws RispostaNotFoundException {
        Optional<Risposta> risposta = rispostaRepository.findById(id);
        if (risposta.isEmpty()) {
            throw new RispostaNotFoundException();
        }
        return risposta.get();
    }

    @Override
    public Risposta findRispostaByPreventivo(Integer idPreventivo) {
        return rispostaRepository.findRispostaByPreventivo(idPreventivo).orElse(null);
    }

    @Override
    public List<Risposta> findRisposteRatedByResponder(Integer idResponder) {
        return rispostaRepository.findRisposteRatedByResponder(idResponder);
    }

    @Override
    public RispostaTesto saveRispostaTesto(RispostaTesto risposta) {
        return rispostaTestoRepository.save(risposta);
    }

    @Override
    public RispostaVideo saveRispostaVideo(RispostaVideo risposta) {
        return rispostaVideoRepository.save(risposta);
    }

    @Override
    public void updateRatingRisposta(Integer idRisposta, Integer rating) {
        rispostaRepository.updateRatingRisposta(idRisposta, rating);
    }

    @Override
    public Utente findResponderByRisposta(Integer idRisposta) {
        return rispostaRepository.findResponderByRisposta(idRisposta);
    }

}
