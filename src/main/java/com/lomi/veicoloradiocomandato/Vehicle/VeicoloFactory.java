package com.lomi.veicoloradiocomandato.Vehicle;

public class VeicoloFactory {
    public static Veicolo creaVeicolo(String codice, String marca, double frequenza, String colore, String urlImmagine, String collisione) {
        return new Auto(codice, marca, frequenza, colore, urlImmagine, collisione);
    }
}