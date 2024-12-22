package com.pawsitivecare;

import com.pawsitivecare.Signup_Page;
import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class Signup_PageTest {

    private Signup_Page signupPage;

    @BeforeEach
    void setup() {
        signupPage = new Signup_Page();
    }

    @Test
    void testValidUsername() {
        assertTrue(signupPage.isValidUsername("User"), "Username with letters and numbers should be valid.");
    }

    @Test
    void testValidPassword() {
        assertTrue(signupPage.isValidPassword("Pass123!"), "Password with letters, numbers, and special characters should be valid.");
    }

    @Test
    void testValidEmail() {
        assertTrue(signupPage.isValidEmail("test@example.com"), "Valid email format should be accepted.");
    }


    @Test
    void testInsertUser_Success() {
        // Arrange
        String username = "user12345";
        String password = "Pass@12345";
        String email = "user12345@example.com";

        // Act
        boolean result = signupPage.insertUser(username, password, email);

        // Assert
        assertTrue(result, "User should be inserted successfully");

        // Verify the data in the database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123")) {
            String query = "SELECT * FROM login WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            assertTrue(rs.next(), "User should exist in the database");
            assertEquals(username, rs.getString("username"), "Username should match");
            assertEquals(password, rs.getString("password"), "Password should match");
            assertEquals(email, rs.getString("email"), "Email should match");
        } catch (Exception e) {
            fail("Database verification failed: " + e.getMessage());
        }
    }

    @Test
    void testInsertUser_Failure_DuplicateUsername() {
        // Arrange
        String username = "user12345";
        String password = "Pass@12345";
        String email = "user12345@example.com";

        // Insert the first user
        signupPage.insertUser(username, password, email);

        // Act
        boolean result = signupPage.insertUser(username, "NewPass@123", "user2@example.com");

        // Assert
        assertFalse(result, "Duplicate username should not be allowed");
    }
}

