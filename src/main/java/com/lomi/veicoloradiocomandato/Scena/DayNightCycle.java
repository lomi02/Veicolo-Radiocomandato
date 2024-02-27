package com.lomi.veicoloradiocomandato.Scena;

import javafx.scene.shape.Rectangle;

public abstract class DayNightCycle {
    protected Rectangle rectangle;

    protected static final int START_TIME = 0;
    protected static final int DAY_TIME = 10;
    protected static final int TWILIGHT_TIME = 15;
    protected static final int NIGHT_TIME = 25;
    protected static final int END_TIME = 30;

    protected abstract void initializeDayNightCycle();
}