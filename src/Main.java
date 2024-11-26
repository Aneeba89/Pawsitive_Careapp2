import java.sql.*;

class SqlConnection {
    public static void main(String[] args) {
        // Define the connection parameters
        String url = "jdbc:sqlserver://DESKTOP-GDTK610\\SQLEXPRESS01;databaseName=PetApp;encrypt=false;trustServerCertificate=true;"; // Replace with your database URL
        String username = "Aneeba12"; // Replace with your SQL Server username
        String password = "Anikhan1234"; // Replace with your SQL Server password

        // SQL query to test connection
        String query = "SELECT * FROM login"; // Replace with your SQL query

        // Load and register the JDBC driver (optional in newer versions of Java)
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            // Create a statement and execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                // Replace with appropriate column names for your table
                System.out.println("Column1: " + resultSet.getString("username"));
                System.out.println("Column1: " + resultSet.getString("email"));
                
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("SQL Server JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}