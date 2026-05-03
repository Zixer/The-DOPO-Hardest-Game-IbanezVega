package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class Enemy extends DynamicObject {

    public Enemy(int x, int y, int speed) {
        super(x, y, 15, 15, speed);
    }

    @Override
    public void update() {
        super.move();
    }

    @Override
    public void handleCollision(Player p, Level l) {
        p.die();
        System.out.println("El jugador ha muerto por contacto con enemigo.");
    }

    @Override
    public void render(Graphics2D g2d) {

        // Enemigo clásico: bolita azul
        g2d.setColor(Color.BLUE);
        g2d.fillOval(posX, posY, width, height);

        // Borde para que resalte
        g2d.setColor(Color.BLACK);
        g2d.drawOval(posX, posY, width, height);
    }

    // ====================================================================
    // MOVIMIENTO
    // ====================================================================

    public void invertX() {
        this.velocityX = -this.velocityX;  
    }

    public void invertY() {
        this.velocityY = -this.velocityY; 
    }
    
    @Override
    public void handleCollisionWithObject(GameObject other, Level level) {
        if (other.blocksMovement()) {
            posX -= velocityX;
            posY -= velocityY;

            invertX();
            invertY();
        }
    }
}