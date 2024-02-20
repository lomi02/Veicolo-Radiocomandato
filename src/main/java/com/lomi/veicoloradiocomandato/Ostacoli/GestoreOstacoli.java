package com.lomi.veicoloradiocomandato.Ostacoli;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestoreOstacoli {
    private static final Logger LOGGER = Logger.getLogger(GestoreOstacoli.class.getName());
    private final List<Obstacle> ostacoli = new ArrayList<>();

    public GestoreOstacoli(String jsonFilePath) {
        loadObstaclesFromJSON(jsonFilePath);
    }

    private void loadObstaclesFromJSON(String jsonFilePath) {
        try (InputStream is = getClass().getResourceAsStream(jsonFilePath)) {
            assert is != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }

                JSONObject jsonObject = new JSONObject(content.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("ostacoli");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject ostacoloJson = jsonArray.getJSONObject(i);
                    String nome_src = ostacoloJson.getString("nome_src");
                    Obstacle ostacolo = ObstacleFactory.creaOstacolo(nome_src, ostacoloJson);
                    ostacoli.add(ostacolo);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading the JSON file", e);
        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Error parsing the JSON", e);
        }
    }

    public List<Obstacle> getObstacles() {
        return ostacoli;
    }
}