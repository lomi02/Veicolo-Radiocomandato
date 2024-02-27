package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;

/**
 * La classe DecrementaMarciaCommand implementa l'interfaccia Command
 * e rappresenta un comando per decrementare la marcia su un radiocomando.
 */
public class DecrementaMarciaCommand implements Command {

    private final Radiocomando radiocomando;
    private final ObstacleManager obstacleManager;

    /**
     * Costruttore della classe DecrementaMarciaCommand.
     *
     * @param radiocomando    Il radiocomando al quale verrà delegato il decremento della marcia.
     * @param obstacleManager Il gestore degli ostacoli al quale verrà richiesto di sospendere e riprendere la generazione.
     */
    public DecrementaMarciaCommand(Radiocomando radiocomando, ObstacleManager obstacleManager) {
        this.radiocomando = radiocomando;
        this.obstacleManager = obstacleManager;
    }

    /**
     * Esegue il comando di decremento della marcia sul radiocomando,
     * interrompendo temporaneamente la generazione di ostacoli.
     */
    @Override
    public void execute() {
        obstacleManager.stopObstacleGeneration();
        radiocomando.decrementaMarcia();
        obstacleManager.startObstacleGeneration();
    }
}