package com.lomi.veicoloradiocomandato.Ostacoli;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;

public abstract class Obstacle {
    protected ImageView imageView;

    public Obstacle(String imagePath) throws FileNotFoundException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lomi/veicoloradiocomandato/obstacle.fxml"));

        try {
            this.imageView = loader.load();
            InputStream is = getClass().getResourceAsStream(imagePath);
            if (is == null)
                throw new FileNotFoundException("Resource not found: " + imagePath);
            Image image = new Image(is);
            this.imageView.setImage(image);
        } catch (IOException e) {
            throw new FileNotFoundException("FXML file not found: " + "/com/lomi/veicoloradiocomandato/obstacle.fxml");
        }
    }

    public ImageView getImageView() {
        return imageView;
    }
}