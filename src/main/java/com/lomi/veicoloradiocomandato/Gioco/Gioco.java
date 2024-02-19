package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Application;
import javafx.stage.Stage;
public class Gioco extends Application {
    @Override
    public void start(Stage stage){
        stage.setTitle("Veicolo radiocomandato");
        new GameManager("src/main/java/com/lomi/veicoloradiocomandato/storage.json", stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}