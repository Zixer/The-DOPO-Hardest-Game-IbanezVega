package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class LifeSource extends Collectable {

    public LifeSource(int x, int y) {
        super(x, y, 15, 15);
    }

    @Override
    protected void applyEffect(Player p, Level l) {
        p.activateShield();
        System.out.println("Protección activada");
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(0, 200, 0));
        g2d.fillOval(posX, posY, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(posX, posY, width, height);
    }
}