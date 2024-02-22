package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.SQLException;

public class Oil extends Obstacle {
    public Oil(String nome, String immagine, String collisione) throws SQLException {
        super(nome, immagine, collisione);
    }
}