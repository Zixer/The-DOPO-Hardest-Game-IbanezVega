package Domain;

public class LevelLoader {

    private LevelFactory factory;

    public LevelLoader() {
        this.factory = new LevelFactory();
    }

    public Level parseFile(String path) {
        Level level = new Level(60);
        return level;
    }

    public static Level loadLevel(int levelNumber) {
        if (levelNumber == 1) {
            return createLevelOne();
        } else if (levelNumber == 2) {
            return createLevelTwo();
        } else {
            return createLevelThree();
        }
    }

    private static Level createLevelOne() {
        Level level = new Level(60);

        // Jugador
        Player player = new Player(80, 290, new Blinky());
        level.addPlayer(player);

        // Zonas seguras
        level.addGameObject(new InitialZone(40, 250, 120, 120));
        level.addGameObject(new FinalZone(720, 250, 120, 120));

        // Bordes externos para encerrar el mapa
        level.addGameObject(new Wall(0, 35, 900, 25));      // arriba
        level.addGameObject(new Wall(0, 600, 900, 25));     // abajo
        level.addGameObject(new Wall(0, 35, 25, 590));      // izquierda
        level.addGameObject(new Wall(875, 35, 25, 590));    // derecha

        // Paredes internas estilo nivel 1
        level.addGameObject(new Wall(160, 35, 25, 215));
        level.addGameObject(new Wall(160, 370, 25, 255));

        level.addGameObject(new Wall(715, 35, 25, 215));
        level.addGameObject(new Wall(715, 370, 25, 255));

        // Pasillo central
        level.addGameObject(new Wall(185, 250, 530, 25));
        level.addGameObject(new Wall(185, 345, 530, 25));

        // Monedas en el centro
        level.addGameObject(new Coin(390, 292));
        level.addGameObject(new Coin(430, 292));
        level.addGameObject(new Coin(470, 292));

        // Enemigos azules moviéndose verticalmente en el corredor
        Enemy enemy1 = new Enemy(260, 285, 3);
        enemy1.setVelocityY(3);
        level.addGameObject(enemy1);

        Enemy enemy2 = new Enemy(340, 330, 3);
        enemy2.setVelocityY(-3);
        level.addGameObject(enemy2);

        Enemy enemy3 = new Enemy(420, 285, 3);
        enemy3.setVelocityY(3);
        level.addGameObject(enemy3);

        Enemy enemy4 = new Enemy(500, 330, 3);
        enemy4.setVelocityY(-3);
        level.addGameObject(enemy4);

        Enemy enemy5 = new Enemy(580, 285, 3);
        enemy5.setVelocityY(3);
        level.addGameObject(enemy5);

        return level;
    }

    private static Level createLevelTwo() {
        Level level = new Level(70);

        Player player = new Player(70, 80, new Blinky());
        level.addPlayer(player);

        level.addGameObject(new InitialZone(40, 50, 100, 100));
        level.addGameObject(new FinalZone(720, 450, 100, 100));
        level.addGameObject(new CheckpointZone(380, 250, 80, 80));

        level.addGameObject(new Wall(220, 0, 40, 420));
        level.addGameObject(new Wall(580, 180, 40, 420));

        level.addGameObject(new Coin(350, 120));
        level.addGameObject(new Coin(450, 380));
        level.addGameObject(new LifeSource(410, 270));

        Enemy enemy = new Enemy(320, 300, 4);
        enemy.setVelocityX(4);
        level.addGameObject(enemy);

        return level;
    }

    private static Level createLevelThree() {
        Level level = new Level(80);

        Player player = new Player(70, 500, new Blinky());
        level.addPlayer(player);

        level.addGameObject(new InitialZone(40, 460, 100, 100));
        level.addGameObject(new FinalZone(720, 40, 100, 100));

        level.addGameObject(new Coin(250, 400));
        level.addGameObject(new Coin(400, 280));
        level.addGameObject(new Coin(550, 160));

        Enemy enemy1 = new Enemy(300, 200, 5);
        enemy1.setVelocityX(5);
        level.addGameObject(enemy1);

        Enemy enemy2 = new Enemy(500, 350, 5);
        enemy2.setVelocityY(-5);
        level.addGameObject(enemy2);

        return level;
    }
}