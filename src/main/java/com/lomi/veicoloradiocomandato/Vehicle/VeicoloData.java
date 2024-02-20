package com.lomi.veicoloradiocomandato.Vehicle;

import org.json.JSONObject;

public abstract class VeicoloData {
    private final String codice;
    private final String tipo;
    private final String marca;
    private final double frequenza;

    public VeicoloData(JSONObject jsonObject) {
        this.codice = jsonObject.getString("codice");
        this.tipo = jsonObject.getString("tipo");
        this.marca = jsonObject.getString("marca");
        this.frequenza = jsonObject.getDouble("frequenza");
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
}