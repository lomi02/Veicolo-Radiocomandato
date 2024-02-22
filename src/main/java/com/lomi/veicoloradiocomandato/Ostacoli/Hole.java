package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.SQLException;

public class Hole extends Obstacle {
    public Hole(String nome, String immagine, String collisione) throws SQLException {
        super(nome, immagine, collisione);
    }
}