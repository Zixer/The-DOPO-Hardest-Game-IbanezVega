package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModePanel extends JPanel {

    private TheWorldsHardestGameGUI gui;

    private JLabel title;
    private JButton normalButton;
    private JButton pvsmButton;
    private JButton pvspButton;
    private JButton backButton;

    public ModePanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setLayout(null);
        setBackground(new Color(0, 102, 255));

        title = new JLabel("SELECT MODE");
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setBounds(290, 90, 350, 60);
        add(title);

        normalButton = new JButton("NORMAL");
        normalButton.setFont(new Font("Arial", Font.BOLD, 18));
        normalButton.setBounds(350, 200, 200, 45);
        add(normalButton);

        pvsmButton = new JButton("PvsM");
        pvsmButton.setFont(new Font("Arial", Font.BOLD, 18));
        pvsmButton.setBounds(350, 270, 200, 45);
        add(pvsmButton);

        pvspButton = new JButton("PvsP");
        pvspButton.setFont(new Font("Arial", Font.BOLD, 18));
        pvspButton.setBounds(350, 340, 200, 45);
        add(pvspButton);

        backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(30, 530, 110, 40);
        add(backButton);
    }
    
    private void prepareActions() {

    	normalButton.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        gui.showScreen("game");
    	    }
    	});

        pvsmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    	        gui.showScreen("game");
    	    }
        });

        pvspButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                gui.startPvSPColorSelection();
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