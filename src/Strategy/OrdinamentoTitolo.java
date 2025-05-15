package Strategy;

import model.Libro;

import java.util.Comparator;
import java.util.List;

public class OrdinamentoTitolo implements OrdinamentoStrategy {
    @Override
    public void ordina(List<Libro> libreria) {
        libreria.sort(Comparator.comparing(Libro::getTitolo));
    }

}
