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

    public Enemy createEnemy(String type, int x, int y, String pattern) {

        Enemy enemy = new Enemy(x, y, 5);

        // 🔥 Movimiento básico (sin Strategy todavía)
        if (pattern.equalsIgnoreCase("VERTICAL")) {
            enemy.setVelocityY(3);
        } else if (pattern.equalsIgnoreCase("HORIZONTAL")) {
            enemy.setVelocityX(3);
        }

        return enemy;
    }

    public Wall createWall(int x, int y, int w, int h) {
        return new Wall(x, y, w, h);
    }

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