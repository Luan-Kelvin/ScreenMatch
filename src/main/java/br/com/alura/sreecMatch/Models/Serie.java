package br.com.alura.sreecMatch.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private Double avaliacao;

    private Integer totaltemporadas;

    @Enumerated(EnumType.STRING)
    private Categoria genero;

    private String atores;

    private String poster;

    private String sinopse;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "serie")
    private List<Episodio> episodios= new ArrayList<>();

    public Serie(){}

    public Serie(DadosSerie dados) {
        this.title = dados.title();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dados.imdbRating())).orElse(0.0);
        this.totaltemporadas = dados.totaltemporadas();
        this.genero = Categoria.fromString(dados.genero().split(",")[0].trim());
        this.atores = dados.atores();
        this.poster = dados.poster();
        this.sinopse = dados.sinopse();
    }

    public void adicionarEpisodio(Episodio episodio){
        episodios.add(episodio);

        episodio.setSerie(this);
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public Integer getTotaltemporadas() {
        return totaltemporadas;
    }

    public Categoria getGenero() {
        return genero;
    }

    public String getAtores() {
        return atores;
    }

    public String getPoster() {
        return poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setTotaltemporadas(Integer totaltemporadas) {
        this.totaltemporadas = totaltemporadas;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return  "Gênero= " + genero +
                ", title= '" + title + '\'' +
                ", avaliacao= " + avaliacao +
                ", totaltemporadas= " + totaltemporadas +
                ", atores= '" + atores + '\'' +
                ", poster= '" + poster + '\'' +
                ", sinopse= '" + sinopse;
    }
}
