package este.test;
import java.sql.*;

public class App {

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/school";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    private static void selectData() {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {

            String query = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");

                System.out.println("Student ID: " + studentId + ", Student Name: " + firstName + " " + lastName + " Age: " + age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData() {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO student (first_name, last_name, age) VALUES (?,?,?)")) {

            preparedStatement.setString(1, "Smith");
            preparedStatement.setString(2, "Joe");
            preparedStatement.setInt(3, 34);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateData() {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {

            int studentIdToUpdate = 1;
            String newName = "Ahmed";

            String updateQuery = "UPDATE student SET first_name = '" + newName + "' WHERE id = " + studentIdToUpdate;
            int rowsAffected = statement.executeUpdate(updateQuery);

            if (rowsAffected > 0) {
                System.out.println("Name updated successfully!");
            } else {
                System.out.println("Failed to update name.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteData() {
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement()) {

            int studentIdToDelete = 2;

            String deleteQuery = "DELETE FROM student WHERE id = " + studentIdToDelete;
            int rowsAffected = statement.executeUpdate(deleteQuery);

            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Failed to delete student.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        selectData();
//        insertData();
//        updateData();
//        deleteData();
    }
}
