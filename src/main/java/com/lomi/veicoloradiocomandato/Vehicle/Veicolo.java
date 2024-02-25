package com.lomi.veicoloradiocomandato.Vehicle;

public abstract class Veicolo extends VeicoloData {
    public Veicolo(String codice, String marca, double frequenza, String colore, String urlImmagine, String collisione) {
        super(marca, urlImmagine, collisione);
    }

    public void sensoriProssimita() {
        // Implementa la logica per i sensori di prossimità
    }

    public void sensoreCrepuscolare() {
        // Implementa la logica per il sensore crepuscolare
    }

    public void scattaFoto() {
        // Implementa la logica per scattare una foto
    }
}
