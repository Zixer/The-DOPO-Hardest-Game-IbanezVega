package Domain;

import java.awt.Color;

public class CheckpointZone extends Zone {

    public CheckpointZone(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.color = new Color(170, 255, 170); // verde claro
    }

    @Override
    public void handleCollision(Player p, Level l) {

        // 🔥 Centrar correctamente al jugador
        int centerX = this.posX + (this.width / 2) - (p.getWidth() / 2);
        int centerY = this.posY + (this.height / 2) - (p.getHeight() / 2);

        p.setSpawnPosition(centerX, centerY);

        // 🔥 efecto visual: cambia a verde fuerte cuando se activa
        this.color = new Color(0, 255, 0);
    }
}