package it.work2gether.quickAnswer.repository;

import it.work2gether.quickAnswer.entity.Domanda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DomandaRepository extends CrudRepository<Domanda, Integer> {

    @Query("SELECT d " +
            "FROM Domanda d " +
            "WHERE d.utenteAsker.id <> :idAsker " +
            "AND NOT EXISTS (SELECT p " +
            "                FROM Preventivo p " +
            "                WHERE d.id = p.domanda.id " +
            "                AND p.isAccettato = TRUE)")
    List<Domanda> getElencoDomandeDisponibiliButAsker(@Param("idAsker") Integer idAsker);

    @Query("SELECT d " +
            "FROM Domanda d " +
            "WHERE d.utenteAsker.id = :idAsker")
    List<Domanda> getElencoDomandeByAsker(@Param("idAsker") Integer idAsker);
}
