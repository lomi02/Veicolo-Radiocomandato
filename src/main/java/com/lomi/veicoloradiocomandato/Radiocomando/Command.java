package com.lomi.veicoloradiocomandato.Radiocomando;

/**
 * L'interfaccia Command rappresenta un comando generico che può essere eseguito.
 */
public interface Command {

    /**
     * Esegue il comando specifico implementato dalla classe che implementa questa interfaccia.
     */
    void execute();
}