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

    public void setBorderColor(Color color) {
        if (currentPlayer == 1) {
            game.setPlayerBorderColor(1, color);
            currentPlayer = 2;
            showScreen("borderColor");
        } else {
            game.setPlayerBorderColor(2, color);
            currentPlayer = 1;
            showScreen("colorSelection");
        }
    }

    public void setPlayerFillColor(Color color) {
        if (currentPlayer == 1) {
            game.setPlayerFillColor(1, color);
            currentPlayer = 2;
            showScreen("colorSelection");
        } else {
            game.setPlayerFillColor(2, color);
            currentPlayer = 1;
            showScreen("levelSelector");
        }
    }

    public void selectLevel(int level) {
        selectedLevel = level;

        try {
            game.loadLevel(selectedLevel);
            showScreen("game");
            gamePanel.startGame();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "No se pudo cargar el nivel " + selectedLevel
            );
        }
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

    public void renderGame(Graphics2D g2d) {
        game.render(g2d);
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

    public static void main(String[] args) {
        TheWorldsHardestGameGUI gui = new TheWorldsHardestGameGUI();
        gui.setVisible(true);
    }
}