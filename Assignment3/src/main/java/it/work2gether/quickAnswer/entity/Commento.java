package it.work2gether.quickAnswer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "commento")
public class Commento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 45)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_commentatore_id", nullable = false)
    private Utente utenteCommentatore;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_commentato_id", nullable = false)
    private Utente utenteCommentato;

    @Column(name = "commentatore_acquirente", nullable = false) //si potrebbe recuperare, ma per efficienza salvo il bit
    private Boolean commentatoreAcquirente;

    @Column(name = "testo", nullable = false, length = 45)
    private String testo;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;
}