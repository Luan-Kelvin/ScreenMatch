package br.com.alura.sreecMatch.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(
        @JsonAlias("Title") String title,
        @JsonAlias("Episode") Integer numero,
        @JsonAlias("Season") Integer temporada,
        @JsonAlias("imdbRating") String avaliacao,
        @JsonAlias("Released") String lancamento
) {
}
