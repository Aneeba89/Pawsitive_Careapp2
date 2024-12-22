package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PetHealthRecordTracker extends JFrame {

    private JTextField petNameField, petAgeField, petBreedField, petAllergiesField, petDiseasesField, lastVetVisitField;
    private JButton editButton, deleteButton, saveButton, backButton;
    private int userId; // To store the logged-in user's id

    public PetHealthRecordTracker(int userId) {
        this.userId = userId;  // Set the user_id based on the logged-in user

        setTitle("Pet Health Record Tracker");
        setSize(800, 700); // Set the size to match the Welcome_Page size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create a custom JPanel with background painting
        Image backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\Aneeba Project 062\\SCDproject\\img_paws.jpg").getImage();
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better field placement
        setContentPane(contentPanel); // Set the custom panel as the content pane

        // Define font for labels and text fields
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16); // Font for labels and fields

        // Create the "Pet Details" label and add it at the top center
        JLabel titleLabel = new JLabel("Pet Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 30)); // Set font for title
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;  // Make it span across multiple columns
        gbcTitle.insets = new Insets(20, 100, 70, 10); // Add some padding
        contentPanel.add(titleLabel, gbcTitle);

        // Pet health details labels and text fields
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding for better spacing

        JLabel petNameLabel = new JLabel("Pet Name:");
        petNameLabel.setFont(new Font("Papyrus", Font.BOLD, 18)); // Set font for labels
        contentPanel.add(petNameLabel, getConstraints(0, 1));
        petNameField = new JTextField(20);  // Adjusted size for the text field
        petNameField.setFont(fieldFont);  // Set font for text fields
        contentPanel.add(petNameField, getConstraints(1, 1));

        JLabel petAgeLabel = new JLabel("Pet Age:");
        petAgeLabel.setFont(new Font("Papyrus", Font.BOLD, 18)); // Set font for labels
        contentPanel.add(petAgeLabel, getConstraints(0, 2));
        petAgeField = new JTextField(20);  // Adjusted size for the text field
        petAgeField.setFont(fieldFont);  // Set font for text fields
        contentPanel.add(petAgeField, getConstraints(1, 2));

        JLabel petBreedLabel = new JLabel("Pet Breed:");
        petBreedLabel.setFont(new Font("Papyrus", Font.BOLD, 18)); // Set font for labels
        contentPanel.add(petBreedLabel, getConstraints(0, 3));
        petBreedField = new JTextField(20);  // Adjusted size for the text field
        petBreedField.setFont(fieldFont);  // Set font for text fields
        contentPanel.add(petBreedField, getConstraints(1, 3));

        JLabel petAllergiesLabel = new JLabel("Allergies:");
        petAllergiesLabel.setFont(new Font("Papyrus", Font.BOLD, 18)); // Set font for labels
        contentPanel.add(petAllergiesLabel, getConstraints(0, 4));
        petAllergiesField = new JTextField(20);  // Adjusted size for the text field
        petAllergiesField.setFont(fieldFont);  // Set font for text fields
        contentPanel.add(petAllergiesField, getConstraints(1, 4));

        JLabel petDiseasesLabel = new JLabel("Diseases:");
        petDiseasesLabel.setFont(new Font("Papyrus", Font.BOLD, 18)); // Set font for labels
        contentPanel.add(petDiseasesLabel, getConstraints(0, 5));
        petDiseasesField = new JTextField(20);  // Adjusted size for the text field
        petDiseasesField.setFont(fieldFont);  // Set font for text fields
        contentPanel.add(petDiseasesField, getConstraints(1, 5));

        JLabel lastVetVisitLabel = new JLabel("Last Vet Visit:");
        lastVetVisitLabel.setFont(new Font("Papyrus", Font.BOLD, 18)); // Set font for labels
        contentPanel.add(lastVetVisitLabel, getConstraints(0, 6));
        lastVetVisitField = new JTextField(20);  // Adjusted size for the text field
        lastVetVisitField.setFont(fieldFont);  // Set font for text fields
        contentPanel.add(lastVetVisitField, getConstraints(1, 6));

        // Create and add buttons for editing, saving, deleting, and going back
        editButton = new JButton("Edit");
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");

        // Adjust button size
        Dimension buttonSize = new Dimension(150, 40);  // Reduced size
        editButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        // Button grid constraints (equally spaced)
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(20, 20, 20, 20); // Add spacing between buttons
        gbcButton.anchor = GridBagConstraints.CENTER;

        // Add buttons in the grid with equal space
        gbcButton.gridx = 0;
        gbcButton.gridy = 7;
        contentPanel.add(editButton, gbcButton);

        gbcButton.gridx = 1;
        contentPanel.add(saveButton, gbcButton);

        gbcButton.gridx = 2;
        contentPanel.add(deleteButton, gbcButton);

        // Back button below the other buttons
        gbcButton.gridx = 1;
        gbcButton.gridy = 8; // Place it below the other buttons
        contentPanel.add(backButton, gbcButton);

        // Initially disable the fields to avoid accidental edits
        setFieldsEditable(false);

        // Load the pet data for the user
        loadPetData();

        // Action listener for the Edit button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFieldsEditable(true); // Enable fields for editing
            }
        });

        // Action listener for the Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePetData();
            }
        });

        // Action listener for the Delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePetData();
            }
        });

        // Action listener for the Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your logic for going back (e.g., navigate to the previous window)
                dispose(); // This will close the current window
            }
        });
    }

    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private void setFieldsEditable(boolean editable) {
        petNameField.setEditable(editable);
        petAgeField.setEditable(editable);
        petBreedField.setEditable(editable);
        petAllergiesField.setEditable(editable);
        petDiseasesField.setEditable(editable);
        lastVetVisitField.setEditable(editable);
    }

    private void loadPetData() {
        // Query to fetch pet data for the user
        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
        String query = "SELECT pet_name, pet_age, pet_breed, pet_allergies, pet_diseases, last_vet_visit FROM PetHealthRecords WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);  // Set the user_id parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Fill the fields with pet data
                petNameField.setText(rs.getString("pet_name"));
                petAgeField.setText(String.valueOf(rs.getInt("pet_age")));
                petBreedField.setText(rs.getString("pet_breed"));
                petAllergiesField.setText(rs.getString("pet_allergies"));
                petDiseasesField.setText(rs.getString("pet_diseases"));
                lastVetVisitField.setText(rs.getString("last_vet_visit"));
            } else {
                // If no data is found, notify the user
                JOptionPane.showMessageDialog(this, "No pet record found for this user.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading pet data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void savePetData() {
        String petAgeText = petAgeField.getText();

        // Validate that the pet age is a valid integer
        int petAge = -1;  // Default value for invalid input
        try {
            petAge = Integer.parseInt(petAgeText); // Try to parse the age
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for pet age.", "Invalid Age", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if the age is invalid
        }

        // Continue with saving data if age is valid
        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
        String query = "UPDATE PetHealthRecords SET pet_name = ?, pet_age = ?, pet_breed = ?, pet_allergies = ?, pet_diseases = ?, last_vet_visit = ? WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the updated values
            stmt.setString(1, petNameField.getText());
            stmt.setInt(2, petAge);  // Use the validated pet age
            stmt.setString(3, petBreedField.getText());
            stmt.setString(4, petAllergiesField.getText());
            stmt.setString(5, petDiseasesField.getText());
            stmt.setString(6, lastVetVisitField.getText());
            stmt.setInt(7, userId);  // Use the user_id

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Pet data updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update pet data.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving pet data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePetData() {
        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
        String query = "DELETE FROM PetHealthRecords WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);  // Use the user_id

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Pet data deleted successfully.");
                dispose();  // Close the current window
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete pet data.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting pet data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Replace with the actual user_id after login
        int loggedInUserId = 1; // Example user_id, you would use the logged-in user's ID
        PetHealthRecordTracker trackerWindow = new PetHealthRecordTracker(loggedInUserId);
        trackerWindow.setVisible(true);
    }
}

