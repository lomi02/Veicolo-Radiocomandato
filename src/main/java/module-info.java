module com.lomi.veicoloradiocomandato {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.json;
    requires java.logging;

    opens com.lomi.veicoloradiocomandato to javafx.fxml;
    opens com.lomi.veicoloradiocomandato.Gioco to javafx.fxml, javafx.graphics;
}