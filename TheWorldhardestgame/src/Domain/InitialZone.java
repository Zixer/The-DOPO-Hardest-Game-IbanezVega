package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class InitialZone extends Zone {

    public InitialZone(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.color = new Color(0, 255, 0); // verde
    }

    @Override
    public void handleCollision(Player p, Level l) {
        // Centro de la zona como spawn
        p.setSpawnPosition(
            this.posX + (this.width / 2),
            this.posY + (this.height / 2)
        );
    }

    // 🔥 ESTE TE FALTABA
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