package Domain;

import java.awt.Color;

public class CheckpointZone extends Zone {

    public CheckpointZone(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.color = new Color(170, 255, 170); 
    }

    /**
     * Maneja la colisión entre el jugador y la zona de checkpoint.
     * 
     * Cuando el jugador entra en esta zona:
     * 1. Se calcula el centro de la zona.
     * 2. Se ajusta la posición para centrar al jugador.
     * 3. Se guarda esta posición como nuevo punto de respawn.
     * 4. Se cambia el color de la zona para indicar activación.
     * 
     * @param p El jugador que colisiona con la zona
     * @param l El nivel actual (no se usa directamente aquí, pero se mantiene por consistencia)
     */
    @Override
    public void applyEffectTo(Player player, Level level) {
        int centerX = posX + (width / 2) - (player.getWidth() / 2);
        int centerY = posY + (height / 2) - (player.getHeight() / 2);

        player.setSpawnPosition(centerX, centerY);
        color = new Color(0, 255, 0);
    }

}