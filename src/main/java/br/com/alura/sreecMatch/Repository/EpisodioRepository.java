package br.com.alura.sreecMatch.Repository;

import br.com.alura.sreecMatch.Models.Episodio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpisodioRepository extends JpaRepository<Episodio, Long> {

    @Query("""
            SELECT e
            FROM Episodio e
            WHERE LOWER(e.titulo ) = LOWER(:trechoEp)
            """)
    List<Episodio> buscarEpiodioPorTrecho(String trechoEp);

    @Query("""
            SELECT e
            FROM Serie s
            JOIN s.episodios e
            WHERE LOWER(s.title) = LOWER(:nomeSerie)
            ORDER BY e.avaliacao DESC
            LIMIT 5
            """)
    List<Episodio> top5Episodios(String nomeSerie);
}
