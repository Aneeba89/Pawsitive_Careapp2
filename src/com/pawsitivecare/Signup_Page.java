package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Signup_Page extends JFrame {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private Image backgroundImage;

    public Signup_Page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);

        // Load the background image
        Image backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\Aneeba Project 062\\SCDproject\\img_pet4.jpg").getImage();

        // Set up the content pane
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Signup Page");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 44));
        titleLabel.setForeground(new Color(191, 128, 64));
        titleLabel.setBounds(-10, 50, 900, 80);
        contentPane.add(titleLabel);

        // Username label
        JLabel usernameLabel = new JLabel("Username*");
        usernameLabel.setFont(new Font("Papyrus", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(191, 128, 64));
        usernameLabel.setBounds(300, 250, 100, 19);
        contentPane.add(usernameLabel);

        // Username text field
        usernameField = new JTextField();
        usernameField.setBounds(300, 280, 300, 30);
        contentPane.add(usernameField);

        // Password label
        JLabel passwordLabel = new JLabel("Password*");
        passwordLabel.setFont(new Font("Papyrus", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(191, 128, 64));
        passwordLabel.setBounds(300, 320, 100, 19);
        contentPane.add(passwordLabel);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(300, 350, 300, 30);
        contentPane.add(passwordField);

        // Email label
        JLabel emailLabel = new JLabel("Email*");
        emailLabel.setFont(new Font("Papyrus", Font.BOLD, 14));
        emailLabel.setForeground(new Color(191, 128, 64));
        emailLabel.setBounds(300, 390, 100, 19);
        contentPane.add(emailLabel);

        // Email text field
        emailField = new JTextField();
        emailField.setBounds(300, 420, 300, 30);
        contentPane.add(emailField);

        // Signup button
        JButton signupButton = new JButton("Signup");
        signupButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(new Color(0, 100, 200));
        signupButton.setBounds(300, 470, 100, 30);
        contentPane.add(signupButton);

        // Reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(220, 20, 60));
        resetButton.setBounds(500, 470, 100, 30);
        contentPane.add(resetButton);

        resetButton.addActionListener(e -> {
            usernameField.setText(null);
            passwordField.setText(null);
            emailField.setText(null);
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setBounds(400, 600, 100, 30);
        contentPane.add(backButton);

        backButton.addActionListener(e -> dispose());

        // Signup button action listener
        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();

            // Thread for username validation
            Thread usernameValidationThread = new Thread(() -> {
                if (!isValidUsername(username)) {
                    showValidationError("Username must contain both letters and numbers.");
                }
            });

            // Thread for password validation
            Thread passwordValidationThread = new Thread(() -> {
                if (!isValidPassword(password)) {
                    showValidationError("Password must be longer than 6 characters and contain letters, numbers, and special characters.");
                }
            });

            // Thread for email validation
            Thread emailValidationThread = new Thread(() -> {
                if (!isValidEmail(email)) {
                    showValidationError("Email must be valid.");
                }
            });

            // Start all validation threads
            usernameValidationThread.start();
            passwordValidationThread.start();
            emailValidationThread.start();

            try {
                // Wait for all validation threads to complete
                usernameValidationThread.join();
                passwordValidationThread.join();
                emailValidationThread.join();

                // If all validations pass, proceed with signup
                if (isValidUsername(username) && isValidPassword(password) && isValidEmail(email)) {
                    new Thread(() -> {
                        boolean success = insertUser(username, password, email);
                        SwingUtilities.invokeLater(() -> {
                            if (success) {
                                JOptionPane.showMessageDialog(contentPane, "Signup Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(contentPane, "Signup Failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                    }).start();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void showValidationError(String message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(contentPane, message, "Validation Error", JOptionPane.ERROR_MESSAGE));
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$");
    }

    public boolean isValidPassword(String password) {
        return password.length() > 6 && password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$");
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    public boolean insertUser(String username, String password, String email) {
        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
        //String query = "INSERT INTO login (username, password, email) VALUES (?, ?, ?)";
        String query = "INSERT INTO login (username, password, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);

            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Signup_Page frame = new Signup_Page();
            frame.setVisible(true);
        });
    }
}
