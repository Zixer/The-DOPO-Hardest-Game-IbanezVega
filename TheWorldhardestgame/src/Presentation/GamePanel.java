package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel {

    private TheWorldsHardestGameGUI gui;

    public GamePanel(TheWorldsHardestGameGUI gui) {
        this.gui = gui;
        prepareElements();
        prepareKeyBindings();
    }

    private void prepareElements() {
        setBackground(Color.BLACK);

        JLabel label = new JLabel("GAME STARTED");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        add(label);
    }
    
    private void prepareKeyBindings() {

        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "goToMenu");

        actionMap.put("goToMenu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showScreen("menu");
            }
        });
    }
}