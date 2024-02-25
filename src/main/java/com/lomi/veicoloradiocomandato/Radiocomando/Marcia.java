package com.lomi.veicoloradiocomandato.Radiocomando;

public class Marcia {
    private final int marciaAttuale;
    public Marcia() {
        this.marciaAttuale = 3;
    }

    public void cambiaMarcia() {
        switch (marciaAttuale) {
            case 1:
                // Logica per la marcia 1
                break;
            case 2:
                // Logica per la marcia 2
                break;
            case 3:
                // Logica per la marcia 3
                break;
            case 4:
                // Logica per la marcia 4
                break;
            case 5:
                // Logica per la marcia 5
                break;
            // Aggiungi altri casi a seconda del numero di marce
            default:
                // Logica per la marcia di default (es. 3)
                break;
        }
    }

    public int getMarciaAttuale() {
        return marciaAttuale;
    }
}
