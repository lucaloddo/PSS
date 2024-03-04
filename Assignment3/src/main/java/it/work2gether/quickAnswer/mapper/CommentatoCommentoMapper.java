package it.work2gether.quickAnswer.mapper;

import it.work2gether.quickAnswer.dto.CommentatoCommentoDto;
import it.work2gether.quickAnswer.entity.Commento;

public class CommentatoCommentoMapper {
    public CommentatoCommentoDto toDto(Commento entity) {
        CommentatoCommentoDto dto = new CommentatoCommentoDto();

        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setTesto(entity.getTesto());
        dto.setCommentatoreAcquirente(entity.getCommentatoreAcquirente());

        ProfiloPubblicoMapper profiloPubblicoMapper = new ProfiloPubblicoMapper();
        dto.setCommentato(profiloPubblicoMapper.toDto(entity.getUtenteCommentato()));

        return dto;
    }

    public Commento toEntity(CommentatoCommentoDto dto) {
        Commento entity = new Commento();

        entity.setId(dto.getId());
        entity.setData(dto.getData());
        entity.setTesto(dto.getTesto());
        entity.setCommentatoreAcquirente(dto.getCommentatoreAcquirente());

        ProfiloPubblicoMapper profiloPubblicoMapper = new ProfiloPubblicoMapper();
        entity.setUtenteCommentato(profiloPubblicoMapper.toEntity(dto.getCommentato()));

        return entity;
    }
}
