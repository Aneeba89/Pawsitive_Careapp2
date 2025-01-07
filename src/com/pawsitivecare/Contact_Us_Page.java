//package com.pawsitivecare;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//class DatabaseConfig {
//    public static final String DB_URL = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;";
//    public static final String USER = "Javeria"; // Replace with your database username
//    public static final String PASSWORD = "JAVERIANOOR123"; // Replace with your database password
//}
//
//public class Contact_Us_Page extends JFrame {
//    private JPanel contentPane;
//    private JTextArea messageArea;
//
//    public Contact_Us_Page() {
//        // Set up frame properties
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 900, 800);
//
//        // Set up the content pane with background image
//        contentPane = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Image backgroundImage = new ImageIcon("C:\\Users\\IDEAL\\OneDrive\\Desktop\\img_paws.jpg").getImage();
//                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//            }
//        };
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//
//        // Title label
//        JLabel titleLabel = new JLabel("Contact Us");
//        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 44));
//        titleLabel.setForeground(new Color(0, 0, 0));
//        titleLabel.setBounds(0, 50, 900, 80);
//        contentPane.add(titleLabel);
//
//        // Subtitle label for contact details
//        JLabel subtitleLabel = new JLabel("We are here to help with your pets' needs!");
//        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        subtitleLabel.setFont(new Font("Papyrus", Font.PLAIN, 20));
//        subtitleLabel.setForeground(new Color(0, 0, 0));
//        subtitleLabel.setBounds(0, 130, 900, 40);
//        contentPane.add(subtitleLabel);
//
//        // Contact info section
//        JLabel contactLabel = new JLabel("<html><b>Phone:</b> +1 800 123 4567<br><b>Email:</b> support@pawsitivecare.com<br><b>Address:</b> 123 Pet Care Lane, Happy City, 12345</html>");
//        contactLabel.setFont(new Font("Papyrus", Font.BOLD, 18));
//        contactLabel.setForeground(new Color(172, 115, 57));
//        contactLabel.setBounds(50, 180, 800, 120);
//        contentPane.add(contactLabel);
//
//        // Section for user to write a message
//        JLabel messageLabel = new JLabel("Leave us a message:");
//        messageLabel.setFont(new Font("Papyrus", Font.BOLD, 18));
//        messageLabel.setForeground(new Color(172, 115, 57));
//        messageLabel.setBounds(50, 320, 200, 30);
//        contentPane.add(messageLabel);
//
//        // Message text area
//        messageArea = new JTextArea();
//        messageArea.setFont(new Font("Papyrus", Font.PLAIN, 16));
//        messageArea.setLineWrap(true);
//        messageArea.setWrapStyleWord(true);
//        messageArea.setBackground(new Color(255, 255, 255, 180));  // Light translucent background
//        messageArea.setBounds(50, 360, 800, 150);
//        contentPane.add(messageArea);
//
//        // Submit button
//        JButton submitButton = new JButton("Submit");
//        submitButton.setFont(new Font("Papyrus", Font.BOLD, 18));
//        submitButton.setForeground(Color.WHITE);
//        submitButton.setBackground(new Color(220, 20, 60));
//        submitButton.setBounds(350, 530, 200, 40);
//        contentPane.add(submitButton);
//
//        // Action listener for submit button
//        submitButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String message = messageArea.getText().trim();
//                String username = JOptionPane.showInputDialog("Enter your username:");
//
//                if (!message.isEmpty() && username != null && !username.trim().isEmpty()) {
//                    boolean isInserted = insertFeedback(username.trim(), message);
//                    if (isInserted) {
//                        JOptionPane.showMessageDialog(contentPane, "Thank you for your feedback! We will get back to you soon.");
//                        messageArea.setText(""); // Clear the message area
//                    } else {
//                        JOptionPane.showMessageDialog(contentPane, "Failed to save your feedback. Please try again.");
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(contentPane, "Please enter a message and username before submitting.");
//                }
//            }
//        });
//
//        // Back button
//        JButton backButton = new JButton("Back");
//        backButton.setFont(new Font("Papyrus", Font.BOLD, 14));
//        backButton.setForeground(Color.WHITE);
//        backButton.setBackground(new Color(0, 0, 0));
//        backButton.setBounds(350, 600, 200, 40);
//        contentPane.add(backButton);
//
//        // Back button action listener
//        backButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                dispose(); // Close the current page
//                // Navigate back to the previous page, such as the main menu or home page
//                // new HomePage().setVisible(true); // Uncomment and modify as necessary
//            }
//        });
//    }
//
//    private boolean insertFeedback(String username, String message) {
//        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;";
//        String query = "INSERT INTO Feedback (username, message) VALUES (?, ?)";
//
//        try (Connection conn = DriverManager.getConnection(url, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, username);
//            stmt.setString(2, message);
//
//            return stmt.executeUpdate() > 0; // Returns true if insertion is successful
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Contact_Us_Page frame = new Contact_Us_Page();
//            frame.setVisible(true);
//        });
//    }
//}



