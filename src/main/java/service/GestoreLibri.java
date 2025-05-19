package service;

import Strategy.FiltroStrategy;
import Strategy.OrdinamentoStrategy;
import model.Libro;
import storage.StorageStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class GestoreLibri {
    private static GestoreLibri instance;
    private final Map<String, Libro> libreria = new HashMap<>();
    private List<Libro> listaLibri = new ArrayList<>();
    private boolean listAggiornata = false;
    private OrdinamentoStrategy ordinamento;
    private FiltroStrategy filtro;

    private GestoreLibri() {}

    public static GestoreLibri getInstance() {
        if (instance == null) {
            instance = new GestoreLibri();
        }
        return instance;
    }

    public void setOrdinamento(OrdinamentoStrategy ordinamento) {
        this.ordinamento = Objects.requireNonNull(ordinamento);
    }

    public void setFiltro(FiltroStrategy filtro) {
        this.filtro = Objects.requireNonNull(filtro);
    }


    private void aggiornaListaSeNecessario() {
        if (!listAggiornata) {
            listaLibri = new ArrayList<>(libreria.values());
            listAggiornata = true;
        }
    }

    public List<Libro> getLibreria() {
        aggiornaListaSeNecessario();
        return Collections.unmodifiableList(listaLibri);
    }


    public void aggiungiLibro(Libro libro) {
        String isbn = libro.getIsbn();
        if (libreria.containsKey(isbn)) {
            throw new IllegalArgumentException("ISBN già esistente: " + isbn);
        }
        libreria.put(isbn, libro);
        listAggiornata = false;
    }

    public  boolean rimuoviLibro(String isbn) {
        boolean rimosso = libreria.remove(isbn) != null;
        if (rimosso) {
            listAggiornata = false;
        }
        return rimosso;
    }


    public List<Libro> cercaTitolo(String titolo) {
        if (titolo == null || titolo.isBlank()) {
            return Collections.emptyList();
        }
        aggiornaListaSeNecessario();
        return listaLibri.stream()
                .filter(l -> titolo.equalsIgnoreCase(l.getTitolo()))
                .collect(Collectors.toList());
    }

    public List<Libro> ordina() {
        if (ordinamento == null) {
            throw new IllegalStateException("Strategia di ordinamento non impostata");
        }
        aggiornaListaSeNecessario();
        List<Libro> copia = new ArrayList<>(listaLibri);
        ordinamento.ordina(copia);
        return copia;
    }

    public List<Libro> filtra() {
        if (filtro == null) {
            throw new IllegalStateException("Strategia di filtro non impostata");
        }
        aggiornaListaSeNecessario();
        return filtro.filtra(listaLibri);
    }


    public void salvaSuFile(String percorso, StorageStrategy storage) {
        storage.salva(libreria, percorso);
    }

    public void caricaDaFile(String percorso, StorageStrategy storage) {
        this.libreria.clear();
        this.libreria.putAll(storage.carica(percorso));
        this.listAggiornata = false;
    }
}