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

    /**
     * Dibuja el objeto en pantalla.
     * 
     * Se representa como:
     * - Un círculo verde (indica vida/protección)
     * - Un borde negro para visibilidad
     * 
     * @param g2d Objeto gráfico utilizado para renderizar
     */
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(0, 200, 0));
        g2d.fillOval(posX, posY, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(posX, posY, width, height);
    }
}