package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// DatabaseConfig for connection strings
class DatabaseConfig {
    public static final String DB_URL = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;";
    public static final String USER = "Javeria"; // Replace with your database username
    public static final String PASSWORD = "JAVERIANOOR123"; // Replace with your database password
}

// Singleton for database connection
class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Establish a connection only once
                connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

public class Contact_Us_Page extends JFrame {
    private JPanel contentPane;
    private JTextArea messageArea;

    public Contact_Us_Page() {
        // Set up frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 700);

        // Set up the content pane with background image
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\catpaws.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title label
        JLabel titleLabel = new JLabel("Contact Us");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 44));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBounds(0, 50, 900, 80);
        contentPane.add(titleLabel);

        // Subtitle label for contact details
        JLabel subtitleLabel = new JLabel("We are here to help with your pets' needs!");
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Papyrus", Font.BOLD, 20));
        subtitleLabel.setForeground(new Color(255,255,255));
        subtitleLabel.setBounds(0, 130, 900, 40);
        contentPane.add(subtitleLabel);

        // Contact info section
        JLabel contactLabel = new JLabel("<html><b>Phone:</b> +1 800 123 4567<br><b>Email:</b> support@pawsitivecare.com<br><b>Address:</b> 123 Pet Care Lane, Happy City, 12345</html>");
        contactLabel.setFont(new Font("Papyrus", Font.BOLD, 18));
        contactLabel.setForeground(new Color(0, 0, 0));
        contactLabel.setBounds(50, 180, 800, 120);
        contentPane.add(contactLabel);

        // Section for user to write a message
        JLabel messageLabel = new JLabel("Leave us a message:");
        messageLabel.setFont(new Font("Papyrus", Font.BOLD, 18));
        messageLabel.setForeground(new Color(0, 0, 0));
        messageLabel.setBounds(50, 320, 200, 30);
        contentPane.add(messageLabel);

        // Message text area
        messageArea = new JTextArea();
        messageArea.setFont(new Font("Papyrus", Font.PLAIN, 16));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBackground(new Color(255, 255, 255, 180));  // Light translucent background
        messageArea.setBounds(50, 360, 800, 150);
        contentPane.add(messageArea);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Papyrus", Font.BOLD, 18));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(220, 20, 60));
        submitButton.setBounds(350, 530, 200, 40);
        contentPane.add(submitButton);

        // Action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = messageArea.getText().trim();
                String username = JOptionPane.showInputDialog("Enter your username:");

                if (!message.isEmpty() && username != null && !username.trim().isEmpty()) {
                    boolean isInserted = insertFeedback(username.trim(), message);
                    if (isInserted) {
                        JOptionPane.showMessageDialog(contentPane, "Thank you for your feedback! We will get back to you soon.");
                        messageArea.setText(""); // Clear the message area
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Failed to save your feedback. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please enter a message and username before submitting.");
                }
            }
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setBounds(350, 600, 200, 40);
        contentPane.add(backButton);

        // Back button action listener
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current page
                // Navigate back to the previous page, such as the main menu or home page
                // new HomePage().setVisible(true); // Uncomment and modify as necessary
            }
        });
    }

    private boolean insertFeedback(String username, String message) {
        String query = "INSERT INTO Feedback (username, message) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); // Get connection from Singleton
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, message);

            return stmt.executeUpdate() > 0; // Returns true if insertion is successful
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Contact_Us_Page frame = new Contact_Us_Page();
            frame.setVisible(true);
        });
    }
}

