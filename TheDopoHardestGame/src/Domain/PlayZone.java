package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

public class PlayZone extends Zone {

    public PlayZone(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    
    @Override
    public void applyEffectTo(Player player, Level level) {
    }
    
    @Override
    public Object[] getData() {
        return new Object[] {
            posX, posY, width, height,
            new Color(235, 235, 255),
            new Color(0, 0, 0, 0),
            "CHECKER"
        };
    }
}