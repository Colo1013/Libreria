package Strategy;

import model.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroValutazione implements FiltroStrategy {
    private int valutazione;
    public FiltroValutazione(int valutazione) {
        this.valutazione = valutazione;
    }
    @Override
    public List<Libro> filtra(List<Libro> libreria) {
        return libreria.stream().filter(libro -> libro.getValutazione() == valutazione).collect(Collectors.toList());
    }
}
