package br.com.alura.sreecMatch.DTO;

import br.com.alura.sreecMatch.Models.Categoria;

public record SerieDto(
        Long id,
        String titulo,
        Double avaliacao,
        Categoria genero,
        Integer totalTemporadas,
        String atores,
        String poster,
        String sinopse

) {
}
