package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class Enemy extends DynamicObject {

    public Enemy(int x, int y, int speed) {
        super(x, y, 15, 15, speed);
    }
    
    @Override
    public Object[] getData() {
        return new Object[] {
            posX, posY, width, height,
            Color.BLUE,
            Color.BLACK,
            "OVAL"
        };
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
    public void applyEffectTo(Player player, Level level) {
        player.receiveDamageFrom(this);
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