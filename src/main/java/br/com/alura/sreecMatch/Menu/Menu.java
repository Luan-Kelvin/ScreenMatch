package br.com.alura.sreecMatch.Menu;

import br.com.alura.sreecMatch.Repository.SerieRepository;
import br.com.alura.sreecMatch.Service.DadosService;
import br.com.alura.sreecMatch.Service.SerieService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Menu {
    private Scanner input = new Scanner(System.in);
    private DadosService dadosService = new DadosService();
    private int resp = 1;

    private final SerieRepository repository;
    private final SerieService serieService;

    public Menu(SerieRepository repository, SerieService serieService){
        this.repository = repository;
        this.serieService =serieService;
    }


    public void exibirMenu(){
        System.out.println(">>>>>>>>>>>>>>> MENU <<<<<<<<<<<<<<<");

        while (resp != 0){
            System.out.println("[ 1 ] -> Buscar Série.");
            System.out.println("[ 2 ] -> Buscar Episódios.");
            System.out.println("[ 3 ] -> Listar Séries.");
            System.out.println("[ 4 ] -> Buscar Série Por Título.");
            System.out.println("[ 5 ] -> Buscar Série Por Autor.");
            System.out.println("[ 6 ] -> Top 5 Séries.");
            System.out.println("[ 7 ] -> Buscar Series Por Número de temoradas e avaliação.");
            System.out.println("[ 8 ] -> Buscar Episódio Por Trecho.");
            System.out.println("[ 9 ] -> Top 5 Episódios dDe Uma Série.");
            System.out.println("[ 0 ] -> Sair.");
            System.out.println("Escolha uma das opções acima: ");
            System.out.println("-----------------------------------");
            resp = Integer.parseInt(input.nextLine());

            switch (resp){
                case 1 -> {
                    System.out.println("Digite nome da série para busca: ");
                    String nomeSerie = input.nextLine();

                    serieService.buscarSerie(nomeSerie);
                }

                case 2 -> {
                    System.out.println("Digite nome da série");
                    String nomeSerie = input.nextLine();

                    serieService.buscarEpisodiosPorSerie(nomeSerie);

                }

                case 3 -> serieService.listarSeries();

                case 4 -> {
                    System.out.println("Digite o título da série: ");
                    String titulo = input.nextLine();

                    System.out.println(serieService.buscarSeriePorTitulo(titulo));
                }

                case 5 -> {
                    System.out.println("Digite o nome do autor para busca: ");
                    String nomeAutor = input.nextLine();

                    serieService.buscarPorAtor(nomeAutor);
                }

                case 6 -> serieService.top5Series();

                case 7 -> {
                    System.out.println("Digite o número de temporadas: ");
                    int numTemp = Integer.parseInt(input.nextLine());

                    System.out.println("Digite a avaliação: ");
                    Double avaliacao = Double.parseDouble(input.nextLine());

                    serieService.buscarSeriesPorTemporadaEAvaliacao(numTemp, avaliacao);
                }

                case 8 -> {
                    System.out.println("Digite o trecho do episódio: ");
                    String trechoEp = input.nextLine();

                    serieService.buscarEpisodioPorTrecho(trechoEp);
                }

                case 9 -> {
                    System.out.println("Digite o nome da série: ");
                    String nomeSerie = input.nextLine();

                    serieService.top5EpisodiosDaSerie(nomeSerie);
                }

                case 0 -> System.out.println("Ecerrando programa.");

                default -> System.out.println("Ecolha uma opção válida.");
            }
        }

        System.out.println(">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<");
    }
}
