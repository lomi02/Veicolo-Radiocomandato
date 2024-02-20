package com.lomi.veicoloradiocomandato.Ostacoli;

import org.json.JSONObject;

import java.io.FileNotFoundException;

public class Cone extends Obstacle {
    public Cone(JSONObject jsonObject) throws FileNotFoundException {
        super(jsonObject.get("immagine").toString());
    }
}