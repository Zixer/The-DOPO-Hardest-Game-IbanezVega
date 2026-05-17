package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class LifeSource extends Collectable {

    public LifeSource(int x, int y) {
        super(x, y, 15, 15);
    }

    /**
     * Aplica el efecto al ser recolectado por el jugador.
     * 
     * - Activa un escudo en el jugador.
     * - Muestra un mensaje en consola.
     * 
     * @param p El jugador que recoge el objeto
     * @param l El nivel actual
     */
    @Override
    protected void applyEffect(Player p, Level l) {
        p.activateShield();
        System.out.println("Protección activada");
    }

   

    @Override
    public Object[] getData() {
        return new Object[] {
            posX, posY, width, height,
            new Color(0, 200, 0),
            Color.BLACK,
            "OVAL"
        };
    }
}