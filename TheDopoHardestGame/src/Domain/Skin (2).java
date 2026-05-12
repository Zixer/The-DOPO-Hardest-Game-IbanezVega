package Domain;

import java.awt.Color;

public interface Skin {

    Color getColor();

    String getName();

    default void applyEffect(Player p) {
        // vacío por defecto
    }
    
    int getSpeed();

    int getSize();

    boolean canResistHit();
}