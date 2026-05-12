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
    private int previousX;
    private int previousY;

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
        previousX = posX;
        previousY = posY;
        super.move();
    }
    
    public void returnToPreviousPosition() {
        posX = previousX;
        posY = previousY;
    }

    @Override
    public void handleCollision(Player p, Level l) {
        // El jugador no necesita reaccionar al chocar consigo mismo.
    }
    
    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    /**
     * Dibuja al jugador en pantalla.
     * 
     * El jugador se representa como un rectángulo con color de relleno
     * y borde definido.
     * 
     * @param g2d Objeto gráfico usado para renderizar
     */
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillRect(posX, posY, width, height);

        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(posX, posY, width, height);
    }

    /**
     * Maneja la muerte o daño del jugador.
     * 
     * Si la skin actual puede resistir un golpe:
     * - Reduce la velocidad.
     * - Cambia la skin a Blinky.
     * 
     * Si el jugador tiene escudo:
     * - Consume el escudo y evita la muerte.
     * 
     * Si no tiene protección:
     * - Incrementa el contador de muertes.
     * - Reaparece en el punto de spawn.
     */
    public void die() {
    	
    	if (currentSkin != null && currentSkin.canResistHit()) {
            speed = (int)(speed * 0.7);
            currentSkin = new Blinky();
            fillColor = currentSkin.getColor();
            return;
        }
    	
        if (shield) {
            shield = false;
            return;
        }

        deaths++;
        respawn();
    }
    
    /**
     * Actualiza el movimiento del jugador según las teclas presionadas.
     * 
     * Reinicia la velocidad y luego modifica los ejes X e Y
     * dependiendo de las direcciones activas.
     * 
     * @param up true si se mueve hacia arriba
     * @param down true si se mueve hacia abajo
     * @param left true si se mueve hacia la izquierda
     * @param right true si se mueve hacia la derecha
     */
    public void updateMovement(boolean up, boolean down, boolean left, boolean right) {
        velocityX = 0;
        velocityY = 0;

        if (up) {
            velocityY -= speed;
        }

        if (down) {
            velocityY += speed;
        }

        if (left) {
            velocityX -= speed;
        }

        if (right) {
            velocityX += speed;
        }
    }
    
    public void activateShield() {
        shield = true;
    }
    
    public int getDeaths() {
        return deaths;
    }		

    /**
     * Hace reaparecer al jugador en su punto de spawn.
     * 
     * También detiene completamente su movimiento.
     */
    private void respawn() {
        posX = spawnX;
        posY = spawnY;
        velocityX = 0;
        velocityY = 0;
    }

    /**
     * Aplica una nueva skin al jugador.
     * 
     * La skin puede modificar:
     * - Color
     * - Velocidad
     * - Tamaño
     * 
     * Además, conserva el centro visual del jugador al cambiar de tamaño.
     * 
     * @param newSkin Nueva skin que se desea aplicar
     */
    public void applySkinEffect(Skin newSkin) {
        if (newSkin == null) {
            return;
        }

        int centerX = posX + width / 2;
        int centerY = posY + height / 2;

        currentSkin = newSkin;
        fillColor = newSkin.getColor();
        speed = newSkin.getSpeed();

        width = newSkin.getSize();
        height = newSkin.getSize();

        posX = centerX - width / 2;
        posY = centerY - height / 2;
    }

    /**
     * Establece la posición de reaparición del jugador.
     * 
     * @param x Nueva posición horizontal de spawn
     * @param y Nueva posición vertical de spawn
     */
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

    /**
     * Cambia el color del borde del jugador.
     * 
     * @param color nuevo color del borde
     */
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