package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Zone extends StaticObject {

    protected Color color; 

    public Zone(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public abstract void applyEffectTo(Player player, Level level);

    @Override
    public void update() {
        // Las zonas no se mueven
    }

    public Object[] getData() {
        return new Object[] {
            posX, posY, width, height,
            color,
            new Color(0, 0, 0, 0),
            "RECT"
        };
    }
    
    // Getter
    public Color getColor() {
        return color;
    }
}