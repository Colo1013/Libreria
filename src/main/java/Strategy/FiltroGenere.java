package main.java.Strategy;

import main.java.model.Genere;
import main.java.model.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroGenere implements FiltroStrategy {
    private final Genere genere;

    public FiltroGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public List<Libro> filtra(List<Libro> libreria) {
        return libreria.stream().filter(libro -> libro.getGenere().equals(genere)).collect(Collectors.toList());
    }
}
