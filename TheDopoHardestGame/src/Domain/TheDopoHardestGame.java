package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

public class TheDopoHardestGame {

    private Level currentLevel;

    public TheDopoHardestGame() {
        currentLevel = null;
    }

    /**
     * Carga un nivel según su número.
     * 
     * - Inicializa el nivel usando LevelLoader.
     * - Aplica skins y colores a los jugadores si existen.
     * 
     * @param levelNumber número del nivel a cargar
     */
    public void loadLevel(int levelNumber, String mode,String player1Skin,Color player1Border,String player2Skin,Color player2Border) {
        currentLevel = LevelLoader.loadLevel(levelNumber, mode);

        Player p1 = currentLevel.getPlayer(0);
        Player p2 = currentLevel.getPlayer(1);

        if (p1 != null) {
            p1.applySkinEffect(createSkin(player1Skin));
            p1.setBorderColor(player1Border);
        }

        if (p2 != null) {
            p2.applySkinEffect(createSkin(player2Skin));
            p2.setBorderColor(player2Border);
        }
    }

    /**
     * Actualiza el estado del juego.
     * 
     * Delega la actualización al nivel actual.
     */
    public void update() {
        if (currentLevel != null) {
            currentLevel.update();
        }
    }
    
    /**
     * Crea una instancia de Skin según el nombre.
     * 
     * @param skinName nombre del skin
     * @return instancia de Skin
     */
    private Skin createSkin(String skinName) {
        if (skinName.equalsIgnoreCase("INKY")) {
            return new InkySkin();
        }

        if (skinName.equalsIgnoreCase("CLYDE")) {
            return new ClydeSkin();
        }

        return new Blinky();
    }
    
    /**
     * Actualiza el movimiento de un jugador según inputs.
     */
    public void updatePlayerMovement(int playerNumber, boolean up, boolean down, boolean left, boolean right) {
        Player player = getPlayer(playerNumber);

        if (player != null) {
            player.updateMovement(up, down, left, right);
        }
    }
    
    public void movePlayerUp(int playerNumber) {
        Player player = getPlayer(playerNumber);
        if (player != null) {
            player.moveUp();
        }
    }

    public void movePlayerDown(int playerNumber) {
        Player player = getPlayer(playerNumber);
        if (player != null) {
            player.moveDown();
        }
    }

    public void movePlayerLeft(int playerNumber) {
        Player player = getPlayer(playerNumber);
        if (player != null) {
            player.moveLeft();
        }
    }

    public void movePlayerRight(int playerNumber) {
        Player player = getPlayer(playerNumber);
        if (player != null) {
            player.moveRight();
        }
    }

    public void stopPlayerVerticalMovement(int playerNumber) {
        Player player = getPlayer(playerNumber);
        if (player != null) {
            player.stopVerticalMovement();
        }
    }

    public void stopPlayerHorizontalMovement(int playerNumber) {
        Player player = getPlayer(playerNumber);
        if (player != null) {
            player.stopHorizontalMovement();
        }
    }

    private Player getPlayer(int playerNumber) {
        if (currentLevel == null) {
            return null;
        }

        return currentLevel.getPlayer(playerNumber - 1);
    }
    
    public boolean isTimeUp() {
        if (currentLevel == null) return false;
        return currentLevel.isTimeUp();
    }
    
    public int getCoinsCollected() {
        if (currentLevel == null) {
            return 0;
        }

        return currentLevel.getCoinsCollected();
    }

    public int getDeaths() {
        if (currentLevel == null) {
            return 0;
        }

        return currentLevel.getDeaths();
    }

    public int getTimeRemaining() {
        if (currentLevel == null) {
            return 0;
        }

        return currentLevel.getTimeRemaining();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
    
    public Object[][] getObjectsData() {
        if (currentLevel == null) {
            return new Object[0][0];
        }

        return currentLevel.getObjectsData();
    }
    
    public void saveGame(int levelNumber, String mode, String path) throws java.io.IOException {
        if (currentLevel != null) {
            SaveManager.saveGame(currentLevel, levelNumber, mode, path);
        }
    }
    
    public int loadSavedGame(String path) throws Exception {

        LoadedGame loaded = SaveManager.loadGame(path);

        loadLevel(
            loaded.levelNumber,
            loaded.mode,
            loaded.player1.skin,
            loaded.player1.border,
            loaded.player2 != null ? loaded.player2.skin : "BLINKY",
            loaded.player2 != null ? loaded.player2.border : java.awt.Color.BLACK
        );

        Player p1 = currentLevel.getPlayer(0);
        p1.setPosX(loaded.player1.x);
        p1.setPosY(loaded.player1.y);

        if (loaded.player2 != null) {
            Player p2 = currentLevel.getPlayer(1);
            p2.setPosX(loaded.player2.x);
            p2.setPosY(loaded.player2.y);
        }

        return loaded.levelNumber;
    }
}