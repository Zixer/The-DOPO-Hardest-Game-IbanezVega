package Domain;

import java.awt.Color;

public class FinalZone extends Zone {

    public FinalZone(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.color = new Color(0, 255, 0);
    }

    @Override
    public void handleCollision(Player p, Level l) {
        if (l.allRequiredObjectsCollected()) {
            l.setLevelCompleted(true);
            System.out.println("¡Nivel completado!");
        } else {
            System.out.println("Aún faltan objetivos por recoger");
        }
    }
}