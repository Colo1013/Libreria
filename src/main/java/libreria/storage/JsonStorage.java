package libreria.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import libreria.model.Libro;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class JsonStorage implements StorageStrategy {
    private Type listType = new TypeToken<Map<String, Libro>>() {}.getType();
    private Gson gson = new Gson();
    @Override
    public void salva(Map<String, Libro> libreria, String percorso) {
        String json = gson.toJson(libreria);
        try {
            Files.writeString(Path.of(percorso), json);
        } catch (IOException e) {
            throw new RuntimeException("Il file inserito non è valido");
        }
    }
    @Override
    public Map<String, Libro> carica(String percorso) {
        String contenuto = null;
        try {
            contenuto = Files.readString(Path.of(percorso));
        } catch (IOException e) {
            throw new RuntimeException("Il file inserito non è valido");
        }
        Map<String, Libro> caricati = gson.fromJson(contenuto, listType);
        return caricati;
    }
}
