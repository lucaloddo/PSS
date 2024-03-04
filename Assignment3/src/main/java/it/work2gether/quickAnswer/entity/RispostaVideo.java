package it.work2gether.quickAnswer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Video")
public class RispostaVideo extends Risposta {

    @Column(name = "video", length = 45)
    private String video;

}