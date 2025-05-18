package service;

import model.Libro;
import model.Genere;
import model.StatoLettura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.StorageStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class GestoreLibriTest {

    private GestoreLibri gestore;
    private Libro libro1;
    private Libro libro2;
    private InMemoryStorageStrategy storage;

    // Implementazione in-memory per StorageStrategy
    static class InMemoryStorageStrategy implements StorageStrategy {
        private Map<String, Libro> data = new HashMap<>();

        @Override
        public void salva(Map<String, Libro> libreria, String percorso) {
            data = new HashMap<>(libreria);
        }

        @Override
        public Map<String, Libro> carica(String percorso) {
            return new HashMap<>(data);
        }
    }

    @BeforeEach
    void setUp() {
        storage = new InMemoryStorageStrategy();
        gestore = GestoreLibri.getInstance();

        // Reset dello stato del singleton
        gestore.caricaDaFile("test", new InMemoryStorageStrategy());

        libro1 = new Libro("123", "Il Signore degli Anelli", "Tolkien", Genere.FANTASY, StatoLettura.LETTO);
        libro2 = new Libro("456", "1984", "Orwell", Genere.DISTOPIA, StatoLettura.DA_LEGGERE);
    }

    @Test
    void testAggiungiLibro() {
        gestore.aggiungiLibro(libro1);
        assertEquals(1, gestore.getLibreria().size());
        assertEquals(libro1, gestore.getLibreria().get(0));
    }

    @Test
    void testAggiungiLibroDuplicato() {
        gestore.aggiungiLibro(libro1);
        assertThrows(IllegalArgumentException.class, () -> gestore.aggiungiLibro(libro1));
    }

    @Test
    void testRimuoviLibro() {
        gestore.aggiungiLibro(libro1);
        assertTrue(gestore.rimuoviLibro("123"));
        assertEquals(0, gestore.getLibreria().size());
    }

    @Test
    void testRimuoviLibroInesistente() {
        assertFalse(gestore.rimuoviLibro("999"));
    }

    @Test
    void testCercaTitolo() {
        gestore.aggiungiLibro(libro1);
        gestore.aggiungiLibro(libro2);

        List<Libro> risultati = gestore.cercaTitolo("1984");
        assertEquals(1, risultati.size());
        assertEquals(libro2, risultati.get(0));
    }

    @Test
    void testCercaTitoloCaseInsensitive() {
        gestore.aggiungiLibro(libro1);
        List<Libro> risultati = gestore.cercaTitolo("iL sIgNORE degli anelli");
        assertEquals(1, risultati.size());
    }

    @Test
    void testSalvaECarica() {
        gestore.aggiungiLibro(libro1);
        gestore.salvaSuFile("test", storage);

        gestore.caricaDaFile("test", storage);
        assertEquals(1, gestore.getLibreria().size());
        assertEquals(libro1.getTitolo(), gestore.getLibreria().get(0).getTitolo());
    }

    @Test
    void testOrdina() {
        gestore.aggiungiLibro(libro2);
        gestore.aggiungiLibro(libro1);

        gestore.setOrdinamento(l -> l.sort(Comparator.comparing(Libro::getTitolo)));
        List<Libro> ordinati = gestore.ordina();

        assertEquals(libro1.getTitolo(), ordinati.get(1).getTitolo());
        assertEquals(libro2.getTitolo(), ordinati.get(0).getTitolo());
    }

    @Test
    void testFiltra() {
        gestore.aggiungiLibro(libro1);
        gestore.aggiungiLibro(libro2);

        gestore.setFiltro(l -> l.stream()
                .filter(libro -> libro.getGenere() == Genere.DISTOPIA)
                .collect(Collectors.toList()));

        List<Libro> filtrati = gestore.filtra();
        assertEquals(1, filtrati.size());
        assertEquals(libro2, filtrati.get(0));
    }
}