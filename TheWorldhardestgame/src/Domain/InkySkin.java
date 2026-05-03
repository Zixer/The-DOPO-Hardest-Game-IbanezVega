package Domain;

import java.awt.Color;

/**
 
Representa la skin azul del jugador.
Implementa el contrato de Skin para integrarse con el sistema de monedas.*/
public class InkySkin implements Skin {

    @Override
    public Color getColor() {
        // Retorna el color cian característico de Inky
        return Color.CYAN;
    }

    @Override
    public String getName() {
        return "Inky";
    }
}