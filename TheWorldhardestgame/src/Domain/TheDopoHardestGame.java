package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

public class TheDopoHardestGame {

    private Level currentLevel;

    private Color player1Border;
    private Color player2Border;
    private Color player1Fill;
    private Color player2Fill;

    public TheDopoHardestGame() {
        currentLevel = null;
    }

    public void loadLevel(int levelNumber) {
        currentLevel = LevelLoader.loadLevel(levelNumber);

        if (currentLevel != null) {

            // 🔥 Aplicar colores al cargar nivel
            if (currentLevel.getPlayer(0) != null) {
                if (player1Border != null) {
                    currentLevel.getPlayer(0).setBorderColor(player1Border);
                }
                if (player1Fill != null) {
                    currentLevel.getPlayer(0).setFillColor(player1Fill);
                }
            }

            if (currentLevel.getPlayer(1) != null) {
                if (player2Border != null) {
                    currentLevel.getPlayer(1).setBorderColor(player2Border);
                }
                if (player2Fill != null) {
                    currentLevel.getPlayer(1).setFillColor(player2Fill);
                }
            }
        }
    }

    public void update() {
        if (currentLevel != null) {
            currentLevel.update();
        }
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

    public void setPlayerFillColor(int playerNumber, Color color) {

        if (playerNumber == 1) {
            player1Fill = color;

            if (currentLevel != null && currentLevel.getPlayer(0) != null) {
                currentLevel.getPlayer(0).setFillColor(color);
            }

        } else if (playerNumber == 2) {
            player2Fill = color;

            if (currentLevel != null && currentLevel.getPlayer(1) != null) {
                currentLevel.getPlayer(1).setFillColor(color);
            }
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

    public Color getPlayerBorderColor(int playerNumber) {
        return (playerNumber == 1) ? player1Border : player2Border;
    }

    public Color getPlayerFillColor(int playerNumber) {
        return (playerNumber == 1) ? player1Fill : player2Fill;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}