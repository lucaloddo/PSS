package it.work2gether.quickAnswer.repository;

import it.work2gether.quickAnswer.entity.Preventivo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PreventivoRepository extends CrudRepository<Preventivo, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Preventivo p SET p.isAccettato = TRUE WHERE p.id = :idPreventivo")
    void accettaPreventivo(@Param("idPreventivo") Integer idPreventivo);

    @Query("SELECT p FROM Preventivo p WHERE p.utenteResponder.id = :idResponder")
    List<Preventivo> findPreventiviByResponder(@Param("idResponder") Integer idResponder);

    @Query("SELECT p FROM Preventivo p WHERE p.domanda.id = :idDomanda")
    List<Preventivo> findPreventiviByDomanda(@Param("idDomanda") Integer idDomanda);

    @Query("SELECT p FROM Preventivo p WHERE p.domanda.id = :idDomanda AND p.isAccettato = TRUE")
    Optional<Preventivo> findPreventivoAccettatoByDomanda(@Param("idDomanda") Integer idDomanda);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Preventivo p SET p.risposta.id = :idRisposta WHERE p.id = :idPreventivo")
    void updateRispostaInPreventivo(@Param("idPreventivo") Integer idPreventivo,
                                    @Param("idRisposta") Integer idRisposta);
}
