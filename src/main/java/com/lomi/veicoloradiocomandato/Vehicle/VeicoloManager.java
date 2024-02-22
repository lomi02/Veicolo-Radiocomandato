package com.lomi.veicoloradiocomandato.Vehicle;

import com.lomi.veicoloradiocomandato.Gioco.Road;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeicoloManager {
    private static final Logger LOGGER = Logger.getLogger(VeicoloManager.class.getName());
    private final GridPane road;
    private final List<Veicolo> veicoli;
    private final String veicoloScelto;
    private final Road roadController;

    public VeicoloManager(GridPane road, List<Veicolo> veicoli, String veicoloScelto, Road roadController) {
        this.road = road;
        this.veicoli = veicoli;
        this.veicoloScelto = veicoloScelto;
        this.roadController = roadController;
    }

    public void spawnVeicolo(String veicoloScelto) {
        try {
            VeicoloFetcher veicoloFetcher = new VeicoloFetcher();

            // Try-catch block around fetching vehicle and adding image
            try {
                Veicolo veicolo = veicoloFetcher.getVeicoloPerMarca(veicoloScelto);
                if (veicolo != null) {
                    ImageView veicoloView = veicolo.getVehicleImage();

                    // Position at the bottom of the central lane of the road
                    GridPane.setRowIndex(veicoloView, 0);
                    GridPane.setColumnIndex(veicoloView, 3);

                    // Add to the UI
                    road.getChildren().add(veicoloView);
                } else {
                    LOGGER.log(Level.SEVERE, "Error during vehicle spawn. Not found vehicle: " + veicoloScelto);
                }
            } catch (NullPointerException e) {
                LOGGER.log(Level.SEVERE, "NullPointerException caught. One or more objects were not initialized properly.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during vehicle spawn.", e);
        }
    }
}