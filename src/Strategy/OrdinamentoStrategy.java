package Strategy;

import model.Libro;
import java.util.List;

public interface OrdinamentoStrategy {
    void ordina(List<Libro> libreria);
}
