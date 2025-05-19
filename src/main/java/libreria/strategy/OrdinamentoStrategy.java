package libreria.strategy;

import libreria.model.Libro;

import java.util.List;

public interface OrdinamentoStrategy {
    void ordina(List<Libro> libreria);
}
