package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.entity.*;
import it.work2gether.quickAnswer.exception.RispostaNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface RispostaService {
    Map<Domanda, Risposta> findRisposteDomandeByIdResponder(Integer idResponder);

    List<Risposta> findRisposteByAskerResponder(Integer idAsker, Integer idResponder);

    List<Risposta> findRisposteByAskerResponderUntil(Integer idAsker, Integer idResponder,
                                                     LocalDateTime untilTime);

    Risposta findRispostaById(Integer id) throws RispostaNotFoundException;

    Risposta findRispostaByPreventivo(Integer idPreventivo);

    List<Risposta> findRisposteRatedByResponder(Integer idResponder);

    RispostaTesto saveRispostaTesto(RispostaTesto risposta);

    RispostaVideo saveRispostaVideo(RispostaVideo risposta);

    void updateRatingRisposta(Integer idRisposta, Integer rating);

    Utente findResponderByRisposta(Integer idRisposta);

}
