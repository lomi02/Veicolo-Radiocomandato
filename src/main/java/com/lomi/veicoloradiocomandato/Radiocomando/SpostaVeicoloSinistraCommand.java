package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;

/**
 * La classe SpostaVeicoloSinistraCommand implementa l'interfaccia Command
 * e rappresenta un comando per spostare un veicolo verso sinistra.
 */
public class SpostaVeicoloSinistraCommand implements Command {

    private final VeicoloManager veicoloManager;

    /**
     * Costruttore della classe SpostaVeicoloSinistraCommand.
     *
     * @param veicoloManager Il gestore dei veicoli al quale verrà delegato lo spostamento del veicolo.
     */
    public SpostaVeicoloSinistraCommand(VeicoloManager veicoloManager) {
        this.veicoloManager = veicoloManager;
    }

    /**
     * Esegue il comando di spostamento del veicolo verso sinistra attraverso il gestore dei veicoli.
     */
    @Override
    public void execute() {
        veicoloManager.muoviVeicolo("sinistra");
    }
}