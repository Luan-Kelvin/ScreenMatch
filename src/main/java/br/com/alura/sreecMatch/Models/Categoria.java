package br.com.alura.sreecMatch.Models;

public enum Categoria {
    ACAO("Action"), DRAMA("Drama"), ROMANCE("Romance"), CRIME("Crime"), COMEDIA("Comedy");

    private String genereoImdb;

    Categoria(String generoOmdb){
        this.genereoImdb = generoOmdb;
    }

    public static Categoria fromString(String txt){
        for (Categoria categoria : Categoria.values()){
            if (categoria.genereoImdb.equalsIgnoreCase(txt)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nao foi encontrado nenhum gênero correspondente.");
    }
}
