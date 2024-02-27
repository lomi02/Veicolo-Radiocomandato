package com.lomi.veicoloradiocomandato.Radiocomando;

/**
 * L'interfaccia RadiocomandoInterface definisce i metodi necessari per gestire le operazioni
 * relative al radiocomando di un veicolo.
 */
public interface RadiocomandoInterface {

    /**
     * Restituisce la marcia attuale impostata sul radiocomando.
     *
     * @return La marcia attuale.
     */
    int getMarciaAttuale();

    /**
     * Reimposta la marcia sul radiocomando, riportandola alla marcia iniziale.
     */
    void resetMarcia();

    /**
     * Incrementa la marcia attuale sul radiocomando.
     */
    void incrementaMarcia();

    /**
     * Decrementa la marcia attuale sul radiocomando.
     */
    void decrementaMarcia();
}