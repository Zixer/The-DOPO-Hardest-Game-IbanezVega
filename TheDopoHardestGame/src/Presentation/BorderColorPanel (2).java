package Presentation;

import javax.swing.*;
import java.awt.*;

public class BorderColorPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;

    private JLabel title;
    private JLabel subtitle;

    private JButton redButton;
    private JButton orangeButton;
    private JButton cyanButton;
    private JButton pinkButton;
    private JButton magentaButton;

    private JButton backButton;

    public BorderColorPanel(TheWorldsHardestGameGUI gui) {
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
        g2d.fillRoundRect(240, 150, 420, 340, 30, 30);

        g2d.setColor(new Color(20, 45, 90));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(240, 150, 420, 340, 30, 30);
    }

    private void prepareElements() {

        setLayout(null);

        title = new JLabel("CHOOSE BORDER");
        title.setFont(new Font("Impact", Font.BOLD, 42));
        title.setForeground(new Color(10, 30, 70));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(220, 60, 460, 60);

        add(title);

        subtitle = new JLabel("PLAYER " + gui.getCurrentPlayer());
        subtitle.setFont(new Font("Verdana", Font.BOLD, 18));
        subtitle.setForeground(new Color(60, 80, 120));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(250, 115, 400, 30);

        add(subtitle);

        redButton = createColorButton("RED", Color.RED);
        redButton.setBounds(340, 190, 220, 45);

        add(redButton);

        orangeButton = createColorButton("ORANGE", Color.ORANGE);
        orangeButton.setBounds(340, 250, 220, 45);

        add(orangeButton);

        cyanButton = createColorButton("CYAN", Color.CYAN);
        cyanButton.setBounds(340, 310, 220, 45);

        add(cyanButton);

        pinkButton = createColorButton("PINK", Color.PINK);
        pinkButton.setBounds(340, 370, 220, 45);

        add(pinkButton);

        magentaButton = createColorButton("MAGENTA", Color.MAGENTA);
        magentaButton.setBounds(340, 430, 220, 45);

        add(magentaButton);

        backButton = createMenuButton("BACK");
        backButton.setBounds(30, 540, 120, 45);

        add(backButton);
    }

    private JButton createColorButton(String text, Color color) {

        JButton button = new JButton(text);

        button.setFont(new Font("Verdana", Font.BOLD, 15));
        button.setForeground(Color.WHITE);

        button.setBackground(color);

        button.setFocusPainted(false);

        button.setBorder(
            BorderFactory.createLineBorder(
                Color.BLACK,
                3
            )
        );

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBorder(
                    BorderFactory.createLineBorder(
                        Color.WHITE,
                        3
                    )
                );
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBorder(
                    BorderFactory.createLineBorder(
                        Color.BLACK,
                        3
                    )
                );
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

        button.setBorder(
            BorderFactory.createLineBorder(
                new Color(10, 30, 80),
                3
            )
        );

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public void updateTitle() {
        subtitle.setText("PLAYER " + gui.getCurrentPlayer());
    }

    private void prepareActions() {

        redButton.addActionListener(e -> gui.setBorderColor(Color.RED));

        orangeButton.addActionListener(e -> gui.setBorderColor(Color.ORANGE));

        cyanButton.addActionListener(e -> gui.setBorderColor(Color.CYAN));

        pinkButton.addActionListener(e -> gui.setBorderColor(Color.PINK));

        magentaButton.addActionListener(e -> gui.setBorderColor(Color.MAGENTA));

        backButton.addActionListener(e -> gui.showScreen("mode"));
    }
}