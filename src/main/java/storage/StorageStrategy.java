package storage;

import model.Libro;


import java.util.Map;

public interface StorageStrategy {
    void salva(Map<String,Libro> libreria, String percorso);

    Map<String, Libro> carica(String percorso);
}
