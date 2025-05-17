package main.java.Strategy;

import main.java.model.Libro;
import main.java.model.StatoLettura;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroLettura implements FiltroStrategy {
    private final StatoLettura statoLettura;

    public FiltroLettura(StatoLettura statoLettura) {
        this.statoLettura = statoLettura;
    }

    @Override
    public List<Libro> filtra(List<Libro> libreria) {
        return libreria.stream().filter(libro -> libro.getStatoLettura().equals(statoLettura)).collect(Collectors.toList());
    }
}
