package main.java.model;

public class Libro {
    // Campi obbligatori in fase di costruzione (aggiunto titolo)
    private final String titolo;
    private final String autore;
    private final String isbn;

    // Campi modificabili
    private StatoLettura statoLettura;
    private Genere genere;
    private int valutazione;

    // Costruttore completo con campi obbligatori
    public Libro(String isbn, String titolo, String autore, Genere genere, StatoLettura statoLettura) {
        // Validazione campi obbligatori
        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("Titolo obbligatorio");
        }
        if (autore == null || autore.isBlank()) {
            throw new IllegalArgumentException("Autore obbligatorio");
        }

        this.isbn = isbn; // TODO: aggiungere validazione ISBN in futuro
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.statoLettura = statoLettura;
    }

    // Getter per campi immutabili
    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    // Getter/Setter per campi modificabili
    public StatoLettura getStatoLettura() {
        return statoLettura;
    }

    public void setStatoLettura(StatoLettura statoLettura) {
        this.statoLettura = statoLettura;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        if (valutazione < 1 || valutazione > 5) { // Modificato a 1-5 come da specifica originale
            throw new IllegalArgumentException("Valutazione deve essere tra 1 e 5 stelle");
        }
        this.valutazione = valutazione;
    }

    // equals e hashCode basati su ISBN
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        return isbn.equals(((Libro) o).isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}