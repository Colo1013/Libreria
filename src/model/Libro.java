package model;

public class Libro {
    private String titolo;
    private String autore;
    private String isbn;
    private StatoLettura statoLettura;
    private Genere genere;


    public String getAutore() {
        return autore;
    }
    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }
    public  StatoLettura getStatoLettura(){
        return statoLettura;
    }
    public Genere getGenere() {
        return genere;
    }
    public void setAutore(String autore) {
        this.autore = autore;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public void setStatoLettura(StatoLettura statoLettura) {
        this.statoLettura = statoLettura;
    }
    public void setGenere(Genere genere) {
        this.genere = genere;
    }
}
