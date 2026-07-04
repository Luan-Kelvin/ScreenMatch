package br.com.alura.sreecMatch.Controller;

import br.com.alura.sreecMatch.DTO.SerieDto;
import br.com.alura.sreecMatch.Repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {
    @Autowired
    SerieRepository serieRepository;

    @GetMapping("/series")
    public List<SerieDto> obterSeries(){
        return serieRepository.findAll()
                .stream()
                .map(s ->  new SerieDto(
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
