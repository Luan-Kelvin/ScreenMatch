package br.com.alura.sreecMatch.Controller;

import br.com.alura.sreecMatch.DTO.SerieDto;
import br.com.alura.sreecMatch.Service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SerieController {
    @Autowired
    SerieService serieService;

    @GetMapping("/series")
    public List<SerieDto> obterSerie(){
       return serieService.obterSerie();
    }

    @GetMapping("/series/top3")
    public List<SerieDto> top3Series(){
        return serieService.obterTop3();
    }
}
