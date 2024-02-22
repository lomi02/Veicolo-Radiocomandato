package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObstacleFactory {

    // Logger per raccogliere eventi/errori in questa classe
    private static final Logger LOGGER = Logger.getLogger(ObstacleFactory.class.getName());

    // Metodo statico per creare un oggetto Obstacle basato su parametri specifici
    public static Obstacle creaOstacolo(String nome_src, String nome, String immagine, String collisione) throws SQLException {
        try {

            // Switch basato su 'nome_src' per determinare il tipo di ostacolo da creare
            return switch (nome_src) {

                // Se 'nome_src' è "cone", crea un nuovo oggetto Cone
                case "CONE" -> new Cone(nome, immagine, collisione);

                // Se 'nome_src' è "hole", crea un nuovo oggetto Hole
                case "HOLE" -> new Hole(nome, immagine, collisione);

                // Se 'nome_src' è "oil", crea un nuovo oggetto Oil
                case "OIL" -> new Oil(nome, immagine, collisione);

                // Se 'nome_src' è "roadblock", crea un nuovo oggetto Roadblock
                case "ROADBLOCK" -> new Roadblock(nome, immagine, collisione);

                // Se 'nome_src' è "tree", crea un nuovo oggetto Tree
                case "TREE" -> new Tree(nome, immagine, collisione);

                // Se 'nome_src' non corrisponde a nessuno dei precedenti, lancia un'eccezione IllegalArgumentException
                default -> throw new IllegalArgumentException();
            };
        } catch (IllegalArgumentException e) {

            // Registra un errore grave se il 'nome_src' non corrisponde a nessuno dei tipi di ostacolo supportati.
            LOGGER.log(Level.SEVERE, "Tipo di ostacolo non supportato", e);

            // Ritorna null se si verifica un'eccezione
            return null;
        }
    }
}