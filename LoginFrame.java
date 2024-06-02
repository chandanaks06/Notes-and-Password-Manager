package mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Password and Notes Manager");
        setSize(400, 400); // Increased window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Set app icon
        ImageIcon icon = new ImageIcon("C:\\Users\\chandana\\Desktop\\mysql\\mysql\\src\\mysql\\logo.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon("C:\\Users\\chandana\\Desktop\\mysql\\mysql\\src\\mysql\\login.png").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(panel);
        panel.repaint();

        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Hello, please enter login credentials");
        titleLabel.setBounds(150, 20, 250, 25); // Adjusted position and size
        titleLabel.setForeground(Color.WHITE); // Set title label color
        panel.add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 80, 25);
        userLabel.setForeground(Color.WHITE); // Set username label color
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 25); // Adjusted size
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 25);
        passwordLabel.setForeground(Color.WHITE); // Set password label color
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 25); // Adjusted size
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 100, 30); // Adjusted size
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(200, 150, 100, 30); // Adjusted size
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate login
                if (DatabaseManager.loginUser(username, password)) {
                    // Show loading screen
                    LoadingScreen loadingScreen = new LoadingScreen();

                    // Simulate loading delay (replace with actual login logic)
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000); // Simulate 2 seconds loading time
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        // Redirect to home frame
                        loadingScreen.dispose();
                        dispose();
                        new HomeFrame(username).setVisible(true);
                    }).start();
                } else {
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegisterFrame().setVisible(true);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
