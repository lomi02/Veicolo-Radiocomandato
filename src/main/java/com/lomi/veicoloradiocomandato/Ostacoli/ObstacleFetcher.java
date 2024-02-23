package com.lomi.veicoloradiocomandato.Ostacoli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObstacleFetcher {
    private static final Logger LOGGER = Logger.getLogger(ObstacleFetcher.class.getName());
    private static final String DB_FILE_PATH = "identifier.sqlite";
    private final List<Obstacle> ostacoli = new ArrayList<>();

    public ObstacleFetcher() {
        loadObstaclesFromDatabase();
    }

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
                Obstacle ostacolo = ObstacleFactory.creaOstacolo(
                        nome_src,
                        resultSet.getString("NOME"),
                        resultSet.getString("URL_IMMAGINE"),
                        resultSet.getString("COLLISIONE"));
                try {
                    ostacoli.add(ostacolo);
                } catch (NullPointerException e) {
                    LOGGER.log(Level.WARNING, "Fallimento nella creazione dell'ostacolo: " + nome_src, e);
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

    public List<Obstacle> getObstacles() {
        return ostacoli;
    }
}