package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Vehicle.Veicolo;

public class Radiocomando implements Observer {
    private Veicolo veicolo;
    public void scegliVeicolo(Veicolo veicolo) {}

    public void cambiaVeicolo() {}
    public void accendiLuci() {}
    public void spegniLuci() {}
    public void scattaFoto() {}

    @Override
    public void update(String evento) {
        // Inserire update
    }
}
