package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class Wall extends StaticObject {

    public Wall(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void render(Graphics2D g2d) {
 
        g2d.setColor(new Color(50, 50, 50));
        g2d.fillRect(posX, posY, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(posX, posY, width, height);
    }
    
    @Override
    public boolean blocksMovement() {
        return true;
    }
    
    @Override
    public void handleCollision(Player p, Level l) {

        int previousRight = p.getPreviousX() + p.getWidth();
        int previousLeft = p.getPreviousX();
        int previousBottom = p.getPreviousY() + p.getHeight();
        int previousTop = p.getPreviousY();

        int wallLeft = this.posX;
        int wallRight = this.posX + this.width;
        int wallTop = this.posY;
        int wallBottom = this.posY + this.height;

        if (previousBottom <= wallTop) {
            p.setPosY(wallTop - p.getHeight());
            p.setVelocityY(0);
        }

        else if (previousTop >= wallBottom) {
            p.setPosY(wallBottom);
            p.setVelocityY(0);
        }

        else if (previousRight <= wallLeft) {
            p.setPosX(wallLeft - p.getWidth());
            p.setVelocityX(0);
        }

        else if (previousLeft >= wallRight) {
            p.setPosX(wallRight);
            p.setVelocityX(0);
        }
    }
}