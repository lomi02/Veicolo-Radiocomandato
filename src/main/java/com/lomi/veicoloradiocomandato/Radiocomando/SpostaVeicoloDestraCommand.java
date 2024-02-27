package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;

/**
 * La classe SpostaVeicoloDestraCommand implementa l'interfaccia Command
 * e rappresenta un comando per spostare un veicolo verso destra.
 */
public class SpostaVeicoloDestraCommand implements Command {

    private final VeicoloManager veicoloManager;

    /**
     * Costruttore della classe SpostaVeicoloDestraCommand.
     *
     * @param veicoloManager Il gestore dei veicoli al quale verrà delegato lo spostamento del veicolo.
     */
    public SpostaVeicoloDestraCommand(VeicoloManager veicoloManager) {
        this.veicoloManager = veicoloManager;
    }

    /**
     * Esegue il comando di spostamento del veicolo verso destra attraverso il gestore dei veicoli.
     */
    @Override
    public void execute() {
        veicoloManager.muoviVeicolo("destra");
    }
}