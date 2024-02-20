package com.lomi.veicoloradiocomandato.Ostacoli;

import org.json.JSONObject;

import java.io.FileNotFoundException;

public class Hole extends Obstacle {
    public Hole(JSONObject jsonObject) throws FileNotFoundException {
        super(jsonObject.get("immagine").toString());
    }
}