package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.dto.CommentatoCommentoDto;
import it.work2gether.quickAnswer.dto.CommentatoreCommentoDto;
import it.work2gether.quickAnswer.entity.Commento;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentoService {
    List<CommentatoreCommentoDto> findCommentiByIdReceiver(Integer idReceiver);

    List<CommentatoCommentoDto> findCommentiByIdSender(Integer idSender);

    void saveCommento(Commento commento);

    void deleteCommento(Integer idCommento);

}
