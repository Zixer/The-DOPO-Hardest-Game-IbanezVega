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

    /**
     * Maneja la colisión con el jugador.
     * 
     * Si el objeto no ha sido recolectado:
     * - Aplica su efecto específico.
     * - Cambia su estado a recolectado.
     * 
     * @param p El jugador que colisiona con el objeto
     * @param l El nivel actual
     */
    @Override
    public void handleCollision(Player p, Level l) {
        if (!collected) {
            applyEffect(p, l);
            collected = true;
        }
    }

    /**
     * Método abstracto que define el efecto del objeto al ser recolectado.
     * 
     * Cada subclase (por ejemplo, Coin) debe implementar su propio efecto.
     * 
     * @param p El jugador que recoge el objeto
     * @param l El nivel actual
     */
    protected abstract void applyEffect(Player p, Level l);

    @Override
    public boolean isVisible() {
        return !collected;
    }

    public boolean isCollected() {
        return collected;
    }
}