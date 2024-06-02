package mysql;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {
    private JProgressBar progressBar;

    public LoadingScreen() {
        setTitle("Password and Notes Manager");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set app icon
        ImageIcon icon = new ImageIcon("C:\\Users\\chandana\\Desktop\\mysql\\mysql\\src\\mysql\\logo.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load background image
                Image img = new ImageIcon("C:\\Users\\chandana\\Desktop\\mysql\\mysql\\src\\mysql\\loa.jpg").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        getContentPane().add(panel);

        

        // Progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true); // Show percentage
        progressBar.setBounds(50, 300, 300, 30);
        progressBar.setForeground(Color.GREEN); // Set progress bar color to green
        panel.add(progressBar);

        // Simulate loading progress
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    progressBar.setValue(i);
                    Thread.sleep(20); // Simulate loading time
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                dispose(); // Close loading screen
            }
        }).start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoadingScreen();
    }
}
