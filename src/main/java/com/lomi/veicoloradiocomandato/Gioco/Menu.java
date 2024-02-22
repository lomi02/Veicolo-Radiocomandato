package com.lomi.veicoloradiocomandato.Gioco;

import javafx.scene.control.ChoiceDialog;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Menu {

    // Metodo per visualizzare il menu di selezione del veicolo
    public static Optional<String> showVehicleSelection() {
        List<String> choices = Arrays.asList("Ferrari", "Mercedes", "Porsche");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Ferrari", choices);
        dialog.setTitle("Veicolo Radiocomandato");
        dialog.setHeaderText("Scegli il veicolo:");
        dialog.setContentText("Seleziona il tuo veicolo:");

        return dialog.showAndWait();
    }
}