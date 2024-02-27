package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe ObstacleFetcher si occupa di recuperare gli ostacoli dal database SQLite.
 */
public class ObstacleFetcher {

    private static final Logger LOGGER = Logger.getLogger(ObstacleFetcher.class.getName());
    private static final String DB_FILE_PATH = "identifier.sqlite";
    private final List<Obstacle> ostacoli = new ArrayList<>();

    /**
     * Costruttore della classe ObstacleFetcher.
     * Carica gli ostacoli dal database durante l'inizializzazione.
     */
    public ObstacleFetcher() {
        loadObstaclesFromDatabase();
    }

    /**
     * Carica gli ostacoli dal database SQLite.
     */
    private void loadObstaclesFromDatabase() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:" + DB_FILE_PATH;
            connection = DriverManager.getConnection(url);

            String sql = "SELECT * FROM OSTACOLI";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String nome_src = resultSet.getString("NOME_SRC");
                Optional<Obstacle> ostacoloOpt = ObstacleFactory.creaOstacolo(
                        nome_src,
                        resultSet.getString("NOME"),
                        resultSet.getString("URL_IMMAGINE"));

                if (ostacoloOpt.isPresent()) {
                    ostacoli.add(ostacoloOpt.get());
                } else {
                    LOGGER.log(Level.WARNING, "Fallimento nella creazione dell'ostacolo: " + nome_src);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Errore nell'accesso al database SQLite", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Errore nella chiusura della connessione al database SQLite", ex);
            }
        }
    }

    /**
     * Restituisce la lista degli ostacoli recuperati dal database.
     *
     * @return La lista degli ostacoli.
     */
    public List<Obstacle> getObstacles() {
        return ostacoli;
    }
}