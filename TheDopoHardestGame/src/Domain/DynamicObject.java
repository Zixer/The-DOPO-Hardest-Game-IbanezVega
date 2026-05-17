package Domain;

public abstract class DynamicObject extends GameObject {

    protected int velocityX;
    protected int velocityY;
    protected int speed;

    public DynamicObject(int x, int y, int w, int h, int speed) {
        super(x, y, w, h);
        this.speed = speed;
        this.velocityX = 0;
        this.velocityY = 0;
    }
     
    /**
     * Actualiza el estado del objeto en cada ciclo del juego.
     * 
     * Por defecto, solo ejecuta el movimiento.
     */
    @Override
    public void update() {
        move();
    }

    /**
     * Aplica el movimiento completo del objeto en ambos ejes.
     */
    protected void move() {
        posX += velocityX;
        posY += velocityY;
    }
    
    protected void moveX() {
        posX += velocityX;
    }

    protected void moveY() {
        posY += velocityY;
    }

    public void moveUp() {
        velocityY = -speed;
    }

    public void moveDown() {
        velocityY = speed;
    }

    public void moveLeft() {
        velocityX = -speed;
    }

    public void moveRight() {
        velocityX = speed;
    }

    public void stopHorizontalMovement() {
        velocityX = 0;
    }

    public void stopVerticalMovement() {
        velocityY = 0;
    }

    public void stopMovement() {
        velocityX = 0;
        velocityY = 0;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}