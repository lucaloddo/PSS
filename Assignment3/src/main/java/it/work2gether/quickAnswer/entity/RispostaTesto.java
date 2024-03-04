package it.work2gether.quickAnswer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Testo")
public class RispostaTesto extends Risposta {

    @Column(name = "testo", length = 400)
    private String testo;

}