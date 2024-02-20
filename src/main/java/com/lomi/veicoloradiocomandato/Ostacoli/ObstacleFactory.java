package com.lomi.veicoloradiocomandato.Ostacoli;
import org.json.JSONObject;

import java.io.FileNotFoundException;

public class ObstacleFactory {
    public static Obstacle creaOstacolo(String nome_src, JSONObject jsonObject) throws FileNotFoundException {
        return switch (nome_src) {
            case "cone" -> new Cone(jsonObject);
            case "hole" -> new Hole(jsonObject);
            case "oil" -> new Oil(jsonObject);
            case "roadblock" -> new Roadblock(jsonObject);
            case "tree" -> new Tree(jsonObject);
            default -> throw new IllegalArgumentException("Tipo di veicolo non supportato");
        };
    }
}