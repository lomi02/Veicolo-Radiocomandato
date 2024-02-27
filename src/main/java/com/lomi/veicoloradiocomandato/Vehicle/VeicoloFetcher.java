package com.lomi.veicoloradiocomandato.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe VeicoloFetcher è responsabile per il recupero dei veicoli da un database SQLite.
 */
public class VeicoloFetcher {

    private static final Logger LOGGER = Logger.getLogger(VeicoloFetcher.class.getName());
    private static final String DB_FILE_PATH = "identifier.sqlite";
    private final List<Veicolo> veicoli = new ArrayList<>();

    /**
     * Costruttore della classe VeicoloFetcher. Carica i veicoli dal database durante l'inizializzazione.
     */
    public VeicoloFetcher() {
        loadVeicoliFromDatabase();
    }

    /**
     * Metodo privato per caricare i veicoli dal database SQLite.
     */
    private void loadVeicoliFromDatabase() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:" + DB_FILE_PATH;
            connection = DriverManager.getConnection(url);

            String sql = "SELECT * FROM VEICOLI";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                String codice = resultSet.getString("CODICE");
                Veicolo veicolo = VeicoloFactory.creaVeicolo(
                        resultSet.getString("MARCA"),
                        resultSet.getString("URL_IMMAGINE"));
                try {
                    veicoli.add(veicolo);
                } catch (NullPointerException e) {
                    LOGGER.log(Level.WARNING, "Fallimento nella creazione dell'veicolo: " + codice, e);
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
     * Restituisce un veicolo in base alla marca fornita.
     *
     * @param marca La marca del veicolo da cercare.
     * @return Il veicolo corrispondente alla marca, o null se non trovato.
     */
    public Veicolo getVeicoloPerMarca(String marca) {
        for (Veicolo veicolo : veicoli) {
            if (veicolo.getMarca().equals(marca)) {
                return veicolo;
            }
        }
        return null;
    }
}