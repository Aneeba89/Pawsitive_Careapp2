package com.pawsitivecare;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest {

    static Connection connection;

    @BeforeAll
    static void setUpDatabase() throws Exception {
        // Establish connection to the test database
        connection = DriverManager.getConnection(
                "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123"
        );

        // Create the login table if it doesn't exist
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE login (username VARCHAR(50), password VARCHAR(50))");
        }

        // Insert test data
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO login (username, password) VALUES ('Aneeba123', 'Aneeba123&')");
        }
    }

    @Test
    void testValidateLogin_Success() throws Exception {
        Login_Page loginPage = new Login_Page();

        // Test valid credentials
        boolean result = loginPage.validateLogin("Aneeba123", "Aneeba123&");
        assertTrue(result, "Login should succeed with valid credentials.");
    }

    @Test
    void testValidateLogin_Failure() throws Exception {
        Login_Page loginPage = new Login_Page();

        // Test invalid credentials
        boolean result = loginPage.validateLogin("InvalidUser", "InvalidPass");
        assertFalse(result, "Login should fail with invalid credentials.");
    }

    @AfterAll
    static void tearDownDatabase() throws Exception {
        connection.close();
    }
}
