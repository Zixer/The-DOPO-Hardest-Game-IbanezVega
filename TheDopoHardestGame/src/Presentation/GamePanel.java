package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    private TheWorldsHardestGameGUI gui;
    private Timer timer;

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean wPressed;
    private boolean sPressed;
    private boolean aPressed;
    private boolean dPressed;

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

        inputMap.put(KeyStroke.getKeyStroke("UP"), "pressUp");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "pressDown");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "pressLeft");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "pressRight");

        inputMap.put(KeyStroke.getKeyStroke("released UP"), "releaseUp");
        inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "releaseDown");
        inputMap.put(KeyStroke.getKeyStroke("released LEFT"), "releaseLeft");
        inputMap.put(KeyStroke.getKeyStroke("released RIGHT"), "releaseRight");
        
        inputMap.put(KeyStroke.getKeyStroke("W"), "pressW");
        inputMap.put(KeyStroke.getKeyStroke("S"), "pressS");
        inputMap.put(KeyStroke.getKeyStroke("A"), "pressA");
        inputMap.put(KeyStroke.getKeyStroke("D"), "pressD");

        inputMap.put(KeyStroke.getKeyStroke("released W"), "releaseW");
        inputMap.put(KeyStroke.getKeyStroke("released S"), "releaseS");
        inputMap.put(KeyStroke.getKeyStroke("released A"), "releaseA");
        inputMap.put(KeyStroke.getKeyStroke("released D"), "releaseD");

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "backToMenu");

        actionMap.put("pressUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = true;
            }
        });

        actionMap.put("pressDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downPressed = true;
            }
        });

        actionMap.put("pressLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = true;
            }
        });

        actionMap.put("pressRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = true;
            }
        });

        actionMap.put("releaseUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = false;
            }
        });

        actionMap.put("releaseDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downPressed = false;
            }
        });

        actionMap.put("releaseLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPressed = false;
            }
        });

        actionMap.put("releaseRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPressed = false;
            }
        });

        actionMap.put("backToMenu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopGame();
                gui.pauseGame();
            }
        });
        
        actionMap.put("pressW", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wPressed = true;
            }
        });

        actionMap.put("pressS", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sPressed = true;
            }
        });

        actionMap.put("pressA", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aPressed = true;
            }
        });

        actionMap.put("pressD", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dPressed = true;
            }
        });

        actionMap.put("releaseW", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wPressed = false;
            }
        });

        actionMap.put("releaseS", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sPressed = false;
            }
        });

        actionMap.put("releaseA", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aPressed = false;
            }
        });

        actionMap.put("releaseD", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dPressed = false;
            }
        });
    }

    public void startGame() {
        requestFocusInWindow();

        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        wPressed = false;
        sPressed = false;
        aPressed = false;
        dPressed = false;

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(16, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.updatePlayerMovement(1, upPressed, downPressed, leftPressed, rightPressed);
            	gui.updatePlayerMovement(2, wPressed, sPressed, aPressed, dPressed);
            	gui.updateGame();

                if (gui.isTimeUp()) {
                    timer.stop();
                    gui.handleTimeUp();
                    return;
                }

                repaint();
            }
        });

        timer.start();
    }

    public void stopGame() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        gui.renderGame(g2d);
        drawHUD(g2d);
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
}