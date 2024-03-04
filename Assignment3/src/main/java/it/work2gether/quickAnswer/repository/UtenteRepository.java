package it.work2gether.quickAnswer.repository;

import it.work2gether.quickAnswer.entity.Utente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UtenteRepository extends CrudRepository<Utente, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM utente WHERE email = :email")
    Utente findByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT * FROM utente WHERE nickname = :nickname")
    Utente findByNickname(@Param("nickname") String nickname);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Utente u SET u.rating = :rating WHERE u.id = :idUtente")
    void updateRatingUtente(@Param("idUtente") Integer idUtente, @Param("rating") Double rating);

}

