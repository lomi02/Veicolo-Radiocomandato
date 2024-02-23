package com.lomi.veicoloradiocomandato.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeicoloFetcher {
    private static final Logger LOGGER = Logger.getLogger(VeicoloFetcher.class.getName());
    private static final String DB_FILE_PATH = "identifier.sqlite";
    private final List<Veicolo> veicoli = new ArrayList<>();

    public VeicoloFetcher() {
        loadVeicoliFromDatabase();
    }

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
                        codice,
                        resultSet.getString("MARCA"),
                        resultSet.getDouble("FREQUENZA"),
                        resultSet.getString("COLORE"),
                        resultSet.getString("URL_IMMAGINE"),
                        resultSet.getString("COLLISIONE"));
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

    public Veicolo getVeicoloPerMarca(String marca) {
        for (Veicolo veicolo : veicoli) {
            if (veicolo.getMarca().equals(marca)) {
                return veicolo;
            }
        }
        return null;
    }
}