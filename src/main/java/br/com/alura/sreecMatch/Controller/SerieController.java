package br.com.alura.sreecMatch.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerieController {

    @GetMapping("/series")
    public String obterSeries(){
        return "Aqui apareceram as series.";
    }
}
