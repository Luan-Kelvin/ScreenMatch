package br.com.alura.sreecMatch.Service;


import br.com.alura.sreecMatch.DTO.SerieDto;
import br.com.alura.sreecMatch.Models.DadosSerie;
import br.com.alura.sreecMatch.Models.DadosTemporada;
import br.com.alura.sreecMatch.Models.Episodio;
import br.com.alura.sreecMatch.Models.Serie;
import br.com.alura.sreecMatch.Repository.EpisodioRepository;
import br.com.alura.sreecMatch.Repository.SerieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SerieService {
    @Autowired
    SerieRepository serieRepository;

    @Autowired
    EpisodioRepository episodioRepository;

    @Autowired
    DadosService dadosService;

    @Autowired
    ConverterDados converterDados;

    public List<SerieDto> obterSerie(){
        return converterDados.converterParaDTO(serieRepository.findAll());

    }

    @Transactional
    public List<SerieDto> obterTop3(){
        return converterDados.converterParaDTO(serieRepository.findAll());
    }

    public void buscarSerie(String nomeSerie){
        try {
            DadosSerie dadosSerie = dadosService.obterDadosGerais(nomeSerie);

            dadosSerie = dadosService.obterDadosGerais(nomeSerie);
            Serie serie = new Serie(dadosSerie);

            for (int i = 1;i <= dadosSerie.totaltemporadas();i++){
                DadosTemporada dadosTemporada = dadosService.obterDadosTemporadas(nomeSerie, i);

                int numTemporada = i;

                List <Episodio> episodiosTemporarios = dadosTemporada.episodios().stream()
                        .map(de -> new Episodio(numTemporada, de))
                        .toList();

                for (Episodio et : episodiosTemporarios) {
                    serie.adicionarEpisodio(et);
                }

            }


            serieRepository.save(serie);
            System.out.println(serie);

        }catch (Exception e){
            System.out.println("Erro ao realizar busca por: "+nomeSerie);
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
    }

    @Transactional
    public void listarSeries(){
        List<Serie> lista = serieRepository.findAll();

        lista.stream().sorted(Comparator.comparing(Serie::getGenero).thenComparing(Serie::getTitle)).forEach(System.out::println);
    }

    @Transactional
    public void buscarEpisodiosPorSerie(String nomeSerie){
        Optional<Serie> serie = serieRepository.findByTitleIgnoreCase(nomeSerie);

        if (!serie.isPresent()){
            throw new RuntimeException("Não foi possível localizar série: "+nomeSerie);
        }

        List<Episodio> episodios = serie.get().getEpisodios();

        System.out.println(episodios.size());
        System.out.println("Episódios de "+serie.get().getTitle());
        episodios.forEach(System.out::println);

    }

    @Transactional
    public Serie buscarSeriePorTitulo(String titulo){
        Optional<Serie> serie = serieRepository.findByTitleContainingIgnoreCase(titulo);

        if (!serie.isPresent()){
            System.out.println("Série não foi encontrada.");
            throw new RuntimeException("Série não foi encontrada.");
        }

        return serie.get();
    }

    @Transactional
    public void buscarPorAtor(String nomeAtor){
        List<Serie> series = serieRepository.findByAtoresContainingIgnoreCase(nomeAtor);

        if (series.isEmpty()){
            System.out.println("Não foi enontrada nenhuma serie cadastrada do autor: "+nomeAtor);
            return;
        }

        series.forEach(System.out::println);
    }

    @Transactional
    public void top5Series(){
        List<Serie> seriesTop = serieRepository.findTop5ByOrderByAvaliacaoDesc();

        seriesTop.forEach(System.out::println);
    }

    @Transactional
    public void buscarSeriesPorTemporadaEAvaliacao(int numTemp, double avaliacao){
        List<Serie> series = serieRepository.buscraSeriesPorTemporadaEAvaliacao(numTemp, avaliacao);

        if (series.isEmpty()){
            System.out.println("nenhuma série foie encontrda.");
        }

        series.forEach(System.out::println);
    }

    @Transactional
    public void buscarSeriesPorTempoEAvaiacao(int numTemp, Double avaliacao){
        List<Serie> series = serieRepository.buscraSeriesPorTemporadaEAvaliacao(numTemp, avaliacao);

        if (series.isEmpty()){
            System.out.println("`não foi possível localizar series com este dados.");
        }

        series.forEach(System.out::println);
    }

    @Transactional
    public void buscarEpisodioPorTrecho(String trecho){
        List<Episodio> episodios = episodioRepository.buscarEpiodioPorTrecho(trecho);

        if (episodios.isEmpty()){
            System.out.println("Não foi possível localizar ep por trecho: "+trecho);
            return;
        }

        episodios.forEach(System.out::println);
    }

    @Transactional
    public void top5EpisodiosDaSerie(String nomeSerie){
        Optional<Serie> serie = serieRepository.findByTitleIgnoreCase(nomeSerie);

        if (!serie.isPresent()){
            System.out.println("Série não foi encontrada.");
            return;
        }

        List<Episodio> top5 = episodioRepository.top5Episodios(serie.get().getTitle());

        top5.forEach(System.out::println);
    }


}
