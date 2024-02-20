package com.lomi.veicoloradiocomandato.Ostacoli;

import org.json.JSONObject;

import java.io.FileNotFoundException;

public class Tree extends Obstacle {
    public Tree(JSONObject jsonObject) throws FileNotFoundException {
        super(jsonObject.get("immagine").toString());
    }
}