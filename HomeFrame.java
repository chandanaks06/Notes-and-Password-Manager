package mysql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame {
    private JButton generatePasswordButton;
    private JButton addPasswordButton;
    private JButton searchPasswordButton;
    private JButton deletePasswordButton;
    private JButton addNoteButton;
    private JButton searchNoteButton;
    private JButton deleteNoteButton;
    private JButton logoutButton;

    public HomeFrame(String username) {
        setTitle("Home - Welcome " + username);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
     // Set app icon
        ImageIcon icon = new ImageIcon("C:\\Users\\chandana\\Desktop\\mysql\\mysql\\src\\mysql\\logo.png");
        setIconImage(icon.getImage());

        

        // Create panel with background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = new ImageIcon("C:\\Users\\chandana\\Desktop\\mysql\\mysql\\src\\mysql\\bac.png").getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(panel);
        panel.repaint();

        panel.setLayout(null);

        // Add buttons one by one
        int y = 10; // Initial y-coordinate
        int buttonHeight = 25;
        int buttonWidth = 180;
        int gap = 10; // Gap between buttons

        generatePasswordButton = new JButton("Generate Password");
        generatePasswordButton.setBounds(10, y, buttonWidth, buttonHeight);
        panel.add(generatePasswordButton);
        y += buttonHeight + gap;
        generatePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PasswordManager.generatePassword();
            }
        });

        addPasswordButton = new JButton("Add Password");
        addPasswordButton.setBounds(10, y, buttonWidth, buttonHeight);
        panel.add(addPasswordButton);
        y += buttonHeight + gap;
        addPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String website_name = JOptionPane.showInputDialog(null, "Enter website_name:");
                String username = JOptionPane.showInputDialog(null, "Enter username:");
                String password = JOptionPane.showInputDialog(null, "Enter password:");

                // Call addPassword method from PasswordManager with necessary parameters
                PasswordManager.addPassword(website_name, username, password);
            }
        });

        searchPasswordButton = new JButton("Search Password");
        searchPasswordButton.setBounds(10, y, buttonWidth, buttonHeight);
        panel.add(searchPasswordButton);
        y += buttonHeight + gap;
        searchPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String websiteName = JOptionPane.showInputDialog("Enter Website Name:");
                String username = JOptionPane.showInputDialog("Enter Username:");
                if (websiteName != null && !websiteName.isEmpty()) {
                    PasswordManager.searchPassword(websiteName, username);
                }
            }
        });

        deletePasswordButton = new JButton("Delete Password");
        deletePasswordButton.setBounds(10, y, buttonWidth, buttonHeight);
        panel.add(deletePasswordButton);
        y += buttonHeight + gap;
        deletePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String websiteName = JOptionPane.showInputDialog("Enter Website Name:");
                String username = JOptionPane.showInputDialog("Enter Username:");
                if (websiteName != null && !websiteName.isEmpty()) {
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete passwords for " + websiteName + "?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        PasswordManager.deletePassword(websiteName, username);
                    }
                }
            }
        });

        addNoteButton = new JButton("Add Note");
        addNoteButton.setBounds(10, y, buttonWidth, buttonHeight);
        panel.add(addNoteButton);
        y += buttonHeight + gap;
        addNoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String note_name = JOptionPane.showInputDialog("Enter Note Name:");
                String note_content = JOptionPane.showInputDialog("Enter Note Content:");
                if (note_name != null && note_content != null && !note_name.isEmpty() && !note_content.isEmpty()) {
                    NotesManager.addNote(note_name, note_content);
                }
            }
        });

        searchNoteButton = new JButton("Search Note");
        searchNoteButton.setBounds(10, y, buttonWidth, buttonHeight);
        panel.add(searchNoteButton);
        y += buttonHeight + gap;
        searchNoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String note_name = JOptionPane.showInputDialog("Enter Note Name:");
                if (note_name != null && !note_name.isEmpty()) {
                    NotesManager.searchNote(note_name);
                }
            }
        });

        deleteNoteButton = new JButton("Delete Note");
        deleteNoteButton.setBounds(10, y, buttonWidth, buttonHeight);
        panel.add(deleteNoteButton);
        y += buttonHeight + gap;
        deleteNoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String note_name = JOptionPane.showInputDialog("Enter Note Name:");
                if (note_name != null && !note_name.isEmpty()) {
                    NotesManager.deleteNote(note_name);
                }
            }
        });

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, y, buttonWidth, buttonHeight);
        panel.add(logoutButton);
        logoutButton.addActionListener(e -> {
            // Redirect to login frame
            dispose();
            new LoginFrame().setVisible(true);
        });

        setVisible(true);
    }

    
}
