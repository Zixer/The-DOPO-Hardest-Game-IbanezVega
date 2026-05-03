package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelSelectorPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;
    private JLabel title;
    private JButton[] levelButtons;
    private JButton backButton;

    public LevelSelectorPanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setLayout(null);
        setBackground(Color.WHITE);

        title = new JLabel("LEVEL SELECTOR");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(50, 45, 300, 40);
        add(title);

        levelButtons = new JButton[18];

        int startX = 90;
        int startY = 120;
        int buttonWidth = 55;
        int buttonHeight = 45;
        int gapX = 80;
        int gapY = 75;

        for (int i = 0; i < 18; i++) {
            final int levelNumber = i + 1; // Variable final para el listener

            levelButtons[i] = new JButton(String.valueOf(levelNumber));
            levelButtons[i].setFont(new Font("Arial", Font.BOLD, 14));
            levelButtons[i].setBackground(new Color(160, 210, 245));
            // Quita el borde de foco feo de Swing
            levelButtons[i].setFocusPainted(false);
            
            levelButtons[i].setBounds(
                startX + (i % 9) * gapX,
                startY + (i / 9) * gapY,
                buttonWidth,
                buttonHeight
            );

            add(levelButtons[i]);
        }

        backButton = new JButton("GO BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 13));
        backButton.setFocusPainted(false);
        // Ajusté la posición para que esté centrado respecto a la cuadrícula de botones
        backButton.setBounds(385, 310, 130, 45);
        add(backButton);
    }

    private void prepareActions() {
        // Usando lambdas para un código más limpio (Java 8+)
        for (int i = 0; i < levelButtons.length; i++) {
            final int levelNumber = i + 1;
            levelButtons[i].addActionListener(e -> {
                // Llama al método que ya tienes en la GUI principal
                gui.selectLevel(levelNumber);
            });
        }

        backButton.addActionListener(e -> {
            // Regresa a la pantalla anterior
            gui.showScreen("colorSelection");
        });
    }
}