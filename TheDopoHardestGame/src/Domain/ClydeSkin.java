package Domain;

import java.awt.Color;

public class ClydeSkin implements Skin {

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public String getName() {
        return "Clyde";
    }

    @Override
    public int getSpeed() {
        return 5; // normal al inicio
    }

    @Override
    public int getSize() {
        return 20; // tamaño normal
    }

    @Override
    public boolean canResistHit() {
        return true; 
    }
}