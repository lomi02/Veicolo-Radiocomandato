package com.lomi.veicoloradiocomandato.Ostacoli;

import org.json.JSONObject;

import java.io.FileNotFoundException;

public class Oil extends Obstacle {
    public Oil(JSONObject jsonObject) throws FileNotFoundException {
        super(jsonObject.get("immagine").toString());
    }
}