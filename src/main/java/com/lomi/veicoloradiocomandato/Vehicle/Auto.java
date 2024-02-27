package com.lomi.veicoloradiocomandato.Vehicle;

/**
 * La classe Auto estende la classe astratta Veicolo e rappresenta un veicolo di tipo auto.
 */
public class Auto extends Veicolo {

    /**
     * Costruttore della classe Auto.
     *
     * @param marca La marca dell'auto.
     * @param url   L'URL dell'immagine associata all'auto.
     */
    public Auto(String marca, String url) {
        super(marca, url);
    }
}