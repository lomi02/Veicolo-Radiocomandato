package com.lomi.veicoloradiocomandato.Vehicle;

import org.json.JSONObject;

public class VeicoloFactory {
    public static Veicolo creaVeicolo(String tipo, JSONObject jsonObject) {
        return switch (tipo) {
            case "auto" -> new Auto(jsonObject);
            case "moto" -> new Moto(jsonObject);
            case "camion" -> new Camion(jsonObject);
            default -> throw new IllegalArgumentException("Tipo di veicolo non supportato");
        };
    }
}