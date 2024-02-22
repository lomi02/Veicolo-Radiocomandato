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

public class VeicoloFetcher {
    private static final Logger LOGGER = Logger.getLogger(VeicoloFetcher.class.getName());
    List<Veicolo> veicoli = new ArrayList<>();

    public VeicoloFetcher(String jsonFilePath) {
        try {

            String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("veicoli");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject veicoloJson = jsonArray.getJSONObject(i);
                String tipo = veicoloJson.getString("tipo");
                Veicolo veicolo = VeicoloFactory.creaVeicolo(tipo, veicoloJson);
                veicoli.add(veicolo);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Errore durante la lettura del file JSON", e);

        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il parsing del JSON", e);
        }
    }
}
