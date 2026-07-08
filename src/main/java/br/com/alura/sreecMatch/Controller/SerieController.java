package br.com.alura.sreecMatch.Controller;

import br.com.alura.sreecMatch.DTO.SerieDto;
import br.com.alura.sreecMatch.Service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    SerieService serieService;

    @GetMapping
    public List<SerieDto> obterSerie(){
       return serieService.obterSerie();
    }

    @GetMapping("/top5")
    public List<SerieDto> top3Series(){
        return serieService.obterTop3();
    }

    @GetMapping("/lancamentos")
    public List<SerieDto> lancamentos(){
        return serieService.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDto obterPorId(@PathVariable Long id){
        return serieService.obterPorId(id);
    }
}
