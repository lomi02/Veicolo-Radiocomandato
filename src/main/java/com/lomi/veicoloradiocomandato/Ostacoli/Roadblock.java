package com.lomi.veicoloradiocomandato.Ostacoli;

import org.json.JSONObject;

import java.io.FileNotFoundException;

public class Roadblock extends Obstacle {
    public Roadblock(JSONObject jsonObject) throws FileNotFoundException {
        super(jsonObject.get("immagine").toString());
    }
}