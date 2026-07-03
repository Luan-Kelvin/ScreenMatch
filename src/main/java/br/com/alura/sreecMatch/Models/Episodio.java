package br.com.alura.sreecMatch.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "episodios")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temporada;
    private String titulo;
    private Integer numEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    @ManyToOne()
    @JoinColumn(name = "id_serie")
    private Serie serie;

    public Episodio() {
    }

    public Episodio(Integer temporada, DadosEpisodio e) {
        this.temporada = temporada;
        this.titulo = e.title();
        this.numEpisodio = e.numero();

        try {
            this.avaliacao = Double.parseDouble(e.avaliacao());
        } catch (Exception ex) {
            this.avaliacao = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(e.lancamento());
        } catch (Exception ex) {
            this.dataLancamento = null;
        }
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumEpisodio(Integer numEpisodio) {
        this.numEpisodio = numEpisodio;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }



    @Override
    public String toString() {
        return "Episodio{" +
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numEpisodio=" + numEpisodio +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento +
                '}';
    }
}
