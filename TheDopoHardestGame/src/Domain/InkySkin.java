package Domain;

import java.awt.Color;

public class InkySkin implements Skin {

    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    @Override
    public String getName() {
        return "Inky";
    }

    @Override
    public int getSpeed() {
        return 8; 
    }

    @Override
    public int getSize() {
        return 30; 
    }

    @Override
    public boolean canResistHit() {
        return false;
    }
}