package com.lomi.veicoloradiocomandato.Vehicle;

/**
 * La classe VeicoloFactory e' responsabile per la creazione di istanze di veicoli.
 */
public class VeicoloFactory {

    /**
     * Metodo statico per la creazione di un veicolo con la marca e l'URL dell'immagine forniti.
     *
     * @param marca       La marca del veicolo da creare.
     * @param urlImmagine L'URL dell'immagine associata al veicolo.
     * @return Un'istanza di Veicolo, in questo caso, un'istanza di Auto con la marca e l'URL forniti.
     */
    public static Veicolo creaVeicolo(String marca, String urlImmagine) {
        return new Auto(marca, urlImmagine);
    }
}