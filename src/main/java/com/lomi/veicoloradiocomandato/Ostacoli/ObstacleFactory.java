package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObstacleFactory {
    private static final Logger LOGGER = Logger.getLogger(ObstacleFactory.class.getName());
    public static Obstacle creaOstacolo(String nome_src, String nome, String immagine, String collisione) throws SQLException {
        try {

            return switch (nome_src) {
                case "CONE" -> new Cone(nome, immagine, collisione);
                case "HOLE" -> new Hole(nome, immagine, collisione);
                case "OIL" -> new Oil(nome, immagine, collisione);
                case "ROADBLOCK" -> new Roadblock(nome, immagine, collisione);
                case "TREE" -> new Tree(nome, immagine, collisione);
                default -> throw new IllegalArgumentException();
            };
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "Tipo di ostacolo non supportato", e);
            return null;
        }
    }
}