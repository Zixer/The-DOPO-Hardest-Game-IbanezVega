package Domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveManager {

    public static void saveGame(
        Level level,
        int levelNumber,
        String mode,
        String path
    ) throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(path));

        writer.println("LEVEL=" + levelNumber);
        writer.println("MODE=" + mode);
        writer.println("TIME_REMAINING=" + level.getTimeRemaining());
        writer.println("COINS_COLLECTED=" + level.getCoinsCollected());

        int playerNumber = 1;

        for (Player player : level.getPlayers()) {
            writer.println(
                "PLAYER," +
                playerNumber + "," +
                player.getPosX() + "," +
                player.getPosY() + "," +
                player.getCurrentSkin().getName() + "," +
                colorToText(player.getBorderColor()) + "," +
                player.getDeaths()
            );

            playerNumber++;
        }

        writer.close();
    }
    
    public static LoadedGame loadGame(String path) throws Exception {

        java.io.BufferedReader br =
            new java.io.BufferedReader(
                new java.io.FileReader(path)
            );

        int levelNumber = 1;
        String mode = "NORMAL";

        PlayerData player1 = null;
        PlayerData player2 = null;

        String line;

        while ((line = br.readLine()) != null) {

            line = line.trim();

            if (line.startsWith("LEVEL=")) {
                levelNumber =
                    Integer.parseInt(
                        line.split("=")[1]
                    );
            }

            else if (line.startsWith("MODE=")) {
                mode = line.split("=")[1];
            }

            else if (line.startsWith("PLAYER")) {

                String[] parts = line.split(",");

                PlayerData data = new PlayerData();

                data.playerNumber =
                    Integer.parseInt(parts[1]);

                data.x =
                    Integer.parseInt(parts[2]);

                data.y =
                    Integer.parseInt(parts[3]);

                data.skin = parts[4];

                data.border =
                    textToColor(parts[5]);

                data.deaths =
                    Integer.parseInt(parts[6]);

                if (data.playerNumber == 1) {
                    player1 = data;
                } else {
                    player2 = data;
                }
            }
        }

        br.close();

        LoadedGame game = new LoadedGame();

        game.levelNumber = levelNumber;
        game.mode = mode;
        game.player1 = player1;
        game.player2 = player2;

        return game;
    }
    
    private static java.awt.Color textToColor(String text) {

        switch (text) {

            case "RED":
                return java.awt.Color.RED;

            case "BLUE":
                return java.awt.Color.BLUE;

            case "GREEN":
                return java.awt.Color.GREEN;

            case "ORANGE":
                return java.awt.Color.ORANGE;

            case "CYAN":
                return java.awt.Color.CYAN;

            case "PINK":
                return java.awt.Color.PINK;

            case "MAGENTA":
                return java.awt.Color.MAGENTA;

            default:
                return java.awt.Color.BLACK;
        }
    }

    private static String colorToText(java.awt.Color color) {
        if (color.equals(java.awt.Color.RED)) {
            return "RED";
        }

        if (color.equals(java.awt.Color.BLUE)) {
            return "BLUE";
        }

        if (color.equals(java.awt.Color.GREEN)) {
            return "GREEN";
        }

        if (color.equals(java.awt.Color.ORANGE)) {
            return "ORANGE";
        }

        if (color.equals(java.awt.Color.CYAN)) {
            return "CYAN";
        }

        if (color.equals(java.awt.Color.PINK)) {
            return "PINK";
        }

        if (color.equals(java.awt.Color.MAGENTA)) {
            return "MAGENTA";
        }

        return "BLACK";
    }
}