package mysql;

import javax.swing.*;
import java.sql.*;
import java.security.SecureRandom;

public class PasswordManager {
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JTextArea notesArea;

    public static void generatePassword() {
        int length = 12;
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()-_=+";

        String allChars = uppercaseChars + lowercaseChars + numberChars + specialChars;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }

        JOptionPane.showMessageDialog(null, "Generated Password: " + password.toString());
    }


    public static void addPassword(String websiteName, String username, String password) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO passwords (website_name, username, password) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, websiteName);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchPassword(String websiteName, String username) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM passwords WHERE website_name = ? AND username = ?")) {
            preparedStatement.setString(1, websiteName);
            preparedStatement.setString(2, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String password = resultSet.getString("password");
                JOptionPane.showMessageDialog(null, "Password found for " + websiteName + ": " + password);
            } else {
                JOptionPane.showMessageDialog(null, "No passwords found for " + websiteName + " and username " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePassword(String websiteName, String user_name) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM passwords WHERE website_name = ? AND username = ?")) {
            preparedStatement.setString(1, websiteName);
            preparedStatement.setString(2, user_name);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Passwords deleted successfully for " + websiteName);
            } else {
                JOptionPane.showMessageDialog(null, "No passwords found for " + websiteName + " and username " + user_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
