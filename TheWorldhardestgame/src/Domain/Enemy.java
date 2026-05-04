package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class Enemy extends DynamicObject {

    public Enemy(int x, int y, int speed) {
        super(x, y, 15, 15, speed);
    }

    
    /**
     * Actualiza el estado del enemigo en cada ciclo del juego.
     * 
     * En este caso, simplemente se mueve según su velocidad actual.
     */
    @Override
    public void update() {
        super.move();
    }

    /**
     * Maneja la colisión con el jugador.
     * 
     * Al colisionar:
     * - El jugador muere.
     * - Se imprime un mensaje en consola.
     * 
     * @param p El jugador que colisiona con el enemigo
     * @param l El nivel actual
     */
    @Override
    public void handleCollision(Player p, Level l) {
        p.die();
        System.out.println("El jugador ha muerto por contacto con enemigo.");
    }

    /**
     * Dibuja el enemigo en pantalla.
     * 
     * Se representa como:
     * - Un círculo azul (cuerpo)
     * - Un borde negro más grande (efecto visual de sombra)
     * 
     * @param g2d Objeto gráfico utilizado para renderizar
     */
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillOval(posX - 4, posY - 4, width + 8, height + 8);

        g2d.setColor(Color.BLUE);
        g2d.fillOval(posX, posY, width, height);
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
    
    /**
     * Maneja la colisión con otros objetos del juego.
     * 
     * Si el objeto bloquea el movimiento:
     * - Se revierte el movimiento realizado.
     * - Se invierte la dirección en ambos ejes (rebote).
     * 
     * @param other Objeto con el que colisiona
     * @param level Nivel actual
     */
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