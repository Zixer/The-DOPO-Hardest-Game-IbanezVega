package Domain;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Zone extends StaticObject {

    protected Color color; // 🔥 CAMBIO CLAVE

    public Zone(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public abstract void handleCollision(Player p, Level l);

    @Override
    public void update() {
        // Las zonas no se mueven
    }

    // 🔥 OPCIONAL: puedes dejar esto abstracto o implementarlo aquí
    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(posX, posY, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(posX, posY, width, height);
    }

    // Getter
    public Color getColor() {
        return color;
    }
}