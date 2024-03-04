package it.work2gether.quickAnswer.mapper;

import it.work2gether.quickAnswer.dto.ProfiloPubblicoDto;
import it.work2gether.quickAnswer.entity.Utente;

public class ProfiloPubblicoMapper {
    public ProfiloPubblicoDto toDto(Utente entity) {
        ProfiloPubblicoDto dto = new ProfiloPubblicoDto();

        dto.setId(entity.getId());
        dto.setRating(entity.getRating());
        dto.setNickname(entity.getNickname());
        dto.setLinkCv(entity.getLinkCv());
        dto.setDescrizione(entity.getDescrizione());

        return dto;
    }

    public Utente toEntity(ProfiloPubblicoDto dto) {
        Utente entity = new Utente();

        entity.setId(dto.getId());
        entity.setRating(dto.getRating());
        entity.setNickname(dto.getNickname());
        entity.setLinkCv(dto.getLinkCv());
        entity.setDescrizione(dto.getDescrizione());

        return entity;
    }
}
