package Strategy;

import model.Libro;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroAutore implements FiltroStrategy {
    private String autore;
    public FiltroAutore(String autore) {
        this.autore = autore;
    }
    @Override
    public List<Libro> filtra(List<Libro> libreria) {
        return libreria.stream().filter(libro -> libro.getAutore().contains(autore)).collect(Collectors.toList());
    }
}
