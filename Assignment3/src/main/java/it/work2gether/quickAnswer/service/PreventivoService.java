package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.entity.Preventivo;
import it.work2gether.quickAnswer.exception.PreventivoNotFoundException;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreventivoService {
    Preventivo findById(Integer idPreventivo) throws PreventivoNotFoundException;

    void savePreventivo(Preventivo preventivo);

    void acceptPreventivo(Integer idPreventivo);

    List<Preventivo> findPreventiviByResponder(Integer idResponder);

    List<Preventivo> findPreventiviByDomanda(Integer idDomanda);

    Preventivo findPreventivoAccettatoByDomanda(@Param("idDomanda") Integer idDomanda);

    void updateRispostaInPreventivo(@Param("idPreventivo") Integer idPreventivo, @Param("idRisposta") Integer idRisposta);
}
