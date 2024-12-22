package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login_Page extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private Image backgroundImage;

    public Login_Page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 700);

        // Load the background image
        backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\Aneeba Project 062\\SCDproject\\img_pet4.jpg").getImage();

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
        JLabel lblNewLabel = new JLabel("Login Page");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Papyrus", Font.BOLD, 44));
        lblNewLabel.setForeground(new Color(191, 128, 64)); // Adjusted text color
        lblNewLabel.setBounds(-10, 50, 900, 80); // Centered at the top
        contentPane.add(lblNewLabel);

        // Username label
        JLabel lblNewLabel_1 = new JLabel("Username*");
        lblNewLabel_1.setFont(new Font("Papyrus", Font.BOLD, 14));
        lblNewLabel_1.setForeground(new Color(191, 128, 64)); // Adjusted text color
        lblNewLabel_1.setBounds(300, 250, 100, 19);
        contentPane.add(lblNewLabel_1);

        // Username text field
        textField = new JTextField();
        textField.setBounds(300, 280, 300, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        // Password label
        JLabel lblNewLabel_2 = new JLabel("Password*");
        lblNewLabel_2.setFont(new Font("Papyrus", Font.BOLD, 14));
        lblNewLabel_2.setForeground(new Color(191, 128, 64)); // Adjusted text color
        lblNewLabel_2.setBounds(300, 320, 100, 19);
        contentPane.add(lblNewLabel_2);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(300, 350, 300, 30);
        contentPane.add(passwordField);

        // Login button
        JButton btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(0, 100, 200));
        btnNewButton.setBounds(300, 400, 100, 30);
        btnNewButton.setFocusable(false);
        contentPane.add(btnNewButton);

        // Login button action listener
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());

                // Run the login validation in a separate thread
                new Thread(() -> {
                    boolean isValid = validateLogin(username, password);

                    // Update the UI on the Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> {
                        if (isValid) {
                            JOptionPane.showMessageDialog(Login_Page.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            // Close the login page
                            dispose();
                            // Open the "Our Services" page
                            new Our_Services_Page().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(Login_Page.this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }).start();
            }
        });

        // Reset button
        JButton btnNewButton_1 = new JButton("Reset");
        btnNewButton_1.setFont(new Font("Papyrus", Font.BOLD, 14));
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setBackground(new Color(220, 20, 60));
        btnNewButton_1.setBounds(500, 400, 100, 30);
        btnNewButton_1.setFocusable(false);
        contentPane.add(btnNewButton_1);

        // Reset button action listener
        btnNewButton_1.addActionListener(e -> {
            textField.setText(null);
            passwordField.setText(null);
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setBounds(400, 600, 100, 30);
        contentPane.add(backButton);

        // Back button action listener
        backButton.addActionListener(e -> {
            dispose();
            // Navigate to the welcome page (you can modify this logic as needed)
        });
    }

    // Method to validate login credentials against the database
    public boolean validateLogin(String username, String password) {
        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
        String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            return rs.next(); // Returns true if a match is found
        } catch (Exception ex) {
            SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(this, "Database Connection Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE)
            );
            return false;
        }
    }

    // Main method to launch the login page
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login_Page().setVisible(true));
    }
}
