package Presentation;

import Domain.TheDopoHardestGame;

import javax.swing.*;
import java.awt.*;

public class TheWorldsHardestGameGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private TheDopoHardestGame game;
    private GamePanel gamePanel;
    private BorderColorPanel borderColorPanel;
    private ColorSelectionPanel colorSelectionPanel;

    private int currentPlayer = 1;
    private int selectedLevel;
    private String modeInProgress;
    
    private Color player1Border;
    private Color player2Border;

    private String player1Skin;
    private String player2Skin;

    public TheWorldsHardestGameGUI() {
        prepareElements();
    }

    private void prepareElements() {
        setTitle("THE DOPO'S HARDEST GAME");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        game = new TheDopoHardestGame();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        gamePanel = new GamePanel(this);
        borderColorPanel = new BorderColorPanel(this);
        colorSelectionPanel = new ColorSelectionPanel(this);

        mainPanel.add(new MenuPanel(this), "menu");
        mainPanel.add(new InstructionsPanel(this), "instructions");
        mainPanel.add(new ModePanel(this), "mode");
        mainPanel.add(borderColorPanel, "borderColor");
        mainPanel.add(colorSelectionPanel, "colorSelection");
        mainPanel.add(new LevelSelectorPanel(this), "levelSelector");
        mainPanel.add(gamePanel, "game");
        mainPanel.add(new PausePanel(this), "pause");

        add(mainPanel);
    }

    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);

        if (screenName.equals("borderColor")) {
            borderColorPanel.updateTitle();
        }

        if (screenName.equals("colorSelection")) {
            colorSelectionPanel.updateTitle();
        }
    }	

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void startPvSPColorSelection() {
        currentPlayer = 1;
        showScreen("borderColor");
    }
    
    public void pauseGame() {
        gamePanel.stopGame();
        showScreen("pause");
    }

    public void resumeGame() {
        showScreen("game");
        gamePanel.startGame();
    }

    public void retryLevel() {
        selectLevel(selectedLevel);
    }

    public void setBorderColor(Color color) {
        if (currentPlayer == 1) {
            player1Border = color;

            if (modeInProgress.equals("PVSP")) {
                currentPlayer = 2;
                showScreen("borderColor");
            } else {
                showScreen("colorSelection");
            }

        } else {
            player2Border = color;
            currentPlayer = 1;
            showScreen("colorSelection");
        }
    }

    public void setPlayerSkin(String skinName) {
        if (currentPlayer == 1) {
            player1Skin = skinName;

            if (modeInProgress.equals("PVSP")) {
                currentPlayer = 2;
                showScreen("colorSelection");
            } else {
                showScreen("levelSelector");
            }

        } else {
            player2Skin = skinName;
            currentPlayer = 1;
            showScreen("levelSelector");
        }
    }

    public void selectLevel(int level) {
        selectedLevel = level;

        try {
            game.loadLevel(selectedLevel,modeInProgress,player1Skin,player1Border,player2Skin,player2Border);

            showScreen("game");
            gamePanel.startGame();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "No se pudo cargar el nivel " + selectedLevel
            );
        }
    }
    
    public void startColorSelectionForMode(String mode) {
        modeInProgress = mode;
        currentPlayer = 1;
        showScreen("borderColor");
    }
    
    public void handleTimeUp() {
        int option = JOptionPane.showOptionDialog(
            this,
            "TIME'S UP!",
            "Game Over",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[]{"RETRY"},
            "RETRY"
        );

        if (option == 0) {
            selectLevel(selectedLevel);
        }
    }
    
    public void renderGame(Graphics2D g2d) {
        Object[][] objects = game.getObjectsData();

        for (Object[] obj : objects) {
            drawObjectData(g2d, obj);
        }
    }

    private void drawObjectData(Graphics2D g2d, Object[] obj) {

        int x = (Integer) obj[0];
        int y = (Integer) obj[1];
        int w = (Integer) obj[2];
        int h = (Integer) obj[3];

        Color fill = (Color) obj[4];
        Color border = (Color) obj[5];
        
        String shape = (String) obj[6];
        
        // Objetos invisibles
        if (fill.getAlpha() == 0 && border.getAlpha() == 0) {
            return;
        }
        // PlayZone tipo tablero
        if (shape.equals("CHECKER")) {
            drawCheckerZone(g2d, x, y, w, h, border);
            return;
        }
        // Relleno
        g2d.setColor(fill);
        if (shape.equals("OVAL")) {
            g2d.fillOval(x, y, w, h);
        } else {
            g2d.fillRect(x, y, w, h);
        }
        // Borde más grueso
        g2d.setColor(border);
        g2d.setStroke(new BasicStroke(4));
        if (shape.equals("OVAL")) {
            g2d.drawOval(x, y, w, h);
        } else {
            g2d.drawRect(x, y, w, h);
        }
    }

    private void drawCheckerZone(Graphics2D g2d, int x, int y, int w, int h, Color border) {

        int tile = 40;

        for (int row = y; row < y + h; row += tile) {
            for (int col = x; col < x + w; col += tile) {

                int tileW = Math.min(tile, x + w - col);
                int tileH = Math.min(tile, y + h - row);

                if (((col + row) / tile) % 2 == 0) {
                    g2d.setColor(new Color(245, 245, 255));
                } else {
                    g2d.setColor(new Color(225, 225, 250));
                }
                g2d.fillRect(col, row, tileW, tileH);
            }
        }

        // SOLO dibuja borde si no es transparente
        if (border.getAlpha() > 0) {
            g2d.setColor(border);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(x, y, w, h);
        }
    }
    
    public void updatePlayerMovement(boolean up, boolean down, boolean left, boolean right) {
        game.updatePlayerMovement(1, up, down, left, right);
    }
    
    public int getCoinsCollected() {
        return game.getCoinsCollected();
    }
    
    public boolean isTimeUp() {
        return game.isTimeUp();
    }

    public int getDeaths() {
        return game.getDeaths();
    }

    public int getTimeRemaining() {
        return game.getTimeRemaining();
    }

    public void updateGame() {
        game.update();
    }
    
    public void movePlayerUp() {
        game.movePlayerUp(1);
    }

    public void movePlayerDown() {
        game.movePlayerDown(1);
    }

    public void movePlayerLeft() {
        game.movePlayerLeft(1);
    }

    public void movePlayerRight() {
        game.movePlayerRight(1);
    }

    public void stopPlayerVerticalMovement() {
        game.stopPlayerVerticalMovement(1);
    }

    public void stopPlayerHorizontalMovement() {
        game.stopPlayerHorizontalMovement(1);
    }
    
    public void updatePlayerMovement(int playerNumber, boolean up, boolean down, boolean left, boolean right) {
        game.updatePlayerMovement(playerNumber, up, down, left, right);
    }

    public static void main(String[] args) {
        TheWorldsHardestGameGUI gui = new TheWorldsHardestGameGUI();
        gui.setVisible(true);
    }
    
    public void saveGame() {
        try {
            game.saveGame(
                selectedLevel,
                modeInProgress,
                "data/saves/save1.txt"
            );

            JOptionPane.showMessageDialog(
                this,
                "Partida guardada correctamente."
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "No se pudo guardar la partida."
            );
        }
    }
    
    public void loadGame() {

        try {
            selectedLevel = game.loadSavedGame("data/saves/save1.txt");

            showScreen("game");
            gamePanel.startGame();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "No se pudo cargar la partida."
            );

            e.printStackTrace();
        }
    }
}