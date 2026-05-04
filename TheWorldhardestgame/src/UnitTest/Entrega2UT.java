package UnitTest;

import Domain.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;

public class Entrega2UT {

    @Test
    public void coinShouldIncrementCoinsOnlyOnce() {
        Level level = new Level(60);
        Player player = new Player(0, 0, null);
        Coin coin = new Coin(0, 0);

        coin.handleCollision(player, level);
        coin.handleCollision(player, level);

        assertEquals(1, level.getCoinsCollected());
        assertTrue(coin.isCollected());
        assertFalse(coin.isVisible());
    }

    @Test
    public void lifeSourceShouldActivateShieldAndAvoidOneDeath() {
        Level level = new Level(60);
        Player player = new Player(0, 0, null);
        LifeSource life = new LifeSource(0, 0);

        life.handleCollision(player, level);
        player.die();

        assertEquals(0, player.getDeaths());

        player.die();

        assertEquals(1, player.getDeaths());
    }

    @Test
    public void checkpointShouldSetPlayerSpawnAtCenteredPosition() {
        Level level = new Level(60);
        Player player = new Player(0, 0, null);
        CheckpointZone checkpoint = new CheckpointZone(100, 100, 40, 40);

        checkpoint.handleCollision(player, level);

        player.setPosX(300);
        player.setPosY(300);
        player.die();

        assertEquals(110, player.getPosX());
        assertEquals(110, player.getPosY());
    }

    @Test
    public void finalZoneShouldNotCompleteLevelIfRequiredObjectsRemain() {
        Level level = new Level(60);
        Player player = new Player(0, 0, null);
        FinalZone finalZone = new FinalZone(0, 0, 50, 50);
        Coin coin = new Coin(10, 10);

        level.addGameObject(coin);

        finalZone.handleCollision(player, level);

        assertFalse(level.isLevelCompleted());
    }

    @Test
    public void finalZoneShouldCompleteLevelWhenAllRequiredObjectsAreCollected() {
        Level level = new Level(60);
        Player player = new Player(0, 0, null);
        FinalZone finalZone = new FinalZone(0, 0, 50, 50);
        Coin coin = new Coin(10, 10);

        level.addGameObject(coin);

        coin.handleCollision(player, level);
        finalZone.handleCollision(player, level);

        assertTrue(level.isLevelCompleted());
    }

    @Test
    public void wallShouldBlockMovement() {
        Wall wall = new Wall(100, 100, 50, 50);

        assertTrue(wall.blocksMovement());
    }

    @Test
    public void wallShouldStopPlayerComingFromLeft() {
        Player player = new Player(90, 110, null);
        Wall wall = new Wall(110, 100, 50, 50);
        Level level = new Level(60);

        player.setVelocityX(20);
        player.update();

        wall.handleCollision(player, level);

        assertEquals(90, player.getPosX());
        assertEquals(0, player.getVelocityX());
    }

    @Test
    public void playerShouldRespawnAfterDeath() {
        Player player = new Player(50, 60, null);

        player.setPosX(200);
        player.setPosY(300);
        player.die();

        assertEquals(50, player.getPosX());
        assertEquals(60, player.getPosY());
        assertEquals(1, player.getDeaths());
    }

    @Test
    public void playerMovementShouldUpdateVelocityCorrectly() {
        Player player = new Player(0, 0, null);

        player.updateMovement(true, false, false, true);

        assertEquals(5, player.getVelocityX());
        assertEquals(-5, player.getVelocityY());
    }

    @Test
    public void playerShouldChangeBorderColor() {
        Player player = new Player(0, 0, null);

        player.setBorderColor(Color.BLUE);

        assertEquals(Color.BLUE, player.getBorderColor());
    }

    @Test
    public void enemyShouldKillPlayerOnCollision() {
        Level level = new Level(60);
        Player player = new Player(0, 0, null);
        Enemy enemy = new Enemy(0, 0, 5);

        enemy.handleCollision(player, level);

        assertEquals(1, player.getDeaths());
    }

    @Test
    public void enemyShouldInvertVelocityX() {
        Enemy enemy = new Enemy(0, 0, 5);

        enemy.setVelocityX(3);
        enemy.invertX();

        assertEquals(-3, enemy.getVelocityX());
    }

    @Test
    public void enemyShouldInvertVelocityY() {
        Enemy enemy = new Enemy(0, 0, 5);

        enemy.setVelocityY(3);
        enemy.invertY();

        assertEquals(-3, enemy.getVelocityY());
    }

    @Test
    public void factoryShouldCreateInitialZone() {
        LevelFactory factory = new LevelFactory();

        Zone zone = factory.createZone("INITIAL", 0, 0, 50, 50);

        assertTrue(zone instanceof InitialZone);
    }

    @Test
    public void factoryShouldCreateCheckpointZoneIgnoringCase() {
        LevelFactory factory = new LevelFactory();

        Zone zone = factory.createZone("checkpoint", 0, 0, 50, 50);

        assertTrue(zone instanceof CheckpointZone);
    }

    @Test
    public void factoryShouldCreateCoin() {
        LevelFactory factory = new LevelFactory();

        Collectable collectable = factory.createCollectible("COIN_YELLOW", 0, 0);

        assertTrue(collectable instanceof Coin);
    }

    @Test
    public void factoryShouldCreateLifeSource() {
        LevelFactory factory = new LevelFactory();

        Collectable collectable = factory.createCollectible("LIFE_SOURCE", 0, 0);

        assertTrue(collectable instanceof LifeSource);
    }

    @Test
    public void factoryShouldCreateVerticalEnemy() {
        LevelFactory factory = new LevelFactory();

        Enemy enemy = factory.createEnemy("NORMAL", 0, 0, "VERTICAL");

        assertEquals(0, enemy.getVelocityX());
        assertEquals(3, enemy.getVelocityY());
    }

    @Test
    public void factoryShouldCreateHorizontalEnemy() {
        LevelFactory factory = new LevelFactory();

        Enemy enemy = factory.createEnemy("NORMAL", 0, 0, "HORIZONTAL");

        assertEquals(3, enemy.getVelocityX());
        assertEquals(0, enemy.getVelocityY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void factoryShouldThrowExceptionForInvalidZone() {
        LevelFactory factory = new LevelFactory();

        factory.createZone("BAD_ZONE", 0, 0, 50, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void factoryShouldThrowExceptionForInvalidCollectable() {
        LevelFactory factory = new LevelFactory();

        factory.createCollectible("BAD_COLLECTABLE", 0, 0);
    }
}