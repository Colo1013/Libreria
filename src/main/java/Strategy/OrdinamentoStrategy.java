package main.java.Strategy;

import main.java.model.Libro;

import java.util.List;

public interface OrdinamentoStrategy {
    void ordina(List<Libro> libreria);
}
