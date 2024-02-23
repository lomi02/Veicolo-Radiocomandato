package com.lomi.veicoloradiocomandato.Gioco;

import javafx.scene.control.ChoiceDialog;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleSelector {
    private static final Logger LOGGER = Logger.getLogger(VehicleSelector.class.getName());

    public Optional<String> selectVehicle() {
        return getVehicle();
    }

    static Optional<String> getVehicle() {
        try {
            List<String> choices = Arrays.asList("Ferrari", "Mercedes", "Porsche");
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Ferrari", choices);
            dialog.setTitle("Veicolo Radiocomandato");
            dialog.setHeaderText("Scegli il veicolo:");
            dialog.setContentText("Seleziona il tuo veicolo:");

            return dialog.showAndWait();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to get the vehicle choice.", e);
            return Optional.empty();
        }
    }
}