package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorderColorPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;
    private JLabel title;
    private JButton redButton; // Cambiado de yellow a red para coincidir con la lÃ³gica
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

    private void prepareElements() {
        setLayout(null);
        setBackground(new Color(0, 102, 255));

        title = new JLabel("CHOOSE BORDER COLOR");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(100, 80, 700, 50);
        add(title);

        // Ajuste de posiciones Y para que no se solapen
        redButton = new JButton("RED");
        redButton.setBounds(330, 180, 220, 40);
        add(redButton);

        orangeButton = new JButton("ORANGE");
        orangeButton.setBounds(330, 240, 220, 40);
        add(orangeButton);

        cyanButton = new JButton("CYAN");
        cyanButton.setBounds(330, 300, 220, 40);
        add(cyanButton);

        pinkButton = new JButton("PINK");
        pinkButton.setBounds(330, 360, 220, 40);
        add(pinkButton);

        magentaButton = new JButton("MAGENTA");
        magentaButton.setBounds(330, 420, 220, 40);
        add(magentaButton);

        backButton = new JButton("BACK");
        backButton.setBounds(30, 530, 110, 40);
        add(backButton);
    }

    public void updateTitle() {
        title.setText("PLAYER " + gui.getCurrentPlayer() + " - CHOOSE BORDER");
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