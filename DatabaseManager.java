package mysql;
//DatabaseManager.java
import java.sql.*;

public class DatabaseManager {
 private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
 private static final String DB_USER = "root";
 private static final String DB_PASSWORD = "";

 //The getConnection() method establishes a connection to the database using JDBC (Java Database Connectivity).
 public static Connection getConnection() throws SQLException {
     return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
 }
// create a statement using connection object
 public static void registerUser(String username, String password) {
     try (Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
         preparedStatement.setString(1, username);
         preparedStatement.setString(2, password);
         preparedStatement.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 public static boolean loginUser(String username, String password) {
     try (Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
         preparedStatement.setString(1, username);
         preparedStatement.setString(2, password);
         ResultSet resultSet = preparedStatement.executeQuery();
         return resultSet.next();
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }

 public static void saveNotes(String username, String noteContent) {
     try (Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_notes (username, note_content) VALUES (?, ?)")) {
         preparedStatement.setString(1, username);
         preparedStatement.setString(2, noteContent);
         int rowsAffected = preparedStatement.executeUpdate();

         if (rowsAffected > 0) {
             System.out.println("Notes saved successfully.");
         } else {
             System.out.println("Failed to save notes.");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 public static String getNotes(String username) {
     StringBuilder notes = new StringBuilder();
     try (Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("SELECT note_content FROM user_notes WHERE username = ?")) {
         preparedStatement.setString(1, username);
         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
             String noteContent = resultSet.getString("note_content");
             notes.append(noteContent).append("\n");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return notes.toString();
 }

 public static void deleteNotes(String username) {
     try (Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user_notes WHERE username = ?")) {
         preparedStatement.setString(1, username);
         int rowsAffected = preparedStatement.executeUpdate();

         if (rowsAffected > 0) {
             System.out.println("Notes deleted successfully.");
         } else {
             System.out.println("Failed to delete notes.");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }


 public static void addPassword(String website_name,String user_name, String password) {
     try (Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO passwords (website_name,username, password) VALUES (?, ?, ?)")) {
    	 preparedStatement.setString(1, website_name);
    	 preparedStatement.setString(2, user_name);
         preparedStatement.setString(3, password);
         
         preparedStatement.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 public static String getPassword(String username) {
     try (Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM passwords WHERE username = ?")) {
         preparedStatement.setString(2, username);
         ResultSet resultSet = preparedStatement.executeQuery();
         if (resultSet.next()) {
             return resultSet.getString("password");
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return null;
 }

 public static void deletePassword(String username) {
     try (Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM passwords WHERE username = ?")) {
         preparedStatement.setString(2, username);
         preparedStatement.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
}

