package br.com.alura.sreecMatch.Service;

import br.com.alura.sreecMatch.DTO.SerieDto;
import br.com.alura.sreecMatch.Models.IConverteDados;
import br.com.alura.sreecMatch.Models.Serie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverterDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String Json, Class<T> classe) {
        try {
            return mapper.readValue(Json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SerieDto> converterParaDTO(List<Serie> series){
        return series
                .stream()
                .map(s -> new SerieDto(
                        s.getId(),
                        s.getTitle(),
                        s.getAvaliacao(),
                        s.getGenero(),
                        s.getTotaltemporadas(),
                        s.getAtores(),
                        s.getPoster(),
                        s.getSinopse()
                ))
                .toList();
    }
}
