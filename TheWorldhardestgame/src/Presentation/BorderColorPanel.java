package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorderColorPanel extends JPanel {

    private TheWorldsHardestGameGUI gui;

    private JLabel title;
    private JButton yellowButton;
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
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);
        title.setBounds(230, 80, 500, 50);
        add(title);

        yellowButton = new JButton("YELLOW");
        yellowButton.setBounds(330, 360, 220, 40);
        add(yellowButton);

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

    	yellowButton.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        gui.setBorderColor(Color.RED);
    	    }
    	});

        orangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.setBorderColor(Color.ORANGE);
            }
        });

        cyanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.setBorderColor(Color.CYAN);
            }
        });

        pinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.setBorderColor(Color.PINK);;
            }
        });

        magentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	gui.setBorderColor(Color.MAGENTA);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("mode");
            }
        });
    }
}