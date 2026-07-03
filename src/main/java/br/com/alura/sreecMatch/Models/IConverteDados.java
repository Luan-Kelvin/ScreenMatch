package br.com.alura.sreecMatch.Models;

public interface IConverteDados {

    <T> T obterDados(String Json, Class<T> classe);
}
