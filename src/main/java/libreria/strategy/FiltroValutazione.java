package libreria.strategy;

import libreria.model.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroValutazione implements FiltroStrategy {
    private final int valutazione;

    public FiltroValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    @Override
    public List<Libro> filtra(List<Libro> libreria) {
        return libreria.stream().filter(libro -> libro.getValutazione() == valutazione).collect(Collectors.toList());
    }
}
