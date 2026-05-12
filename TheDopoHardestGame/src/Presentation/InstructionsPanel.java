package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionsPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;
    private JLabel title;
    private JTextArea instructionsText;
    private JButton backButton;
    private JButton playButton;

    public InstructionsPanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        prepareElements();
        prepareActions();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(210, 230, 255),
            0, getHeight(), Color.WHITE
        );

        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(new Color(180, 210, 255, 90));
        g2d.fillOval(70, 80, 200, 200);
        g2d.fillOval(650, 380, 230, 230);

        g2d.setColor(new Color(255, 255, 255, 210));
        g2d.fillRoundRect(170, 150, 560, 280, 30, 30);

        g2d.setColor(new Color(20, 45, 90));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(170, 150, 560, 280, 30, 30);
    }

    private void prepareElements() {
        setLayout(null);

        title = new JLabel("HOW TO PLAY");
        title.setFont(new Font("Impact", Font.BOLD, 48));
        title.setForeground(new Color(10, 30, 70));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(180, 75, 540, 60);
        add(title);

        instructionsText = new JTextArea();
        instructionsText.setText(
            "• Move your square using the arrow keys.\n\n" +
            "• Avoid the blue enemies.\n\n" +
            "• Collect all the coins before reaching the green final zone.\n\n" +
            "• Your deaths and remaining time are shown at the top.\n\n" +
            "• Press ESC during the game to leave the level."
        );
        instructionsText.setFont(new Font("Verdana", Font.BOLD, 16));
        instructionsText.setForeground(new Color(35, 55, 95));
        instructionsText.setEditable(false);
        instructionsText.setOpaque(false);
        instructionsText.setLineWrap(true);
        instructionsText.setWrapStyleWord(true);
        instructionsText.setBounds(205, 180, 500, 230);
        add(instructionsText);

        backButton = createGameButton("BACK");
        backButton.setBounds(285, 470, 130, 50);
        add(backButton);

        playButton = createGameButton("PLAY GAME");
        playButton.setBounds(470, 470, 150, 50);
        add(playButton);
    }

    private JButton createGameButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(35, 95, 210));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(10, 30, 80), 3));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(60, 130, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(35, 95, 210));
            }
        });

        return button;
    }

    private void prepareActions() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("menu");
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("mode");
            }
        });
    }
}