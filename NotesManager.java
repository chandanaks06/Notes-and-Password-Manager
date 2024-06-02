package mysql;

import javax.swing.*;
import java.sql.*;

public class NotesManager {

    public static void addNote(String note_name, String note_content) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO notes (note_name, note_content) VALUES (?, ?)")) {
            preparedStatement.setString(1, note_name);
            preparedStatement.setString(2, note_content);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Note added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add note.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchNote(String note_name) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT note_content FROM notes WHERE note_name = ?")) {
            preparedStatement.setString(1, note_name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String note_content = resultSet.getString("note_content");
                JOptionPane.showMessageDialog(null, "Note content for " + note_name + ": " + note_content);
            } else {
                JOptionPane.showMessageDialog(null, "No notes found for " + note_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteNote(String note_name) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM notes WHERE note_name = ?")) {
            preparedStatement.setString(1, note_name);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Note deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No notes found for " + note_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
