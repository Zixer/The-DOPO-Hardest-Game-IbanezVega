package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    private TheWorldsHardestGameGUI gui;
    private Timer timer;

    public GamePanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        prepareElements();
        prepareKeyBindings();
    }

    private void prepareElements() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(180, 180, 255));
        setFocusable(true);
    }

    private void prepareKeyBindings() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");

        inputMap.put(KeyStroke.getKeyStroke("released UP"), "stopVertical");
        inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "stopVertical");
        inputMap.put(KeyStroke.getKeyStroke("released LEFT"), "stopHorizontal");
        inputMap.put(KeyStroke.getKeyStroke("released RIGHT"), "stopHorizontal");

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "backToMenu");

        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.movePlayerUp();
            }
        });

        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.movePlayerDown();
            }
        });

        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.movePlayerLeft();
            }
        });

        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.movePlayerRight();
            }
        });

        actionMap.put("stopVertical", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.stopPlayerVerticalMovement();
            }
        });

        actionMap.put("stopHorizontal", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.stopPlayerHorizontalMovement();
            }
        });

        actionMap.put("backToMenu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("menu");
            }
        });
    }

    public void startGame() {
        requestFocusInWindow();

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(16, e -> {
            gui.updateGame();

            if (gui.isTimeUp()) {
                timer.stop();
                gui.handleTimeUp();
                return;
            }

            repaint();
        });

        timer.start();
    }
    
    private void drawHUD(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), 35);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        g2d.drawString("TIME: " + gui.getTimeRemaining(), 20, 23);
        g2d.drawString("DEATHS: " + gui.getDeaths(), 160, 23);
        g2d.drawString("COINS: " + gui.getCoinsCollected(), 320, 23);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        gui.renderGame(g2d);
        drawHUD(g2d);
    }
}