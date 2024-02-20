package com.lomi.veicoloradiocomandato.Vehicle;

import com.lomi.veicoloradiocomandato.Radiocomando.Observer;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Veicolo extends VeicoloData {
    private final List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void notifyObservers(String evento) {
        for (Observer observer : observers) {
            observer.update(evento);
        }
    }

    public Veicolo(JSONObject jsonObject) {
        super(jsonObject);
    }

    public void sensoriProssimita(){

    }

    public void luciAnteriori(){

    }

    public void sensoreCrepuscolare(){

    }

    public void scattaFoto(){

    }
}