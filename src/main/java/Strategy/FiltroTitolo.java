package Strategy;

import model.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroTitolo implements FiltroStrategy {
    private final String titolo;

    public FiltroTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public List<Libro> filtra(List<Libro> libreria) {
        return libreria.stream().filter(libro -> libro.getTitolo().contains(titolo)).collect(Collectors.toList());
    }
}
