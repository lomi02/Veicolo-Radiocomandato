package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.SQLException;

public class Tree extends Obstacle {
    public Tree(String nome, String immagine, String collisione) throws SQLException {
        super(nome, immagine, collisione);
    }
}