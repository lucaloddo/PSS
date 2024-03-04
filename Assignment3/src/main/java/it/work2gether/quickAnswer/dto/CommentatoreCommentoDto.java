package it.work2gether.quickAnswer.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentatoreCommentoDto {

    private Integer id;

    private ProfiloPubblicoDto commentatore;

    private Boolean commentatoreAcquirente;

    private String testo;

    private LocalDateTime data;
}
