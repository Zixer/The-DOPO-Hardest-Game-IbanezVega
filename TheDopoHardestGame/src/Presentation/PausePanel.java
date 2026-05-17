package Presentation;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {

    private TheWorldsHardestGameGUI gui;

    public PausePanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        prepareElements();
    }

    private void prepareElements() {
        setLayout(null);
        setBackground(new Color(210, 230, 255));

        JLabel title = new JLabel("PAUSED");
        title.setFont(new Font("Impact", Font.BOLD, 54));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(250, 90, 400, 70);
        add(title);

        JButton resumeButton = createButton("RESUME");
        resumeButton.setBounds(350, 200, 200, 45);
        resumeButton.addActionListener(e -> gui.resumeGame());
        add(resumeButton);

        JButton saveButton = createButton("SAVE GAME");
        saveButton.setBounds(350, 265, 200, 45);
        saveButton.addActionListener(e -> gui.saveGame());
        add(saveButton);

        JButton retryButton = createButton("RETRY");
        retryButton.setBounds(350, 330, 200, 45);
        retryButton.addActionListener(e -> gui.retryLevel());
        add(retryButton);

        JButton menuButton = createButton("MENU");
        menuButton.setBounds(350, 395, 200, 45);
        menuButton.addActionListener(e -> gui.showScreen("menu"));
        add(menuButton);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(35, 95, 210));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(10, 30, 80), 3));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}