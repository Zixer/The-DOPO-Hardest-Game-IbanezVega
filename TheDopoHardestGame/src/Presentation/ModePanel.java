package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModePanel extends JPanel {

    private TheWorldsHardestGameGUI gui;

    private JLabel title;
    private JLabel subtitle;

    private JButton normalButton;
    private JButton pvsmButton;
    private JButton pvspButton;
    private JButton backButton;

    public ModePanel(TheWorldsHardestGameGUI gui) {
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
        g2d.fillOval(80, 100, 220, 220);
        g2d.fillOval(620, 350, 250, 250);

        g2d.setColor(new Color(255, 255, 255, 210));
        g2d.fillRoundRect(250, 160, 400, 280, 30, 30);

        g2d.setColor(new Color(20, 45, 90));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(250, 160, 400, 280, 30, 30);
    }

    private void prepareElements() {

        setLayout(null);

        title = new JLabel("SELECT MODE");
        title.setFont(new Font("Impact", Font.BOLD, 46));
        title.setForeground(new Color(10, 30, 70));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(200, 70, 500, 60);

        add(title);

        subtitle = new JLabel("Choose how you want to play");
        subtitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        subtitle.setForeground(new Color(60, 80, 120));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(240, 125, 420, 30);

        add(subtitle);

        normalButton = createGameButton("NORMAL");
        normalButton.setBounds(340, 210, 220, 50);

        add(normalButton);

        pvsmButton = createGameButton("PLAYER VS MACHINE");
        pvsmButton.setBounds(340, 285, 220, 50);

        add(pvsmButton);

        pvspButton = createGameButton("PLAYER VS PLAYER");
        pvspButton.setBounds(340, 360, 220, 50);

        add(pvspButton);

        backButton = createGameButton("BACK");
        backButton.setBounds(30, 540, 120, 45);

        add(backButton);
    }

    private JButton createGameButton(String text) {

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

        normalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.startColorSelectionForMode("NORMAL");
            }
        });

        pvsmButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("levelSelector");
            }
        });

        pvspButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.startColorSelectionForMode("PVSP");
            }
        });

        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("instructions");
            }
        });
    }
}