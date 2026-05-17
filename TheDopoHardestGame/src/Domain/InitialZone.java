package Domain;

import java.awt.Graphics2D;
import java.awt.Color;

public class InitialZone extends Zone {

    public InitialZone(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.color = new Color(0, 255, 0); // verde
    }

    /**
     * Maneja la colisión entre el jugador y la zona inicial.
     * 
     * - Se calcula el centro de la zona.
     * - Se establece como nueva posición de respawn del jugador.
     * 
     * @param p El jugador que entra en la zona
     * @param l El nivel actual
     */
    @Override
    public void applyEffectTo(Player player, Level level) {
        player.setSpawnPosition(
            posX + (width / 2) - (player.getWidth() / 2),
            posY + (height / 2) - (player.getHeight() / 2)
        );
    }

}