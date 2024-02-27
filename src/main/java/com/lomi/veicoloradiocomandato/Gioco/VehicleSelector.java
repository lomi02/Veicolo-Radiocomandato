package com.lomi.veicoloradiocomandato.Gioco;

import javafx.scene.control.ChoiceDialog;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe VehicleSelector gestisce la selezione del veicolo radiocomandato.
 */
public class VehicleSelector {
    private static final Logger LOGGER = Logger.getLogger(VehicleSelector.class.getName());

    /**
     * Consente all'utente di selezionare un veicolo tra le opzioni disponibili.
     *
     * @return L'opzione selezionata dall'utente (o vuota se la selezione fallisce).
     */
    public Optional<String> selectVehicle() {
        return getVehicle();
    }

    /**
     * Mostra una finestra di dialogo per la selezione del veicolo.
     *
     * @return L'opzione selezionata dall'utente (o vuota se la selezione fallisce).
     */
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