package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class Wall extends StaticObject {

    public Wall(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    /**
     * Dibuja la pared en pantalla.
     * 
     * @param g2d Objeto gráfico usado para renderizar
     */
    @Override
    public void render(Graphics2D g2d) {
 
        g2d.setColor(new Color(50, 50, 50));
        g2d.fillRect(posX, posY, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(posX, posY, width, height);
    }
    
    /**
     * Indica que la pared bloquea el movimiento.
     * 
     * @return true porque la pared impide el paso
     */
    @Override
    public boolean blocksMovement() {
        return true;
    }
    
    /**
     * Maneja la colisión entre el jugador y la pared.
     * 
     * Usa la posición anterior del jugador para determinar desde qué lado
     * chocó contra la pared y lo reposiciona fuera de ella.
     * También detiene la velocidad correspondiente al eje de colisión.
     * 
     * @param p Jugador que colisiona con la pared
     * @param l Nivel actual
     */
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