package com.lomi.veicoloradiocomandato.Vehicle;
import com.lomi.veicoloradiocomandato.Radiocomando.Observer;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class VeicoloStandard implements Veicolo {
    private final String codice;
    private final String tipo;
    private final String marca;
    private final double frequenza;

    public VeicoloStandard(JSONObject jsonObject) {
        this.codice = jsonObject.getString("codice");
        this.tipo = jsonObject.getString("tipo");
        this.marca = jsonObject.getString("marca");
        this.frequenza = jsonObject.getDouble("frequenza");
        observers = new ArrayList<>();
    }

    public String getCodice() {
        return codice;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public double getFrequenza() {
        return frequenza;
    }

    private final List<Observer> observers;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String evento) {
        for (Observer observer : observers) {
            observer.update(evento);
        }
    }
}