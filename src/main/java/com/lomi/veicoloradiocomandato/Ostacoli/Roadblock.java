package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.SQLException;

public class Roadblock extends Obstacle {
    public Roadblock(String nome, String immagine, String collisione) throws SQLException {
        super(nome, immagine, collisione);
    }
}