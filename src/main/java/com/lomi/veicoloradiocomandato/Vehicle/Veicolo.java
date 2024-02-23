package com.lomi.veicoloradiocomandato.Vehicle;

import com.lomi.veicoloradiocomandato.Radiocomando.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Veicolo extends VeicoloData {
    private final List<Observer> observers = new ArrayList<>();

    public Veicolo(String codice, String marca, double frequenza, String colore, String urlImmagine, String collisione) {
        super(marca, urlImmagine, collisione);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String evento) {
        for (Observer observer : observers) {
            observer.update(evento);
        }
    }

    public void sensoriProssimita() {

    }

    public void luciAnteriori() {

    }

    public void sensoreCrepuscolare() {

    }

    public void scattaFoto() {

    }
}