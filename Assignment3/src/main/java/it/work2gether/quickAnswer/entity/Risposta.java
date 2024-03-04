package it.work2gether.quickAnswer.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "risposta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="tipoRisposta",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Risposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @ColumnDefault("0")
    @Column(name = "rating")
    private Integer rating;

}