package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class Coin extends Collectable {

    public Coin(int x, int y) {
        super(x, y, 10, 10);
    }

    @Override
    protected void applyEffect(Player p, Level l) {
        l.incrementCoinsCollected();
        System.out.println("Moneda recolectada");
    }

    @Override
    public boolean isRequiredToFinish() {
        return !collected;
    }

    @Override
    public void render(Graphics2D g2d) {

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(posX, posY, width, height);

        g2d.setColor(Color.ORANGE);
        g2d.drawOval(posX, posY, width, height);
    }
}