package Domain;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class Player extends DynamicObject {
	
	private int deaths;
    private Skin currentSkin;
    private boolean shield;

    private int spawnX;
    private int spawnY;

    private Color borderColor;
    private Color fillColor;

    public Player(int x, int y, Skin initialSkin) {
        super(x, y, 20, 20, 5);
        
        this.shield = false;
        this.deaths = 0;
        this.currentSkin = initialSkin;
        this.spawnX = x;
        this.spawnY = y;

        this.borderColor = Color.BLACK;

        if (initialSkin != null) {
            this.fillColor = initialSkin.getColor();
        } else {
            this.fillColor = Color.RED;
        }
    }

    @Override
    public void update() {
        super.move();
    }

    @Override
    public void handleCollision(Player p, Level l) {
        // El jugador no necesita reaccionar al chocar consigo mismo.
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillRect(posX, posY, width, height);

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(posX, posY, width, height);
    }

    public void die() {
        if (shield) {
            shield = false;
            return;
        }

        deaths++;
        respawn();
    }
    
    public void activateShield() {
        shield = true;
    }
    
    public int getDeaths() {
        return deaths;
    }		

    private void respawn() {
        posX = spawnX;
        posY = spawnY;
        velocityX = 0;
        velocityY = 0;
    }

    public void applySkinEffect(Skin newSkin) {
        currentSkin = newSkin;

        if (newSkin != null) {
            fillColor = newSkin.getColor();
        }
    }

    public void setSpawnPosition(int x, int y) {
        spawnX = x;
        spawnY = y;
    }

    public int getLives() {
        return 0;
    }

    public void setLives(int lives) {
        //
    }

    public Skin getCurrentSkin() {
        return currentSkin;
    }

    public void setBorderColor(Color color) {
        if (color != null) {
            borderColor = color;
        }
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setFillColor(Color color) {
        if (color != null) {
            fillColor = color;
        }
    }

    public Color getFillColor() {
        return fillColor;
    }
}