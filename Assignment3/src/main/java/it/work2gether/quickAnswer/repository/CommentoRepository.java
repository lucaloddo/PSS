package it.work2gether.quickAnswer.repository;

import it.work2gether.quickAnswer.entity.Commento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentoRepository extends CrudRepository<Commento, Integer> {
    @Query("SELECT c " +
            "FROM Commento c " +
            "WHERE c.utenteCommentato.id = :idReceiver " +
            "ORDER BY c.data DESC")
    List<Commento> findCommentiByIdReceiver(@Param("idReceiver") Integer idReceiver);


    @Query("SELECT c " +
            "FROM Commento c " +
            "WHERE c.utenteCommentatore.id = :idSender " +
            "ORDER BY c.data DESC")
    List<Commento> findCommentiByIdSender(@Param("idSender") Integer idSender);


}
