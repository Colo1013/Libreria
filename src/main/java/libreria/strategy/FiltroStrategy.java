package libreria.strategy;

import libreria.model.Libro;

import java.util.List;

public interface FiltroStrategy {
    List<Libro> filtra(List<Libro> libreria);
}
