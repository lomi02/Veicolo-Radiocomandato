package com.lomi.veicoloradiocomandato.Vehicle;

public abstract class VeicoloData {
    private final String codice;
    private final String marca;
    private final double frequenza;
    private final String colore;
    private final String urlImmagine;
    private final String collisione;

    public VeicoloData(String codice, String marca, double frequenza, String colore, String url, String collisione) {
        this.codice = codice;
        this.marca = marca;
        this.frequenza = frequenza;
        this.colore = colore;
        this.urlImmagine = url;
        this.collisione = collisione;
    }

    public String getCodice() {
        return codice;
    }

    public String getMarca() {
        return marca;
    }

    public double getFrequenza() {
        return frequenza;
    }

    public String getColore() {
        return colore;
    }

    public String getUrl() {
        return urlImmagine;
    }

    public String getCollisione() {
        return collisione;
    }
}