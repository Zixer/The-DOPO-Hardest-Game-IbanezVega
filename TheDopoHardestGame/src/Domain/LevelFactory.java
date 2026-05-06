package Domain;

public class LevelFactory {

    public Zone createZone(String type, int x, int y, int w, int h) {
        switch (type.toUpperCase()) {
            case "INITIAL":
                return new InitialZone(x, y, w, h);
            case "CHECKPOINT":
                return new CheckpointZone(x, y, w, h);
            case "FINAL":
                return new FinalZone(x, y, w, h);
            default:
                throw new IllegalArgumentException("Tipo de zona inválido: " + type);
        }
    }

    /**
     * Crea un objeto recolectable según el tipo.
     * 
     * Tipos soportados:
     * - COIN_YELLOW
     * - LIFE_SOURCE
     * 
     * @param type Tipo de colectable
     * @param x Posición horizontal
     * @param y Posición vertical
     * @return instancia de Collectable
     * @throws IllegalArgumentException si el tipo es inválido
     */
    public Collectable createCollectible(String type, int x, int y) {
        switch (type.toUpperCase()) {
            case "COIN_YELLOW":
                return new Coin(x, y);
            case "LIFE_SOURCE":
                return new LifeSource(x, y);
            default:
                throw new IllegalArgumentException("Tipo de colectable inválido: " + type);
        }
    }

    /**
     * Crea un enemigo con un patrón de movimiento básico.
     * 
     * Patrones soportados:
     * - VERTICAL
     * - HORIZONTAL
     * 
     * @param type Tipo de enemigo (actualmente no usado para variación)
     * @param x Posición horizontal
     * @param y Posición vertical
     * @param pattern Patrón de movimiento
     * @return instancia de Enemy configurada
     */
    public Enemy createEnemy(String type, int x, int y, String pattern) {

        Enemy enemy = new Enemy(x, y, 5);

        if (pattern.equalsIgnoreCase("VERTICAL")) {
            enemy.setVelocityY(3);
        } else if (pattern.equalsIgnoreCase("HORIZONTAL")) {
            enemy.setVelocityX(3);
        }

        return enemy;
    }

    /**
     * Crea una pared que bloquea el movimiento.
     * 
     * @param x Posición horizontal
     * @param y Posición vertical
     * @param w Ancho
     * @param h Alto
     * @return instancia de Wall
     */
    public Wall createWall(int x, int y, int w, int h) {
        return new Wall(x, y, w, h);
    }

    /**
     * Crea un jugador con un skin inicial.
     * 
     * Tipos soportados:
     * - BLINKY
     * - INKY
     * 
     * Si el tipo no es reconocido, se usa BLINKY por defecto.
     * 
     * @param x Posición horizontal inicial
     * @param y Posición vertical inicial
     * @param initialSkin Nombre del skin
     * @return instancia de Player con el skin correspondiente
     */
    public Player createPlayer(int x, int y, String initialSkin) {

        Skin skin;

        switch (initialSkin.toUpperCase()) {
            case "BLINKY":
                skin = new Blinky();
                break;
            case "INKY":
                skin = new InkySkin();
                break;
            default:
                skin = new Blinky();
        }

        return new Player(x, y, skin);
    }
}