package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

public class Wall extends StaticObject {

    public Wall(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public Object[] getData() {
        return new Object[] {
            posX, posY, width, height,
            new Color(40, 70, 180),
            Color.BLACK,
            "RECT"
        };
    }

    @Override
    public boolean blocksMovement() {
        return true;
    }

    @Override
    public void applyEffectTo(Player player, Level level) {
        player.blockAgainst(this);
    }

}