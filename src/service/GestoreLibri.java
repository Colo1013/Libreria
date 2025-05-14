package service;

import model.Libro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestoreLibri {
    private List<Libro> libreria;

    public GestoreLibri() {
        this.libreria = new ArrayList<Libro>();
    }

    public List<Libro> getLibreria() {
        return libreria;
    }

    public void aggiungiLibro(Libro libro) {
        this.libreria.add(libro);
    }

    public boolean  rimuoviLibro(String isbn) {
        return libreria.removeIf(libro -> libro.getIsbn().equals(isbn));
    }

    public Libro cercaTitolo(String titolo){
        for (Libro libro : libreria) {
            if (libro.getTitolo().equals(titolo)) {
                return libro;
            }
        }
        return null;
    }
    public void ordinaPerTitolo(){
        libreria.sort(Comparator.comparing(Libro::getTitolo));
    }

}
