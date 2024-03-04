package it.work2gether.quickAnswer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "enabled", nullable = false)
    private Integer enabled;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "cognome", nullable = false, length = 45)
    private String cognome;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @Column(name = "stato", nullable = false, length = 45)
    private String stato;

    @Column(name = "provincia", nullable = false, length = 45)
    private String provincia;

    @Column(name = "cap", nullable = false)
    private Integer cap;

    @Column(name = "citta", nullable = false, length = 45)
    private String citta;

    @Column(name = "indirizzo", nullable = false, length = 45)
    private String indirizzo;

    @Column(name = "civico", nullable = false)
    private Integer civico;

    @Column(name = "link_cv", length = 45)
    private String linkCv;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "descrizione", length = 45)
    private String descrizione;

    @Column(name = "nickname", nullable = false, length = 45)
    private String nickname;

    @Column(name = "saldo")
    private Double saldo;

    @Column(name = "ruolo", length = 45)
    private String ruolo;

}