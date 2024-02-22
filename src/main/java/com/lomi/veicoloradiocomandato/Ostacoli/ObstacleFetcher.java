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

    // Logger per raccogliere eventi/errori in questa classe
    private static final Logger LOGGER = Logger.getLogger(ObstacleFetcher.class.getName());

    // Percorso del file del database SQLite
    private static final String DB_FILE_PATH = "identifier.sqlite";

    // Lista per contenere gli oggetti ostacolo
    private final List<Obstacle> ostacoli = new ArrayList<>();

    public ObstacleFetcher() {

        // Il costruttore chiama il metodo per caricare gli ostacoli dal database SQLite
        loadObstaclesFromDB();
    }

    private void loadObstaclesFromDB() {

        // Oggetto Connection per SQLite
        Connection connection = null;
        try {

            // URL del database SQLite
            String url = "jdbc:sqlite:" + DB_FILE_PATH;

            // Apre una connessione al database SQLite
            connection = DriverManager.getConnection(url);

            // Query SQL per selezionare tutte le righe dalla tabella OSTACOLI
            String sql = "SELECT * FROM OSTACOLI";

            // Crea un oggetto Statement dalla connessione
            Statement statement = connection.createStatement();

            // Esegue la query SQL e ottiene il ResultSet
            ResultSet resultSet = statement.executeQuery(sql);

            // Itera ogni riga nel ResultSet
            while (resultSet.next()) {

                // Ottiene il valore 'nome_src' dalla riga
                String nome_src = resultSet.getString("NOME_SRC");

                // Chiama il metodo di fabbrica per creare un oggetto Obstacle dai valori della riga
                Obstacle ostacolo = ObstacleFactory.creaOstacolo(
                        nome_src,
                        resultSet.getString("NOME"),
                        resultSet.getString("URL_IMMAGINE"),
                        resultSet.getString("COLLISIONE"));
                try {

                    // Aggiunge l'oggetto Obstacle creato alla lista
                    ostacoli.add(ostacolo);

                } catch (NullPointerException e) {

                    // Registra un avvertimento se c'è stata un'eccezione di puntatore nullo durante la creazione dell'oggetto ostacolo
                    LOGGER.log(Level.WARNING, "Fallimento nella creazione dell'ostacolo: " + nome_src, e);
                }
            }

            // Se si verifica un'eccezione SQL durante l'accesso al database, registra un messaggio di errore grave.
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Errore nell'accesso al database SQLite", e);
        } finally {
            try {

                // Chiude la connessione al database SQLite
                if (connection != null) {
                    connection.close();
                }

                // Se si verifica un'eccezione SQL durante la chiusura della connessione, registra un messaggio di errore grave.
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Errore nella chiusura della connessione al database SQLite", ex);
            }
        }
    }

    // Metodo getter per fornire accesso esterno alla lista degli oggetti ostacolo
    public List<Obstacle> getObstacles() {
        return ostacoli;
    }
}