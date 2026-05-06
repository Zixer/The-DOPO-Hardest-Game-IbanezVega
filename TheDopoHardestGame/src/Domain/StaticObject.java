package Domain;

/**
 * Representa objetos estáticos del juego (no se mueven).
 * Ejemplos: Wall, Zone.
 */
public abstract class StaticObject extends GameObject {

    public StaticObject(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void update() {
        // Los objetos estáticos no cambian en el tiempo
    }
}