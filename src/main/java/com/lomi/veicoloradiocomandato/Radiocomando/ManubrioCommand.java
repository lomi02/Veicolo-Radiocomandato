package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;

public class ManubrioCommand implements Command {
    private final VeicoloManager veicoloManager;
    private final String direzione;

    public ManubrioCommand(VeicoloManager veicoloManager, String direzione) {
        this.veicoloManager = veicoloManager;
        this.direzione = direzione;
    }

    @Override
    public void execute() {
        veicoloManager.spostaVeicolo(direzione);
    }
}