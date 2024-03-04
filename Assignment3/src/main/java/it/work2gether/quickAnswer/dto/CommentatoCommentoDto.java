package it.work2gether.quickAnswer.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentatoCommentoDto {

    private Integer id;

    private ProfiloPubblicoDto commentato;

    private Boolean commentatoreAcquirente;

    private String testo;

    private LocalDateTime data;
}
