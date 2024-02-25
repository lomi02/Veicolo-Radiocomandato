package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Scena.GameFieldInterface;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Radiocomando {
    private Command manubrioCommand;
    private Command acceleratoreCommand;
    private Command frenoCommand;

    private boolean isAnimating = false;

    public Radiocomando(GameFieldInterface gameFieldInterface, VeicoloManager veicoloManager, Marcia marcia) {
        marcia = new Marcia();
        EventHandler<KeyEvent> keyEventHandler = event -> {
            if (!isAnimating) {
                switch (event.getCode()) {
                    case W:
                        //setAcceleratoreCommand(new AcceleratoreCommand(veicolo, marcia));
                        break;
                    case S:
                        //setFrenoCommand(new FrenoCommand(veicolo, marcia));
                        break;
                    case A:
                        setManubrioCommand(new ManubrioCommand(veicoloManager, "sinistra"));
                        manubrio();
                        break;
                    case D:
                        setManubrioCommand(new ManubrioCommand(veicoloManager, "destra"));
                        manubrio();
                        break;
                }
            }
        };
        gameFieldInterface.getScene().setOnKeyPressed(keyEventHandler);
    }

    public void setManubrioCommand(Command manubrioCommand) {
        this.manubrioCommand = manubrioCommand;
    }

    public void setAcceleratoreCommand(Command acceleratoreCommand) {
        this.acceleratoreCommand = acceleratoreCommand;
    }

    public void setFrenoCommand(Command frenoCommand) {
        this.frenoCommand = frenoCommand;
    }

    public void manubrio() {
        if (!isAnimating) {
            isAnimating = true;
            manubrioCommand.execute();
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> isAnimating = false);
            pause.play();
        }
    }


    public void acceleratore() {
        acceleratoreCommand.execute();
    }

    public void freno() {
        frenoCommand.execute();
    }
}
