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

    /**
     * Actualiza el estado general del nivel.
     * 
     * En cada ciclo:
     * - Verifica si el nivel ya terminó.
     * - Verifica si el tiempo se agotó.
     * - Actualiza los objetos del juego.
     * - Revisa colisiones entre objetos.
     * - Actualiza a los jugadores.
     * - Revisa colisiones del jugador.
     * - Resuelve colisiones con paredes.
     */
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
        
        checkPlayerCollisions();
    }
    
    private void checkPlayerCollisions() {
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player p1 = players.get(i);
                Player p2 = players.get(j);

                if (isColliding(p1, p2)) {
                    p1.applyEffectTo(p2, this);
                }
            }
        }
    }
    
    /**
     * Indica si el tiempo del nivel se agotó.
     * 
     * @return true si el tiempo llegó a cero, false en caso contrario
     */
    public boolean isTimeUp() {
        return timeUp;
    }
    
    /**
     * Revisa colisiones entre los objetos del nivel.
     * 
     * Permite que objetos dinámicos, como enemigos, reaccionen
     * al chocar con otros objetos, como paredes.
     */
    private void checkObjectCollisions() {
        for (GameObject movingObject : gameObjects) {
            for (GameObject staticObject : gameObjects) {
                if (movingObject != staticObject && isColliding(movingObject, staticObject)) {
                    movingObject.handleCollisionWithObject(staticObject, this);
                }
            }
        }
    }
    
    /**
     * Incrementa el contador de monedas recolectadas.
     */
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
    
    /**
     * Revisa las colisiones entre un jugador y todos los objetos del nivel.
     * 
     * Si hay colisión, el objeto ejecuta su comportamiento correspondiente.
     * 
     * @param player Jugador al que se le revisan las colisiones
     */
    private void checkCollisions(Player player) {
        for (GameObject obj : gameObjects) {
            if (isColliding(player, obj)) {
            	obj.applyEffectTo(player, this);
            }
        }
    }

    /**
     * Verifica si dos objetos están colisionando usando cajas rectangulares.
     * 
     * @param a Primer objeto
     * @param b Segundo objeto
     * @return true si los objetos se intersectan, false en caso contrario
     */
    private boolean isColliding(GameObject a, GameObject b) {
        return a.getPosX() < b.getPosX() + b.getWidth()
            && a.getPosX() + a.getWidth() > b.getPosX()
            && a.getPosY() < b.getPosY() + b.getHeight()
            && a.getPosY() + a.getHeight() > b.getPosY();
    }

    /**
     * Verifica si todos los objetos requeridos para terminar el nivel
     * ya fueron recolectados.
     * 
     * @return true si no quedan objetos obligatorios pendientes
     */
    public boolean allRequiredObjectsCollected() {
        for (GameObject obj : gameObjects) {
            if (obj.isRequiredToFinish()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Agrega un objeto al nivel.
     * 
     * @param obj Objeto que se desea agregar
     */
    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

    /**
     * Elimina un objeto del nivel.
     * 
     * @param obj Objeto que se desea eliminar
     */
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

    /**
     * Obtiene todos los objetos del nivel, incluyendo jugadores.
     * 
     * @return lista con objetos del juego y jugadores
     */
    public List<GameObject> getGameObjects() {
        List<GameObject> allObjects = new ArrayList<>(gameObjects);
        allObjects.addAll(players);
        return allObjects;
    }

    /**
     * Indica si el nivel fue completado.
     * 
     * @return true si el nivel está completado
     */
    public boolean isLevelCompleted() {
        return levelCompleted;
    }

    public void setLevelCompleted(boolean status) {
        this.levelCompleted = status;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
    
    public Object[][] getObjectsData() {
        ArrayList<Object[]> data = new ArrayList<Object[]>();

        for (GameObject obj : gameObjects) {
            if (obj.isVisible()) {
                data.add(obj.getData());
            }
        }

        for (Player player : players) {
            if (player.isVisible()) {
                data.add(player.getData());
            }
        }

        return data.toArray(new Object[data.size()][]);
    }
}