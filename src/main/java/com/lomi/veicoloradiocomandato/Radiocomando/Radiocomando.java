package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Vehicle.Veicolo;

public class Radiocomando implements Observer {
    private Veicolo veicolo;

    public void manubrio() {}
    public void acceleratore() {}
    public void marce() {}
    public void freno() {}
    public void scegliVeicolo(Veicolo veicolo) {}

    @Override
    public void update(String evento) {
        // Inserire update
    }
}