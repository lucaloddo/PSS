package it.work2gether.quickAnswer.repository;

import it.work2gether.quickAnswer.entity.Risposta;
import it.work2gether.quickAnswer.entity.Utente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RispostaRepository<T extends Risposta> extends CrudRepository<T, Integer> {
    @Query("SELECT d, r " +
            "FROM Risposta r, Utente u, Preventivo p, Domanda d " +
            "WHERE u.id = :idResponder " +
            "AND p.utenteResponder.id = u.id " +
            "AND p.domanda.id = d.id " +
            "AND p.risposta.id = r.id")
    List<Object[]> findRisposteDomandeByIdResponder(@Param("idResponder") Integer idResponder);

    @Query("SELECT r " +
            "FROM Domanda d, Preventivo p, Utente responder, Utente asker, Risposta r " +
            "WHERE d.id = p.domanda.id " +
            "AND p.utenteResponder.id = responder.id " +
            "AND d.utenteAsker.id = asker.id " +
            "AND p.risposta.id = r.id " +
            "AND asker.id = :idAsker " +
            "AND responder.id = :idResponder ")
    List<Risposta> findRisposteByAskerResponder(@Param("idAsker") Integer idAsker,
                                                @Param("idResponder") Integer idResponder);

    @Query("SELECT r " +
            "FROM Domanda d, Preventivo p, Utente responder, Utente asker, Risposta r " +
            "WHERE d.id = p.domanda.id " +
            "AND p.utenteResponder.id = responder.id " +
            "AND d.utenteAsker.id = asker.id " +
            "AND p.risposta.id = r.id " +
            "AND asker.id = :idAsker " +
            "AND responder.id = :idResponder " +
            "AND r.data < :untilTime")
    List<Risposta> findRisposteByAskerResponderUntil(@Param("idAsker") Integer idAsker,
                                                     @Param("idResponder") Integer idResponder,
                                                     @Param("untilTime") LocalDateTime untilTime);

    @Query("SELECT r " +
            "FROM Risposta r, Preventivo p " +
            "WHERE p.risposta.id = r.id " +
            "AND p.id = :idPreventivo")
    Optional<Risposta> findRispostaByPreventivo(@Param("idPreventivo") Integer idPreventivo);

    @Query("SELECT r " +
            "FROM Risposta r, Utente u, Preventivo p " +
            "WHERE p.risposta.id = r.id " +
            "AND p.utenteResponder.id = :idResponder " +
            "AND r.rating <> 0")
    List<Risposta> findRisposteRatedByResponder(@Param("idResponder") Integer idResponder);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Risposta r SET r.rating = :rating WHERE r.id = :idRisposta")
    void updateRatingRisposta(@Param("idRisposta") Integer idRisposta, @Param("rating") Integer rating);

    @Query("SELECT u " +
            "FROM Utente u, Risposta r, Preventivo p " +
            "WHERE r.id = :idRisposta " +
            "AND p.risposta.id = r.id " +
            "AND u.id = p.utenteResponder.id")
    Utente findResponderByRisposta(@Param("idRisposta") Integer idRisposta);
}
