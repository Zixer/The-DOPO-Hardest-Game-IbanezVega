package Presentation;

import javax.swing.*;
import java.awt.*;

public class ColorSelectionPanel extends JPanel {
    private TheWorldsHardestGameGUI gui;
    private JLabel title;
    private JButton blueButton, greenButton, redButton;

    public ColorSelectionPanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        setLayout(null);
        setBackground(new Color(50, 50, 50));

        title = new JLabel("PLAYER " + gui.getCurrentPlayer() + " - CHOOSE FILL");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 80, 500, 50);
        add(title);

        blueButton = new JButton("BLUE");
        blueButton.setBounds(330, 200, 220, 40);
        blueButton.addActionListener(e -> gui.setPlayerSkin("INKY"));
        add(blueButton);

        greenButton = new JButton("GREEN");
        greenButton.setBounds(330, 260, 220, 40);
        greenButton.addActionListener(e -> gui.setPlayerSkin("CLYDE"));
        add(greenButton);

        redButton = new JButton("RED");
        redButton.setBounds(330, 320, 220, 40);
        redButton.addActionListener(e -> gui.setPlayerSkin("BLINKY"));
        add(redButton);
    }

    public void updateTitle() {
        title.setText("PLAYER " + gui.getCurrentPlayer() + " - CHOOSE FILL");
    }
}