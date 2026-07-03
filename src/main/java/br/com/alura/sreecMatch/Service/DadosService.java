package br.com.alura.sreecMatch.Service;

import br.com.alura.sreecMatch.Models.DadosEpisodio;
import br.com.alura.sreecMatch.Models.DadosSerie;
import br.com.alura.sreecMatch.Models.DadosTemporada;
import org.springframework.stereotype.Service;

@Service
public class DadosService {
    private String endereco = null;
    ConverterDados conversor = new ConverterDados();

    public DadosSerie obterDadosGerais(String nameSerie){
        endereco = "https://www.omdbapi.com/?t="+ nameSerie.replace(" ", "+") +"&apikey=5bf9ac42";
        String json = ConsumiApi.requisicao(endereco);
        return conversor.obterDados(json, DadosSerie.class);

    }

    public DadosEpisodio obterDadosEpisodio(String nameSerie, Integer temporada, Integer episodio){
        endereco = "https://www.omdbapi.com/?t="+ nameSerie.replace(" ", "+") +"&season="+ temporada +"&episode="+ episodio +"&apikey=5bf9ac42";
        String json = ConsumiApi.requisicao(endereco);
        return conversor.obterDados(json, DadosEpisodio.class);
    }

    public DadosTemporada obterDadosTemporadas(String nameSerie, Integer temporada){
        endereco = "https://www.omdbapi.com/?t="+ nameSerie.replace(" ", "+") +"&season="+ temporada +"&apikey=5bf9ac42";
        String json = ConsumiApi.requisicao(endereco);
        return conversor.obterDados(json, DadosTemporada.class);
    }
}
