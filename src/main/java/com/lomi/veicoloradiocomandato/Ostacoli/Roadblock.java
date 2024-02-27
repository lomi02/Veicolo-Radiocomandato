package com.lomi.veicoloradiocomandato.Ostacoli;

/**
 * La classe Roadblock estende la classe Obstacle e rappresenta un ostacolo di tipo blocco stradale.
 */
public class Roadblock extends Obstacle {

    /**
     * Costruttore della classe Roadblock.
     *
     * @param nome    Il nome del blocco stradale.
     * @param immagine Il percorso dell'immagine associata al blocco stradale.
     */
    public Roadblock(String nome, String immagine) {
        super(nome, immagine);
    }
}