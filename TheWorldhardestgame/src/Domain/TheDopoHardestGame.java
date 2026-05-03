package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

public class TheDopoHardestGame {

    private Level currentLevel;

    private Color player1Border;
    private Color player2Border;
    private String player1Skin = "BLINKY";
    private String player2Skin = "BLINKY";

    public TheDopoHardestGame() {
        currentLevel = null;
    }

    public void loadLevel(int levelNumber) {
        currentLevel = LevelLoader.loadLevel(levelNumber);

        if (currentLevel != null) {

            Player player1 = currentLevel.getPlayer(0);
            Player player2 = currentLevel.getPlayer(1);

            if (player1 != null) {
                player1.applySkinEffect(createSkin(player1Skin));

                if (player1Border != null) {
                    player1.setBorderColor(player1Border);
                }
            }

            if (player2 != null) {
                player2.applySkinEffect(createSkin(player2Skin));

                if (player2Border != null) {
                    player2.setBorderColor(player2Border);
                }
            }
        }
    }

    public void update() {
        if (currentLevel != null) {
            currentLevel.update();
        }
    }
    
    public void setPlayerSkin(int playerNumber, String skinName) {
        if (playerNumber == 1) {
            player1Skin = skinName;
        } else if (playerNumber == 2) {
            player2Skin = skinName;
        }

        Player player = getPlayer(playerNumber);

        if (player != null) {
            player.applySkinEffect(createSkin(skinName));
        }
    }
    
    private Skin createSkin(String skinName) {
        if (skinName.equalsIgnoreCase("INKY")) {
            return new InkySkin();
        }

        if (skinName.equalsIgnoreCase("CLYDE")) {
            return new ClydeSkin();
        }

        return new Blinky();
    }

    public void render(Graphics2D g2d) {
        if (currentLevel != null) {
            currentLevel.render(g2d);
        }
    }

    public void setPlayerBorderColor(int playerNumber, Color color) {

        if (playerNumber == 1) {
            player1Border = color;

            if (currentLevel != null && currentLevel.getPlayer(0) != null) {
                currentLevel.getPlayer(0).setBorderColor(color);
            }

        } else if (playerNumber == 2) {
            player2Border = color;

            if (currentLevel != null && currentLevel.getPlayer(1) != null) {
                currentLevel.getPlayer(1).setBorderColor(color);
            }
        }
    }
    
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
    
    public void resetPlayerConfiguration() {
        player1Skin = "BLINKY";
        player2Skin = "BLINKY";

        player1Border = Color.BLACK;
        player2Border = Color.BLACK;
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

    public Color getPlayerBorderColor(int playerNumber) {
        return (playerNumber == 1) ? player1Border : player2Border;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}