package Domain;

import java.awt.Color;

public class FinalZone extends Zone {

    public FinalZone(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.color = new Color(0, 255, 0);
    }

    /**
     * Maneja la colisión entre el jugador y la zona final.
     * 
     * - Si todos los objetos requeridos han sido recolectados:
     *      → Se marca el nivel como completado.
     * - Si no:
     *      → Se muestra un mensaje indicando que faltan objetivos.
     * 
     * @param p El jugador que entra en la zona final
     * @param l El nivel actual
     */
    @Override
    public void applyEffectTo(Player player, Level level) {
        if (level.allRequiredObjectsCollected()) {
            level.setLevelCompleted(true);
            System.out.println("¡Nivel completado!");
        } else {
            System.out.println("Aún faltan objetivos por recoger");
        }
    }
}