package it.work2gether.quickAnswer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "preventivo")
public class Preventivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 45)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_responder_id", nullable = false)
    private Utente utenteResponder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "domanda_id", nullable = false)
    private Domanda domanda;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "prezzo", nullable = false)
    private Double prezzo;

    @Column(name = "approfondimento", nullable = false)
    private Integer approfondimento;

    @Column(name = "giorni_attesa", nullable = false)
    private Integer giorniAttesa;

    @Column(name = "isAccettato", nullable = false)
    private Boolean isAccettato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risposta_id")
    private Risposta risposta;

}