package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;
    private JLabel smallTitle;
    private JLabel title;
    private JLabel subtitle;
    private JButton playButton;
    private JButton quitButton;

    public MenuPanel(TheWorldsHardestGameGUI gui) {
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
        g2d.fillOval(90, 80, 180, 180);
        g2d.fillOval(650, 360, 220, 220);
    }

    private void prepareElements() {
        setLayout(null);

        smallTitle = new JLabel("THE WORLD'S...");
        smallTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        smallTitle.setForeground(new Color(20, 45, 90));
        smallTitle.setHorizontalAlignment(SwingConstants.CENTER);
        smallTitle.setBounds(250, 140, 400, 35);
        add(smallTitle);

        title = new JLabel("HARDEST GAME");
        title.setFont(new Font("Impact", Font.BOLD, 52));
        title.setForeground(new Color(10, 30, 70));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(170, 180, 560, 70);
        add(title);

        subtitle = new JLabel("Avoid enemies • collect coins • reach the safe zone");
        subtitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        subtitle.setForeground(new Color(60, 80, 120));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(180, 255, 540, 30);
        add(subtitle);

        playButton = createGameButton("PLAY");
        playButton.setBounds(315, 330, 130, 50);
        add(playButton);

        quitButton = createGameButton("QUIT");
        quitButton.setBounds(465, 330, 130, 50);
        add(quitButton);
    }

    private JButton createGameButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, 18));
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
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("instructions");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}