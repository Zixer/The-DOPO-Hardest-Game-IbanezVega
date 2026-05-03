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
}