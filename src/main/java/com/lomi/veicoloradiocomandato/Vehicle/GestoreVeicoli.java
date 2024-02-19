package com.lomi.veicoloradiocomandato.Vehicle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestoreVeicoli {
    private static final Logger LOGGER = Logger.getLogger(GestoreVeicoli.class.getName());
    List<Veicolo> veicoli = new ArrayList<>();

    public GestoreVeicoli(String jsonFilePath) {
        try {

            // Legge tutto il contenuto del file JSON in una stringa
            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            // Crea un oggetto JSONObject dal contenuto del file
            JSONObject jsonObject = new JSONObject(content);

            // Ottiene l'array di veicoli dal JSONObject
            JSONArray jsonArray = jsonObject.getJSONArray("veicoli");

            // Itera su ogni elemento dell'array di veicoli
            for (int i = 0; i < jsonArray.length(); i++) {

                // Ottiene l'oggetto veicolo corrente come JSONObject
                JSONObject veicoloJson = jsonArray.getJSONObject(i);

                // Ottiene il tipo di veicolo dal JSONObject del veicolo
                String tipo = veicoloJson.getString("tipo");

                // Crea un nuovo veicolo usando la factory di veicoli
                Veicolo veicolo = VeicoloFactory.creaVeicolo(tipo, veicoloJson);

                // Aggiunge il veicolo alla lista di veicoli
                veicoli.add(veicolo);
            }
        } catch (IOException e) {

            // Registra un errore se si verifica un'eccezione durante la lettura del file JSON
            LOGGER.log(Level.SEVERE, "Errore durante la lettura del file JSON", e);

        } catch (JSONException e) {

            // Registra un errore se si verifica un'eccezione durante il parsing del JSON
            LOGGER.log(Level.SEVERE, "Errore durante il parsing del JSON", e);
        }
    }
}
