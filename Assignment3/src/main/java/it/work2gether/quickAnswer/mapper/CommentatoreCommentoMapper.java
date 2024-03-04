package it.work2gether.quickAnswer.mapper;

import it.work2gether.quickAnswer.dto.CommentatoreCommentoDto;
import it.work2gether.quickAnswer.entity.Commento;

public class CommentatoreCommentoMapper {
    public CommentatoreCommentoDto toDto(Commento entity) {
        CommentatoreCommentoDto dto = new CommentatoreCommentoDto();

        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setTesto(entity.getTesto());
        dto.setCommentatoreAcquirente(entity.getCommentatoreAcquirente());

        ProfiloPubblicoMapper profiloPubblicoMapper = new ProfiloPubblicoMapper();
        dto.setCommentatore(profiloPubblicoMapper.toDto(entity.getUtenteCommentatore()));

        return dto;
    }

    public Commento toEntity(CommentatoreCommentoDto dto) {
        Commento entity = new Commento();

        entity.setId(dto.getId());
        entity.setData(dto.getData());
        entity.setTesto(dto.getTesto());
        entity.setCommentatoreAcquirente(dto.getCommentatoreAcquirente());

        ProfiloPubblicoMapper profiloPubblicoMapper = new ProfiloPubblicoMapper();
        entity.setUtenteCommentatore(profiloPubblicoMapper.toEntity(dto.getCommentatore()));

        return entity;
    }
}
