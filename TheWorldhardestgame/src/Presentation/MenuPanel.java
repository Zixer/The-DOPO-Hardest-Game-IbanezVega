package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;
    private JLabel smallTitle;
    private JLabel title;
    private JButton playButton;
    private JButton quitButton;

    public MenuPanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setLayout(null);
        setBackground(Color.WHITE);

        smallTitle = new JLabel("THE WORLD'S...");
        smallTitle.setFont(new Font("Arial", Font.BOLD, 22));
        smallTitle.setBounds(355, 140, 250, 30);
        add(smallTitle);

        title = new JLabel("HARDEST GAME");
        title.setFont(new Font("Arial", Font.BOLD, 46));
        title.setBounds(260, 180, 450, 60);
        add(title);

        playButton = new JButton("PLAY");
        playButton.setFont(new Font("Arial", Font.BOLD, 16));
        playButton.setBounds(320, 290, 110, 45);
        add(playButton);

        quitButton = new JButton("QUIT");
        quitButton.setFont(new Font("Arial", Font.BOLD, 16));
        quitButton.setBounds(470, 290, 110, 45);
        add(quitButton);
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