package mysql;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterFrame() {
    	setTitle("Password and Notes Manager");
        setSize(400, 400); // Adjusted screen size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set app icon
        ImageIcon icon = new ImageIcon("C:\\Users\\chandana\\Desktop\\mysql\\mysql\\src\\mysql\\logo.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load background image
                Image img = new ImageIcon("C:\\Users\\chandana\\Desktop\\mysql\\mysql\\src\\mysql\\reg.jpg").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(panel);
        panel.setLayout(null);

        // Title "Register to Login"
        JLabel titleLabel = new JLabel("Register to Login");
        titleLabel.setBounds(150, 50, 150, 25);
        titleLabel.setForeground(Color.WHITE); // Set title label color
        panel.add(titleLabel);

        // Username label and text field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 100, 80, 25);
        userLabel.setForeground(Color.WHITE); // Set username label color
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 100, 200, 25);
        panel.add(usernameField);

        // Password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 80, 25);
        passwordLabel.setForeground(Color.WHITE); // Set password label color
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 150, 200, 25);
        panel.add(passwordField);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 200, 100, 30);
        panel.add(registerButton);

        // Action listener for register button
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter username and password.");
            } else {
                // Call register method from DatabaseManager
                DatabaseManager.registerUser(username, password);
                JOptionPane.showMessageDialog(null, "Registration successful.");
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 250, 100, 30);
        panel.add(loginButton);
        loginButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        setVisible(true);
    }
}
