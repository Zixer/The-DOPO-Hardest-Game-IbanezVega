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

    public abstract void render(Graphics2D g2d);

    public boolean isVisible() {
        return true;
    }

    public boolean isRequiredToFinish() {
        return false;
    }
    
    public boolean blocksMovement() {
        return false;
    }

    public void handleCollisionWithObject(GameObject other, Level level) {
        // Por defecto no hace nada
    }

    public abstract void handleCollision(Player p, Level l);

    public int getPosX() { return posX; }
    
    public void setPosX(int posX) { this.posX = posX; }

    public int getPosY() { return posY; }
    
    public void setPosY(int posY) { this.posY = posY; }

    public int getWidth() { return width; }
    
    public int getHeight() { return height; }
}