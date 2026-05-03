package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class Wall extends StaticObject {

    public Wall(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void render(Graphics2D g2d) {
        // Color de la pared (gris oscuro tipo juego)
        g2d.setColor(new Color(50, 50, 50));
        g2d.fillRect(posX, posY, width, height);

        // Borde para que se vea más claro
        g2d.setColor(Color.BLACK);
        g2d.drawRect(posX, posY, width, height);
    }
    
    @Override
    public boolean blocksMovement() {
        return true;
    }
    
    @Override
    public void handleCollision(Player p, Level l) {
        p.returnToPreviousPosition();
    }
}