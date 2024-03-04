package it.work2gether.quickAnswer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "domanda")
public class Domanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "testo_quesito", nullable = false, length = 45)
    private String testoQuesito;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_asker_id", nullable = false)
    private Utente utenteAsker;

    @Column(name = "titolo", nullable = false, length = 45)
    private String titolo;

    @Column(name = "livello_app", nullable = false)
    private Integer livelloApp;

    @Column(name = "materia", nullable = false, length = 45)
    private String materia;

}