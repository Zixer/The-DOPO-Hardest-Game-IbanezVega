package Domain;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;

public class Level {

    private List<GameObject> gameObjects;
    private List<Player> players;
    private int timeLimit;
    private boolean levelCompleted;
    private int coinsCollected;
    private long startTime;
    private boolean timeUp;

    public Level(int timeLimit) {
        this.gameObjects = new ArrayList<>();
        this.players = new ArrayList<>();
        this.timeLimit = timeLimit;
        this.levelCompleted = false;
        this.coinsCollected = 0;
        this.startTime = System.currentTimeMillis();
        this.timeUp = false;
    }

    public void update() {
        if (levelCompleted) {
            return;
        }

        if (getTimeRemaining() <= 0) {
            timeUp = true;
            return;
        }

        for (GameObject obj : gameObjects) {
            obj.update();
        }

        checkObjectCollisions();

        for (Player player : players) {
            player.update();
            checkCollisions(player);
        }
    }
    
    public boolean isTimeUp() {
        return timeUp;
    }
    
    private void checkObjectCollisions() {
        for (GameObject movingObject : gameObjects) {
            for (GameObject staticObject : gameObjects) {
                if (movingObject != staticObject && isColliding(movingObject, staticObject)) {
                    movingObject.handleCollisionWithObject(staticObject, this);
                }
            }
        }
    }
    
    public void incrementCoinsCollected() {
        coinsCollected++;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public int getDeaths() {
        int totalDeaths = 0;

        for (Player player : players) {
            totalDeaths += player.getDeaths();
        }

        return totalDeaths;
    }

    public int getTimeRemaining() {
        long elapsedMillis = System.currentTimeMillis() - startTime;
        int elapsedSeconds = (int) (elapsedMillis / 1000);
        int remaining = timeLimit - elapsedSeconds;

        if (remaining < 0) {
            return 0;
        }

        return remaining;
    }

    public void render(Graphics2D g2d) {
        for (GameObject obj : gameObjects) {
            if (obj.isVisible()) {
                obj.render(g2d);
            }
        }

        for (Player player : players) {
            if (player.isVisible()) {
                player.render(g2d);
            }
        }
    }

    private void checkCollisions(Player player) {
        for (GameObject obj : gameObjects) {
            if (isColliding(player, obj)) {
                obj.handleCollision(player, this);
            }
        }
    }

    private boolean isColliding(GameObject a, GameObject b) {
        return a.getPosX() < b.getPosX() + b.getWidth()
            && a.getPosX() + a.getWidth() > b.getPosX()
            && a.getPosY() < b.getPosY() + b.getHeight()
            && a.getPosY() + a.getHeight() > b.getPosY();
    }

    public boolean allRequiredObjectsCollected() {
        for (GameObject obj : gameObjects) {
            if (obj.isRequiredToFinish()) {
                return false;
            }
        }
        return true;
    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

    public void removeGameObject(GameObject obj) {
        gameObjects.remove(obj);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayer() {
        if (players.isEmpty()) {
            return null;
        }
        return players.get(0);
    }

    public Player getPlayer(int index) {
        if (index < 0 || index >= players.size()) {
            return null;
        }
        return players.get(index);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> allObjects = new ArrayList<>(gameObjects);
        allObjects.addAll(players);
        return allObjects;
    }

    public boolean isLevelCompleted() {
        return levelCompleted;
    }

    public void setLevelCompleted(boolean status) {
        this.levelCompleted = status;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}