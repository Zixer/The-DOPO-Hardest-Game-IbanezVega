package Domain;

import java.awt.Graphics2D;

public abstract class GameObject {

    protected int posX;
    protected int posY;
    protected int width;
    protected int height;

    public GameObject(int x, int y, int w, int h) {
        this.posX = x;
        this.posY = y;
        this.width = w;
        this.height = h;
    }

    public abstract void update();

    /**
     * Método abstracto para dibujar el objeto en pantalla.
     * 
     * @param g2d Objeto gráfico utilizado para renderizar
     */
    public abstract void render(Graphics2D g2d);

    public boolean isVisible() {
        return true;
    }

    /**
     * Indica si este objeto es necesario para completar el nivel.
     * 
     * Por defecto, los objetos no son obligatorios.
     * 
     * @return true si es necesario para terminar el nivel
     */
    public boolean isRequiredToFinish() {
        return false;
    }
    
    public boolean blocksMovement() {
        return false;
    }

    /**
     * Maneja la colisión con otro objeto del juego.
     * 
     * Este método puede ser sobrescrito por subclases para definir
     * comportamientos específicos de interacción entre objetos.
     * 
     * @param other Objeto con el que colisiona
     * @param level Nivel actual
     */
    public void handleCollisionWithObject(GameObject other, Level level) {
        // Por defecto no hace nada
    }

    /**
     * Maneja la colisión con el jugador.
     * 
     * Este método debe ser implementado por todas las subclases.
     * 
     * @param p El jugador que colisiona con el objeto
     * @param l El nivel actual
     */
    public abstract void handleCollision(Player p, Level l);

    public int getPosX() { return posX; }
    
    public void setPosX(int posX) { this.posX = posX; }

    public int getPosY() { return posY; }
    
    public void setPosY(int posY) { this.posY = posY; }

    public int getWidth() { return width; }
    
    public int getHeight() { return height; }
}