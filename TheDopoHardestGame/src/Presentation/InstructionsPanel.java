package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

    private void prepareElements() {
        setLayout(null);
        setBackground(Color.WHITE);

        title = new JLabel("INSTRUCTIONS");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setBounds(305, 80, 350, 50);
        add(title);

        instructionsText = new JTextArea();
        instructionsText.setText(
            "You are the red square.\n\n" +
            "Avoid the blue circles and collect the yellow coins.\n\n" +
            "Once you have collected all the coins, move to the green zone to complete the level.\n\n" +
            "Your score depends on how many times you die."
        );
        instructionsText.setFont(new Font("Arial", Font.PLAIN, 18));
        instructionsText.setEditable(false);
        instructionsText.setLineWrap(true);
        instructionsText.setWrapStyleWord(true);
        instructionsText.setBackground(Color.WHITE);
        instructionsText.setBounds(190, 160, 520, 220);
        add(instructionsText);

        backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(280, 430, 120, 45);
        add(backButton);

        playButton = new JButton("PLAY GAME");
        playButton.setFont(new Font("Arial", Font.BOLD, 16));
        playButton.setBounds(480, 430, 140, 45);
        add(playButton);
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