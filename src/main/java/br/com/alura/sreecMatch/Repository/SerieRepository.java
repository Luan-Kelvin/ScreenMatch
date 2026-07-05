package br.com.alura.sreecMatch.Repository;

import br.com.alura.sreecMatch.Models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    Optional<Serie> findByTitleIgnoreCase(String nomeSerie);

    Optional<Serie> findByTitleContainingIgnoreCase(String titulo);

    List<Serie> findByAtoresContainingIgnoreCase(String nomeAtor);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findTop3ByOrderByAvaliacaoDesc();

    @Query("""
            SELECT s
            FROM Serie s
            WHERE s.totaltemporadas <= :numTemp AND s.avaliacao >= :avaliacao
            """)
    List<Serie> buscraSeriesPorTemporadaEAvaliacao(int numTemp, double avalicao);
}
