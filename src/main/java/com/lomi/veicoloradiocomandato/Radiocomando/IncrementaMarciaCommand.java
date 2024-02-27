package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;

/**
 * La classe IncrementaMarciaCommand implementa l'interfaccia Command
 * e rappresenta un comando per incrementare la marcia su un radiocomando.
 */
public class IncrementaMarciaCommand implements Command {

    private final Radiocomando radiocomando;
    private final ObstacleManager obstacleManager;

    /**
     * Costruttore della classe IncrementaMarciaCommand.
     *
     * @param radiocomando    Il radiocomando al quale verrà delegato l'incremento della marcia.
     * @param obstacleManager Il gestore degli ostacoli al quale verrà richiesto di sospendere e riprendere la generazione.
     */
    public IncrementaMarciaCommand(Radiocomando radiocomando, ObstacleManager obstacleManager) {
        this.radiocomando = radiocomando;
        this.obstacleManager = obstacleManager;
    }

    /**
     * Esegue il comando di incremento della marcia sul radiocomando,
     * interrompendo temporaneamente la generazione di ostacoli.
     */
    @Override
    public void execute() {
        obstacleManager.stopObstacleGeneration();
        radiocomando.incrementaMarcia();
        obstacleManager.startObstacleGeneration();
    }
}