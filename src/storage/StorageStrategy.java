package storage;

import model.Libro;

import java.util.List;

public interface StorageStrategy {
    void salva(List<Libro> libreria, String percorso);
    List<Libro> carica(String percorso);
}
