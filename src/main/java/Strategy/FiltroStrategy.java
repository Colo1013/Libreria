package main.java.Strategy;

import main.java.model.Libro;

import java.util.List;

public interface FiltroStrategy {
    List<Libro> filtra(List<Libro> libreria);
}
