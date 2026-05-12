package Presentation;

import javax.swing.*;
import java.awt.*;

public class ColorSelectionPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;

    private JLabel title;
    private JLabel subtitle;

    private JButton blinkyButton;
    private JButton inkyButton;
    private JButton clydeButton;
    private JButton backButton;

    public ColorSelectionPanel(TheWorldsHardestGameGUI gui) {
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
        g2d.fillOval(80, 90, 220, 220);
        g2d.fillOval(650, 370, 240, 240);

        g2d.setColor(new Color(255, 255, 255, 215));
        g2d.fillRoundRect(150, 155, 600, 330, 30, 30);

        g2d.setColor(new Color(20, 45, 90));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(150, 155, 600, 330, 30, 30);
    }

    private void prepareElements() {
        setLayout(null);

        title = new JLabel("CHOOSE SKIN");
        title.setFont(new Font("Impact", Font.BOLD, 44));
        title.setForeground(new Color(10, 30, 70));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(200, 60, 500, 60);
        add(title);

        subtitle = new JLabel("PLAYER " + gui.getCurrentPlayer());
        subtitle.setFont(new Font("Verdana", Font.BOLD, 18));
        subtitle.setForeground(new Color(60, 80, 120));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(250, 115, 400, 30);
        add(subtitle);

        blinkyButton = createSkinButton(
            "BLINKY",
            Color.RED,
            "Normal skin: speed 1x, size 1x, no special ability."
        );
        blinkyButton.setBounds(210, 230, 130, 150);
        add(blinkyButton);

        inkyButton = createSkinButton(
            "INKY",
            Color.BLUE,
            "Fast skin: speed 1.5x, size 1.5x."
        );
        inkyButton.setBounds(385, 230, 130, 150);
        add(inkyButton);

        clydeButton = createSkinButton(
            "CLYDE",
            Color.GREEN,
            "Resistant skin: speed 1x, size 1x, resists one hit, then slows down."
        );
        clydeButton.setBounds(560, 230, 130, 150);
        add(clydeButton);

        backButton = createMenuButton("BACK");
        backButton.setBounds(30, 540, 120, 45);
        add(backButton);
    }

    private JButton createSkinButton(String name, Color skinColor, String info) {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                g2d.setColor(skinColor);
                g2d.fillRect(42, 25, 45, 45);

                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(4));
                g2d.drawRect(42, 25, 45, 45);

                g2d.setFont(new Font("Verdana", Font.BOLD, 14));
                g2d.setColor(Color.WHITE);

                FontMetrics metrics = g2d.getFontMetrics();
                int textX = (getWidth() - metrics.stringWidth(name)) / 2;
                g2d.drawString(name, textX, 110);
            }
        };

        button.setBackground(new Color(35, 95, 210));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(10, 30, 80), 3));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setToolTipText(info);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(60, 130, 255));
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(35, 95, 210));
                button.setBorder(BorderFactory.createLineBorder(new Color(10, 30, 80), 3));
            }
        });

        return button;
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);

        button.setFont(new Font("Verdana", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(35, 95, 210));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(10, 30, 80), 3));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public void updateTitle() {
        subtitle.setText("PLAYER " + gui.getCurrentPlayer());
    }

    private void prepareActions() {
        blinkyButton.addActionListener(e -> gui.setPlayerSkin("BLINKY"));

        inkyButton.addActionListener(e -> gui.setPlayerSkin("INKY"));

        clydeButton.addActionListener(e -> gui.setPlayerSkin("CLYDE"));

        backButton.addActionListener(e -> gui.showScreen("borderColor"));
    }
}