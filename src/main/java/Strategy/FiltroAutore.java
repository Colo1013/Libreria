package main.java.Strategy;

import main.java.model.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroAutore implements FiltroStrategy {
    private final String autore;

    public FiltroAutore(String autore) {
        this.autore = autore;
    }

    @Override
    public List<Libro> filtra(List<Libro> libreria) {
        return libreria.stream().filter(libro -> libro.getAutore().contains(autore)).collect(Collectors.toList());
    }
}
