package com.lomi.veicoloradiocomandato.Ostacoli;

/**
 * La classe Oil estende la classe Obstacle e rappresenta un ostacolo di tipo macchia d'olio.
 */
public class Oil extends Obstacle {

    /**
     * Costruttore della classe Oil.
     *
     * @param nome     Il nome della macchia d'olio.
     * @param immagine Il percorso dell'immagine associata alla macchia d'olio.
     */
    public Oil(String nome, String immagine) {
        super(nome, immagine);
    }
}