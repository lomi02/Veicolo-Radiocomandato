package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe ObstacleFactory è responsabile della creazione degli oggetti Ostacolo.
 */
public class ObstacleFactory {

    private static final Logger LOGGER = Logger.getLogger(ObstacleFactory.class.getName());

    /**
     * Crea un oggetto Ostacolo in base al tipo specificato.
     *
     * @param nome_src  Il tipo di ostacolo specificato come stringa.
     * @param nome      Il nome dell'ostacolo.
     * @param immagine  Il percorso dell'immagine associata all'ostacolo.
     * @return Un Optional contenente l'oggetto Ostacolo creato, o vuoto se il tipo non è supportato.
     * @throws SQLException Se si verifica un errore durante l'accesso al database.
     */
    public static Optional<Obstacle> creaOstacolo(String nome_src, String nome, String immagine) throws SQLException {
        try {
            Obstacle obstacle = switch (nome_src) {
                case "CONE" -> new Cone(nome, immagine);
                case "HOLE" -> new Hole(nome, immagine);
                case "OIL" -> new Oil(nome, immagine);
                case "ROADBLOCK" -> new Roadblock(nome, immagine);
                case "TREE" -> new Tree(nome, immagine);
                default -> throw new IllegalArgumentException("Tipo di ostacolo non supportato: " + nome_src);
            };

            return Optional.of(obstacle);

        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "Tipo di ostacolo non supportato: " + nome_src, e);
            return Optional.empty();
        }
    }
}