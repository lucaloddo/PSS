package it.work2gether.quickAnswer.service;

import it.work2gether.quickAnswer.dto.CommentatoCommentoDto;
import it.work2gether.quickAnswer.dto.CommentatoreCommentoDto;
import it.work2gether.quickAnswer.entity.Commento;
import it.work2gether.quickAnswer.mapper.CommentatoCommentoMapper;
import it.work2gether.quickAnswer.mapper.CommentatoreCommentoMapper;
import it.work2gether.quickAnswer.repository.CommentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentoServiceImpl implements CommentoService {

    @Autowired
    private CommentoRepository commentoRepository;

    @Override
    public List<CommentatoreCommentoDto> findCommentiByIdReceiver(Integer idReceiver) {
        List<Commento> commenti = commentoRepository.findCommentiByIdReceiver(idReceiver);
        CommentatoreCommentoMapper commentatoreCommentoMapper = new CommentatoreCommentoMapper();

        List<CommentatoreCommentoDto> commentiDto = new ArrayList<>();
        for (Commento commento : commenti) {
            commentiDto.add(commentatoreCommentoMapper.toDto(commento));
        }

        return commentiDto;
    }

    @Override
    public List<CommentatoCommentoDto> findCommentiByIdSender(Integer idSender) {
        List<Commento> commenti = commentoRepository.findCommentiByIdSender(idSender);
        CommentatoCommentoMapper commentatoreCommentoMapper = new CommentatoCommentoMapper();

        List<CommentatoCommentoDto> commentiDto = new ArrayList<>();
        for (Commento commento : commenti) {
            commentiDto.add(commentatoreCommentoMapper.toDto(commento));
        }

        return commentiDto;
    }

    @Override
    public void saveCommento(Commento commento) {
        commentoRepository.save(commento);
    }

    @Override
    public void deleteCommento(Integer idCommento) {
        commentoRepository.deleteById(idCommento);
    }


}
