package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorSelectionPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;

    private JLabel title;
    private JButton blueButton;
    private JButton redButton;
    private JButton greenButton;
    private JButton backButton;

    public ColorSelectionPanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setLayout(null);
        setBackground(new Color(0, 102, 255));

        title = new JLabel("CHOOSE COLOR");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(280, 100, 400, 60);
        add(title);

        blueButton = new JButton("BLUE");
        blueButton.setFont(new Font("Arial", Font.BOLD, 18));
        blueButton.setBounds(350, 220, 200, 45);
        blueButton.setBackground(Color.BLUE);
        blueButton.setForeground(Color.WHITE);
        add(blueButton);

        redButton = new JButton("RED");
        redButton.setFont(new Font("Arial", Font.BOLD, 18));
        redButton.setBounds(350, 290, 200, 45);
        redButton.setBackground(Color.RED);
        redButton.setForeground(Color.WHITE);
        add(redButton);

        greenButton = new JButton("GREEN");
        greenButton.setFont(new Font("Arial", Font.BOLD, 18));
        greenButton.setBounds(350, 360, 200, 45);
        greenButton.setBackground(Color.GREEN);
        greenButton.setForeground(Color.BLACK);
        add(greenButton);

        backButton = new JButton("BACK");
        backButton.setBounds(30, 530, 110, 40);
        add(backButton);
    }

    private void prepareActions() {

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BLUE SELECTED");
            }
        });

        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RED SELECTED");
            }
        });

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("GREEN SELECTED");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("menu");
            }
        });
    }
}