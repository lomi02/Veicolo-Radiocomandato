package com.lomi.veicoloradiocomandato.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeicoloFetcher {

    // Logger per raccogliere eventi/errori in questa classe
    private static final Logger LOGGER = Logger.getLogger(VeicoloFetcher.class.getName());

    // Percorso del file del database SQLite
    private static final String DB_FILE_PATH = "identifier.sqlite";

    // Lista per contenere gli oggetti ostacolo
    private final List<Veicolo> veicoli = new ArrayList<>();

    public VeicoloFetcher() {

        // Il costruttore chiama il metodo per caricare gli ostacoli dal database SQLite
        loadVeicoliFromDatabase();
    }

    private void loadVeicoliFromDatabase() {

        // Oggetto Connection per SQLite
        Connection connection = null;
        try {

            // URL del database SQLite
            String url = "jdbc:sqlite:" + DB_FILE_PATH;

            // Apre una connessione al database SQLite
            connection = DriverManager.getConnection(url);

            // Query SQL per selezionare tutte le righe dalla tabella OSTACOLI
            String sql = "SELECT * FROM VEICOLI";

            // Crea un oggetto Statement dalla connessione
            Statement statement = connection.createStatement();

            // Esegue la query SQL e ottiene il ResultSet
            ResultSet resultSet = statement.executeQuery(sql);

            // Itera ogni riga nel ResultSet
            while (resultSet.next()) {

                // Ottiene il valore 'codice' dalla riga
                String codice = resultSet.getString("CODICE");

                // Chiama il metodo di fabbrica per creare un oggetto Veicolo dai valori della riga
                Veicolo veicolo = VeicoloFactory.creaVeicolo(
                        codice,
                        resultSet.getString("MARCA"),
                        resultSet.getDouble("FREQUENZA"),
                        resultSet.getString("COLORE"),
                        resultSet.getString("URL"),
                        resultSet.getString("COLLISION"));
                try {

                    // Aggiunge l'oggetto Obstacle creato alla lista
                    veicoli.add(veicolo);

                } catch (NullPointerException e) {

                    // Registra un avvertimento se c'è stata un'eccezione di puntatore nullo durante la creazione dell'oggetto veicolo
                    LOGGER.log(Level.WARNING, "Fallimento nella creazione dell'veicolo: " + codice, e);
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

    // Metodo getter per fornire accesso esterno alla lista degli oggetti veicolo
    public List<Veicolo> getVeicoli() {
        return veicoli;
    }
}