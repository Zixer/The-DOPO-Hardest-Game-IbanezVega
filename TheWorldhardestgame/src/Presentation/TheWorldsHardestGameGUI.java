package Presentation;

import javax.swing.*;
import java.awt.*;

public class TheWorldsHardestGameGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private BorderColorPanel borderColorPanel;
    private int currentPlayer = 1;
    private Color player1Border;
    private Color player2Border;

    public TheWorldsHardestGameGUI() {
        prepareElements();
    }

    private void prepareElements() {
        setTitle("THE WORLD'S HARDEST GAME");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new MenuPanel(this), "menu");
        mainPanel.add(new InstructionsPanel(this), "instructions");
        mainPanel.add(new ModePanel(this), "mode");
        mainPanel.add(new GamePanel(this), "game");
        mainPanel.add(new BorderColorPanel(this), "borderColor");
        borderColorPanel = new BorderColorPanel(this);
        mainPanel.add(borderColorPanel, "borderColor");

        add(mainPanel);
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
            player1Border = color;
            currentPlayer = 2;
            showScreen("borderColor");
        } else {
            player2Border = color;
            currentPlayer = 1;
            showScreen("game");
        }
    }
    
    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);

        if (screenName.equals("borderColor")) {
            borderColorPanel.updateTitle();
        }
    }
    public static void main(String[] args) {
        TheWorldsHardestGameGUI gui = new TheWorldsHardestGameGUI();
        gui.setVisible(true);
    }
}