package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

public class PlayZone extends StaticObject {

    public PlayZone(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    
    @Override
    public void handleCollision(Player p, Level l) {
    }

    /**
     * Dibuja la zona jugable en pantalla.
     * 
     * Se representa como un patrón tipo tablero (cuadriculado),
     * alternando dos tonos claros para dar textura visual.
     * 
     * @param g2d Objeto gráfico utilizado para renderizar
     */
    @Override
    public void render(Graphics2D g2d) {
        int tile = 40;

        for (int i = posX; i < posX + width; i += tile) {
            for (int j = posY; j < posY + height; j += tile) {
                if (((i + j) / tile) % 2 == 0) {
                    g2d.setColor(new Color(245, 245, 255));
                } else {
                    g2d.setColor(new Color(225, 225, 250));
                }

                g2d.fillRect(i, j, tile, tile);
            }
        }

        g2d.setColor(Color.BLACK);
        g2d.drawRect(posX, posY, width, height);
    }
}