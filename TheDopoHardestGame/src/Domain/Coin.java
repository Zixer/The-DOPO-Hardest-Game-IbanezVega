package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class Coin extends Collectable {

    public Coin(int x, int y) {
        super(x, y, 10, 10);
    }
    

    /**
     * Aplica el efecto cuando el jugador recoge la moneda.
     * 
     * - Incrementa el contador de monedas en el nivel
     * - Muestra un mensaje en consola
     * 
     * @param p El jugador que recoge la moneda
     * @param l El nivel actual
     */
    @Override
    protected void applyEffect(Player p, Level l) {
        l.incrementCoinsCollected();
        System.out.println("Moneda recolectada");
    }

    /**
     * Indica si esta moneda es necesaria para completar el nivel.
     * 
     * Retorna true mientras no haya sido recolectada.
     * 
     * @return true si la moneda no ha sido recogida, false en caso contrario
     */

    @Override
    public boolean isRequiredToFinish() {
        return !collected;
    }

    @Override
    public Object[] getData() {
        return new Object[] {
            posX, posY, width, height,
            Color.YELLOW,
            Color.BLACK,
            "OVAL"
        };
    }
}