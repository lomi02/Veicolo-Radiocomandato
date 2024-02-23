package com.lomi.veicoloradiocomandato.Vehicle;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VeicoloManager {
    private static final Logger LOGGER = Logger.getLogger(VeicoloManager.class.getName());
    private final GridPane road;
    private ImageView veicoloView;

    public VeicoloManager(GridPane road) {
        this.road = road;
    }
    public void spawnVeicolo(String veicoloScelto) {
        try {
            VeicoloFetcher veicoloFetcher = new VeicoloFetcher();

            Veicolo veicolo = veicoloFetcher.getVeicoloPerMarca(veicoloScelto);
            if (veicolo != null) {
                this.veicoloView = veicolo.getVehicleImage();
                placeVehicleInView(veicoloView);
            } else {
                LOGGER.log(Level.SEVERE, "Error during vehicle spawn. Not found vehicle: " + veicoloScelto);
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "NullPointerException caught. One or more objects were not initialized properly.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during vehicle spawn.", e);
        }
    }
    private void placeVehicleInView(ImageView veicoloView) {
        road.getChildren().add(veicoloView);
        GridPane.setColumnIndex(veicoloView, 3);
        double position = 700 - veicoloView.getImage().getHeight() * 1.2;
        veicoloView.setTranslateY(position);
    }
    public ImageView getVehicleNode() {
        return veicoloView;
    }
}