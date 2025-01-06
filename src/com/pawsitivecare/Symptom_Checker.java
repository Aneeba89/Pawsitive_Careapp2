package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Symptom_Checker extends JFrame {
    private JPanel contentPane;
    public JTextField symptomInput;
    public JTextArea resultArea;
    private Image backgroundImage;
    private JFrame previousFrame; // Reference to the previous window

    // Constructor with a reference to the previous window
    public Symptom_Checker(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 700);

        // Load the background image
        backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\dogpic.jpg").getImage();

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
        JLabel titleLabel = new JLabel("Symptom Checker");
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 44));
        titleLabel.setForeground(new Color(191, 128, 64));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 50, 850, 80);
        contentPane.add(titleLabel);

        // Input field label
        JLabel symptomLabel = new JLabel("Enter Pet Symptoms:");
        symptomLabel.setFont(new Font("Papyrus", Font.BOLD, 20));
        symptomLabel.setForeground(new Color(191, 128, 64));
        symptomLabel.setBounds(250, 200, 200, 30);
        contentPane.add(symptomLabel);

        // Symptom input field
        symptomInput = new JTextField();
        symptomInput.setFont(new Font("Helvetica", Font.PLAIN, 16));
        symptomInput.setBounds(250, 240, 350, 30);
        contentPane.add(symptomInput);

        // Check button
        JButton checkButton = new JButton("Check");
        checkButton.setFont(new Font("Papyrus", Font.BOLD, 16));
        checkButton.setForeground(Color.WHITE);
        checkButton.setBackground(new Color(0, 100, 200));
        checkButton.setBounds(370, 290, 100, 30);
        checkButton.setFocusable(false);
        contentPane.add(checkButton);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Helvetica", Font.PLAIN, 14));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(250, 340, 350, 200);
        contentPane.add(scrollPane);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setBounds(370, 570, 100, 30);
        contentPane.add(backButton);

        // Back button action listener
        backButton.addActionListener(e -> {
            dispose(); // Close the current frame
            if (previousFrame != null) {
                previousFrame.setVisible(true); // Show the previous frame
            }
        });

        // Check button action listener
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String symptoms = symptomInput.getText().trim();
                if (!symptoms.isEmpty()) {
                    checkSymptoms(symptoms);
                } else {
                    resultArea.setText("Please enter a symptom to search.");
                }
            }
        });
    }

    // Method to check symptoms in the database
    public void checkSymptoms(String symptoms) {
        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
        String query = "SELECT disease, precautions FROM Symptoms WHERE symptom LIKE ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + symptoms + "%");
            ResultSet rs = stmt.executeQuery();

            StringBuilder results = new StringBuilder();
            while (rs.next()) {
                String disease = rs.getString("disease");
                String precautions = rs.getString("precautions");
                results.append("Disease: ").append(disease).append("\n")
                        .append("Precautions: ").append(precautions).append("\n\n");
            }

            if (results.length() > 0) {
                resultArea.setText(results.toString());
            } else {
                resultArea.setText("No matching symptoms found in the database.");
            }
        } catch (SQLException e) {
            SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE)
            );
        }
    }

    // Main method to launch the symptom checker page
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame previousFrame = new Login_Page(); // Replace with your previous frame
            previousFrame.setVisible(true);
            new Symptom_Checker(previousFrame).setVisible(true);
            previousFrame.setVisible(false); // Hide the previous frame
        });
    }
}
