package main.java.service;

import main.java.Strategy.FiltroStrategy;
import main.java.Strategy.OrdinamentoStrategy;
import main.java.model.Libro;
import main.java.storage.StorageStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GestoreLibri {
    private List<Libro> libreria;
    private OrdinamentoStrategy ordinamento;
    private FiltroStrategy filtro;

    public GestoreLibri() {
        this.libreria = new ArrayList<>();
    }

    public void setOrdinamento(OrdinamentoStrategy ordinamento) {
        this.ordinamento = Objects.requireNonNull(ordinamento,
                "Strategia di ordinamento non può essere null");
    }

    public void setFiltro(FiltroStrategy filtro) {
        this.filtro = Objects.requireNonNull(filtro,
                "Strategia di filtro non può essere null");
    }

    public List<Libro> getLibreria() {
        return Collections.unmodifiableList(libreria);
    }

    public void aggiungiLibro(Libro libro) {
        boolean presente = libreria.stream()
                .anyMatch(l -> l.getIsbn().equals(libro.getIsbn()));

        if (presente) {
            throw new IllegalArgumentException("ISBN già esistente: " + libro.getIsbn());
        }
        libreria.add(libro);
    }

    public boolean rimuoviLibro(String isbn) {
        return libreria.removeIf(libro -> libro.getIsbn().equals(isbn));
    }

    public List<Libro> cercaTitolo(String titolo) {
        return libreria.stream()
                .filter(n -> n.getTitolo().equalsIgnoreCase(titolo))
                .collect(Collectors.toList());
    }

    public List<Libro> ordina() {
        if (ordinamento == null) {
            throw new IllegalStateException("Strategia di ordinamento non impostata");
        }
        List<Libro> out = new ArrayList<>(libreria);
        ordinamento.ordina(out);
        return out;
    }

    public List<Libro> filtra() {
        if (filtro == null) {
            throw new IllegalStateException("Strategia di filtro non impostata");
        }
        return filtro.filtra(libreria);
    }

    public void salvaSuFile(String percorso, StorageStrategy storage) {
        storage.salva(libreria, percorso);
    }

    public void caricaDaFile(String percorso, StorageStrategy storage) {
        this.libreria = storage.carica(percorso);
    }
}