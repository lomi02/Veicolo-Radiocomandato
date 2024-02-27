package com.lomi.veicoloradiocomandato.Ostacoli;

/**
 * La classe Cone estende la classe Obstacle e rappresenta un ostacolo di tipo cono stradale.
 */
public class Cone extends Obstacle {

    /**
     * Costruttore della classe Cone.
     *
     * @param nome    Il nome del cono stradale.
     * @param immagine Il percorso dell'immagine associata al cono stradale.
     */
    public Cone(String nome, String immagine) {
        super(nome, immagine);
    }
}