package Domain;

import java.awt.Color;

public class Blinky implements Skin {

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public String getName() {
        return "Blinky";
    }

    @Override
    public int getSpeed() {
        return 5; // velocidad normal
    }

    @Override
    public int getSize() {
        return 20; // tamaño normal
    }

    @Override
    public boolean canResistHit() {
        return false;
    }
}