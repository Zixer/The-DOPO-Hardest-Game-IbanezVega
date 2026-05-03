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

        Player player = new Player(70, 260, new Blinky());
        level.addPlayer(player);

        level.addGameObject(new InitialZone(40, 230, 100, 100));
        level.addGameObject(new FinalZone(720, 230, 100, 100));

        level.addGameObject(new Wall(250, 120, 40, 360));
        level.addGameObject(new Wall(530, 120, 40, 360));

        level.addGameObject(new Coin(390, 260));
        level.addGameObject(new Coin(430, 260));

        Enemy enemy1 = new Enemy(360, 180, 3);
        enemy1.setVelocityY(3);
        level.addGameObject(enemy1);

        Enemy enemy2 = new Enemy(460, 360, 3);
        enemy2.setVelocityY(-3);
        level.addGameObject(enemy2);

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