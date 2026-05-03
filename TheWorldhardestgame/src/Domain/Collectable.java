package Domain;

/**
 * Representa objetos recolectables como monedas o vidas.
 */
public abstract class Collectable extends StaticObject {

    protected boolean collected;

    public Collectable(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.collected = false;
    }

    @Override
    public void handleCollision(Player p, Level l) {
        if (!collected) {
            applyEffect(p, l);
            collected = true;
        }
    }

    protected abstract void applyEffect(Player p, Level l);

    @Override
    public boolean isVisible() {
        return !collected;
    }

    public boolean isCollected() {
        return collected;
    }
}