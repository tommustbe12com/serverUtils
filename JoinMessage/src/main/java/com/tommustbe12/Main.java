package com.tommustbe12;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ServerUtils");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 100);
            frame.add(new JLabel("      Please put me in the plugins folder on your server to use me!"));
            frame.setLocationRelativeTo(null); // center on screen
            frame.setVisible(true);
        });
    }
}
