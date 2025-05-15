package Strategy;

import model.Libro;

import java.util.ArrayList;
import java.util.List;

public class FiltroComposto implements FiltroStrategy {
    private List<FiltroStrategy> filtri;
    public FiltroComposto(List<FiltroStrategy> filtri) {
        this.filtri = filtri;
    }

    @Override
    public List<Libro> filtra(List<Libro> libreria) {
        List<Libro> risultato = new ArrayList<>(libreria);
        for (FiltroStrategy filtro : filtri){
            risultato = filtro.filtra(risultato);
        }
        return risultato;
    }
}
