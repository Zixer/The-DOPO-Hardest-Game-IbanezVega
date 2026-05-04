package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class InitialZone extends Zone {

    public InitialZone(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.color = new Color(0, 255, 0); // verde
    }

    /**
     * Maneja la colisión entre el jugador y la zona inicial.
     * 
     * - Se calcula el centro de la zona.
     * - Se establece como nueva posición de respawn del jugador.
     * 
     * @param p El jugador que entra en la zona
     * @param l El nivel actual
     */
    @Override
    public void handleCollision(Player p, Level l) {
        // Centro de la zona como spawn
        p.setSpawnPosition(
            this.posX + (this.width / 2),
            this.posY + (this.height / 2)
        );
    }

    /**
     * Dibuja la zona inicial en pantalla.
     * 
     * Se representa como:
     * - Un rectángulo verde (zona activa)
     * - Un borde negro para mejor visibilidad
     * 
     * @param g2d Objeto gráfico utilizado para renderizar
     */
    @Override
    public void render(Graphics2D g2d) {

        // Zona verde
        g2d.setColor(color);
        g2d.fillRect(posX, posY, width, height);

        // Borde para diferenciar
        g2d.setColor(Color.BLACK);
        g2d.drawRect(posX, posY, width, height);
    }
}