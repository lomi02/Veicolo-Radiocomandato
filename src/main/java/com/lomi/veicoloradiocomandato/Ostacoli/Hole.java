package com.lomi.veicoloradiocomandato.Ostacoli;

/**
 * La classe Hole estende la classe Obstacle e rappresenta un ostacolo di tipo buca.
 */
public class Hole extends Obstacle {

    /**
     * Costruttore della classe Hole.
     *
     * @param nome    Il nome della buca.
     * @param immagine Il percorso dell'immagine associata alla buca.
     */
    public Hole(String nome, String immagine) {
        super(nome, immagine);
    }
}