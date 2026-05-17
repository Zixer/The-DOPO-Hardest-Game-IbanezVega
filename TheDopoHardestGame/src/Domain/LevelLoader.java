package Domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelLoader {

	private LevelFactory factory;
	
    public LevelLoader() {
    	this.factory = new LevelFactory();
    }

    public Level parseFile(String path) {
        Level level = new Level(60);
        return level;
    }

    public static Level loadLevel(int levelNumber, String mode) {
        if (levelNumber == 1) {
            return createLevelOne(mode);
        } else if (levelNumber == 2) {
            return createLevelTwo(mode);
        } else {
            return createLevelThree(mode);
        }
    }

    private static Level createLevelOne(String mode) {
        Level level = new Level(60);

        // Jugador
        Player player1 = new Player(200, 315, new Blinky());
        level.addPlayer(player1);

        if (mode.equals("PVSP")) {
            Player player2 = new Player(780, 315, new Blinky());
            level.addPlayer(player2);
        }
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

    private static Level createLevelTwo(String mode) {
    	
        Level level = new Level(60);
        try {
            BufferedReader br = new BufferedReader(
                new FileReader("data/levels/level1.txt")
            );
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("TIME=")) {
                    level = new Level(Integer.parseInt(line.split("=")[1]));
                    continue;
                }
                String[] parts = line.split(",");
                switch (parts[0]) {
                    case "PLAYER":
                        Player player1 = new Player(
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            new Blinky()
                        );
                        level.addPlayer(player1);
                        if (mode.equals("PVSP")) {
                            Player player2 = new Player(
                                800,
                                315,
                                new Blinky()
                            );

                            level.addPlayer(player2);
                        }
                        break;
                    case "PLAYZONE":
                        level.addGameObject(new PlayZone(
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4])
                        ));
                        break;
                    case "INITIAL_ZONE":
                        level.addGameObject(new InitialZone(
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4])
                        ));
                        break;
                    case "FINAL_ZONE":
                        level.addGameObject(new FinalZone(
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4])
                        ));
                        break;
                    case "WALL":
                        level.addGameObject(new Wall(
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4])
                        ));
                        break;
                    case "COIN":
                        level.addGameObject(new Coin(
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2])
                        ));
                        break;
                    case "ENEMY":
                        Enemy enemy = new Enemy(
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            Math.abs(Integer.parseInt(parts[4]))
                        );
                        if (parts[3].equals("VERTICAL")) {
                            enemy.setVelocityY(Integer.parseInt(parts[4]));
                        } else if (parts[3].equals("HORIZONTAL")) {
                            enemy.setVelocityX(Integer.parseInt(parts[4]));
                        }
                        level.addGameObject(enemy);
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error cargando archivo del nivel");
            e.printStackTrace();
        }
        return level;
    }

    private static Level createLevelThree(String mode) {
        Level level = new Level(60);

        // Jugador
        Player player1 = new Player(200, 315, new Blinky());
        level.addPlayer(player1);

        if (mode.equals("PVSP")) {
            Player player2 = new Player(800, 315, new Blinky());
            level.addPlayer(player2);
        }

        // Fondo jugable tipo original
        
        level.addGameObject(new PlayZone(255, 265, 80, 200));  // cuello izquierdo
        level.addGameObject(new PlayZone(335, 265, 335, 155)); // corredor central
        level.addGameObject(new PlayZone(670, 225, 85, 195));  // cuello derecho
        

        // Zonas seguras verdes
        level.addGameObject(new InitialZone(130, 220, 125, 245));
        level.addGameObject(new FinalZone(755, 220, 120, 245));

        // Paredes invisibles para colisión externa
        
        //paredes izquierda
        level.addGameObject(new Wall(125, 215, 5, 250));
        level.addGameObject(new Wall(125, 215, 130, 5));
        level.addGameObject(new Wall(125, 465, 215, 5));
        level.addGameObject(new Wall(255, 215, 5, 205));
        //zona media
        level.addGameObject(new Wall(335, 415, 5, 50));
        level.addGameObject(new Wall(335, 415, 420, 5));
        //parte arriba
        level.addGameObject(new Wall(335, 415, 335, 5));
        level.addGameObject(new Wall(255, 265, 415, 5));
        level.addGameObject(new Wall(665, 220, 5, 45));
        
        // derecha
        level.addGameObject(new Wall(665, 220, 210, 5));
        level.addGameObject(new Wall(875, 220, 5, 245));
        level.addGameObject(new Wall(750, 465, 130, 5));
        level.addGameObject(new Wall(750, 270, 5, 200));

        // Moneda central
        level.addGameObject(new Coin(485, 335));

        // Enemigos
        Enemy enemy1 = new Enemy(430, 275, 3);
        enemy1.setVelocityY(3);
        level.addGameObject(enemy1);

        Enemy enemy2 = new Enemy(560, 315, 3);
        enemy2.setVelocityY(3);
        level.addGameObject(enemy2);

        Enemy enemy3 = new Enemy(430, 365, 3);
        enemy3.setVelocityY(-3);
        level.addGameObject(enemy3);

        Enemy enemy4 = new Enemy(560, 400, 3);
        enemy4.setVelocityY(-3);
        level.addGameObject(enemy4);

        return level;
    }
}