///*package com.pawsitivecare;
//
//import javax.swing.*;
//import java.awt.*;
//import java.sql.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//public class PetHealthRecordTracker extends JFrame {
//
//    private JTextField petNameField, petAgeField, petBreedField, petAllergiesField, petDiseasesField, lastVetVisitField;
//    private JButton editButton, deleteButton, saveButton, backButton;
//    private final int userId;
//
//    public PetHealthRecordTracker(int userId) {
//        this.userId = userId;
//
//        setTitle("Pet Health Record Tracker");
//        setSize(800, 700);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Custom JPanel for background
//        Image backgroundImage = new ImageIcon("C:\\path\\to\\img_paws.jpg").getImage();
//        JPanel contentPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//            }
//        };
//        contentPanel.setLayout(new GridBagLayout());
//        setContentPane(contentPanel);
//
//        // Title Label
//        JLabel titleLabel = new JLabel("Pet Details", JLabel.CENTER);
//        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 30));
//        GridBagConstraints gbcTitle = new GridBagConstraints();
//        gbcTitle.gridx = 0;
//        gbcTitle.gridy = 0;
//        gbcTitle.gridwidth = 2;
//        gbcTitle.insets = new Insets(20, 100, 70, 10);
//        contentPanel.add(titleLabel, gbcTitle);
//
//        // Add Fields
//        addField(contentPanel, "Pet Name:", petNameField = new JTextField(20), 1);
//        addField(contentPanel, "Pet Age:", petAgeField = new JTextField(20), 2);
//        addField(contentPanel, "Pet Breed:", petBreedField = new JTextField(20), 3);
//        addField(contentPanel, "Allergies:", petAllergiesField = new JTextField(20), 4);
//        addField(contentPanel, "Diseases:", petDiseasesField = new JTextField(20), 5);
//        addField(contentPanel, "Last Vet Visit:", lastVetVisitField = new JTextField(20), 6);
//
//        // Add Buttons
//        editButton = createButton("Edit", 150);
//        saveButton = createButton("Save", 150);
//        deleteButton = createButton("Delete", 150);
//        backButton = createButton("Back", 150);
//
//        GridBagConstraints gbcButton = new GridBagConstraints();
//        gbcButton.insets = new Insets(20, 20, 20, 20);
//        gbcButton.anchor = GridBagConstraints.CENTER;
//
//        gbcButton.gridx = 0;
//        gbcButton.gridy = 7;
//        contentPanel.add(editButton, gbcButton);
//
//        gbcButton.gridx = 1;
//        contentPanel.add(saveButton, gbcButton);
//
//        gbcButton.gridx = 2;
//        contentPanel.add(deleteButton, gbcButton);
//
//        gbcButton.gridx = 1;
//        gbcButton.gridy = 8;
//        contentPanel.add(backButton, gbcButton);
//
//        // Disable Fields Initially
//        setFieldsEditable(false);
//
//        // Load Data
//        loadPetData();
//
//        // Button Actions
//        editButton.addActionListener(e -> setFieldsEditable(true));
//        saveButton.addActionListener(e -> savePetData());
//        deleteButton.addActionListener(e -> deletePetData());
//        backButton.addActionListener(e -> dispose());
//    }
//
//    private void addField(JPanel panel, String labelText, JTextField field, int y) {
//        GridBagConstraints gbcLabel = new GridBagConstraints();
//        gbcLabel.gridx = 0;
//        gbcLabel.gridy = y;
//        gbcLabel.anchor = GridBagConstraints.WEST;
//        gbcLabel.insets = new Insets(10, 10, 10, 10);
//
//        JLabel label = new JLabel(labelText);
//        label.setFont(new Font("Papyrus", Font.BOLD, 18));
//        panel.add(label, gbcLabel);
//
//        GridBagConstraints gbcField = new GridBagConstraints();
//        gbcField.gridx = 1;
//        gbcField.gridy = y;
//        gbcField.fill = GridBagConstraints.HORIZONTAL;
//        gbcField.insets = new Insets(10, 10, 10, 10);
//
//        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//        panel.add(field, gbcField);
//    }
//
//    private JButton createButton(String text, int width) {
//        JButton button = new JButton(text);
//        button.setPreferredSize(new Dimension(width, 40));
//        return button;
//    }
//
//    private void setFieldsEditable(boolean editable) {
//        petNameField.setEditable(editable);
//        petAgeField.setEditable(editable);
//        petBreedField.setEditable(editable);
//        petAllergiesField.setEditable(editable);
//        petDiseasesField.setEditable(editable);
//        lastVetVisitField.setEditable(editable);
//    }
//
//    private void loadPetData() {
//        String query = "SELECT pet_name, pet_age, pet_breed, pet_allergies, pet_diseases, last_vet_visit FROM PetHealthRecords WHERE user_id = " + userId;
//        try (Connection conn = createConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
//            if (rs.next()) {
//                petNameField.setText(rs.getString("pet_name"));
//                petAgeField.setText(String.valueOf(rs.getInt("pet_age")));
//                petBreedField.setText(rs.getString("pet_breed"));
//                petAllergiesField.setText(rs.getString("pet_allergies"));
//                petDiseasesField.setText(rs.getString("pet_diseases"));
//                lastVetVisitField.setText(rs.getString("last_vet_visit"));
//            } else {
//                JOptionPane.showMessageDialog(this, "No pet record found for this user.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error loading pet data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void savePetData() {
//        if (!validateFields()) {
//            return; // Exit if validation fails
//        }
//
//        String query = "UPDATE PetHealthRecords SET pet_name = '" + petNameField.getText() +
//                "', pet_age = " + petAgeField.getText() +
//                ", pet_breed = '" + petBreedField.getText() +
//                "', pet_allergies = '" + petAllergiesField.getText() +
//                "', pet_diseases = '" + petDiseasesField.getText() +
//                "', last_vet_visit = '" + lastVetVisitField.getText() +
//                "' WHERE user_id = " + userId;
//
//        System.out.println("Generated SQL Query: " + query); // Debug: Print the query
//
//        try (Connection conn = createConnection(); Statement stmt = conn.createStatement()) {
//            int rows = stmt.executeUpdate(query);
//            if (rows > 0) {
//                JOptionPane.showMessageDialog(this, "Pet data updated successfully.");
//            } else {
//                JOptionPane.showMessageDialog(this, "No record updated. Check user ID.", "Update Failed", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Print detailed error information
//            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//
//    private void deletePetData() {
//        String query = "DELETE FROM PetHealthRecords WHERE user_id = " + userId;
//        try (Connection conn = createConnection(); Statement stmt = conn.createStatement()) {
//            int rows = stmt.executeUpdate(query);
//            if (rows > 0) {
//                JOptionPane.showMessageDialog(this, "Pet record deleted successfully.");
//                dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "No record deleted. Check user ID.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private boolean validateFields() {
//        if (petNameField.getText().trim().isEmpty() ||
//                petAgeField.getText().trim().isEmpty() ||
//                petBreedField.getText().trim().isEmpty() ||
//                petAllergiesField.getText().trim().isEmpty() ||
//                petDiseasesField.getText().trim().isEmpty() ||
//                lastVetVisitField.getText().trim().isEmpty()) {
//
//            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Validation Error", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
//
//        try {
//            int age = Integer.parseInt(petAgeField.getText());
//            if (age < 0) {
//                JOptionPane.showMessageDialog(this, "Age must be a positive number!", "Validation Error", JOptionPane.WARNING_MESSAGE);
//                return false;
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Age must be a valid number!", "Validation Error", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
//
//        if (!isValidDate(lastVetVisitField.getText())) {
//            JOptionPane.showMessageDialog(this, "Last Vet Visit must be in YYYY-MM-DD format!", "Validation Error", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean isValidDate(String date) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            sdf.setLenient(false);
//            sdf.parse(date);
//            return true;
//        } catch (ParseException e) {
//            return false;
//        }
//    }
//
//    private Connection createConnection() throws SQLException {
//        // Replace with your database credentials and URL
//        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
//        String username = "Javeria";
//        String password = "JAVERIANOOR123";
//        return DriverManager.getConnection(url, username, password);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new PetHealthRecordTracker(1).setVisible(true));
//    }
//}*/
//
//package com.pawsitivecare;
//
//import javax.swing.*;
//import java.awt.*;
//import java.sql.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//public class PetHealthRecordTracker extends JFrame {
//
//    private JTextField petNameField, petAgeField, petBreedField, petAllergiesField, petDiseasesField, lastVetVisitField;
//    private JButton editButton, deleteButton, saveButton, backButton;
//    private final int userId;
//
//    public PetHealthRecordTracker(int userId) {
//        this.userId = userId;
//
//        setTitle("Pet Health Record Tracker");
//        setSize(800, 700);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Custom JPanel for background
//        Image backgroundImage = new ImageIcon("C:\\path\\to\\img_paws.jpg").getImage();
//        JPanel contentPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//            }
//        };
//        contentPanel.setLayout(new GridBagLayout());
//        setContentPane(contentPanel);
//
//        // Title Label
//        JLabel titleLabel = new JLabel("Pet Details", JLabel.CENTER);
//        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 30));
//        GridBagConstraints gbcTitle = new GridBagConstraints();
//        gbcTitle.gridx = 0;
//        gbcTitle.gridy = 0;
//        gbcTitle.gridwidth = 2;
//        gbcTitle.insets = new Insets(20, 100, 70, 10);
//        contentPanel.add(titleLabel, gbcTitle);
//
//        // Add Fields
//        addField(contentPanel, "Pet Name:", petNameField = new JTextField(20), 1);
//        addField(contentPanel, "Pet Age:", petAgeField = new JTextField(20), 2);
//        addField(contentPanel, "Pet Breed:", petBreedField = new JTextField(20), 3);
//        addField(contentPanel, "Allergies:", petAllergiesField = new JTextField(20), 4);
//        addField(contentPanel, "Diseases:", petDiseasesField = new JTextField(20), 5);
//        addField(contentPanel, "Last Vet Visit:", lastVetVisitField = new JTextField(20), 6);
//
//        // Add Buttons
//        editButton = createButton("Edit", 150);
//        saveButton = createButton("Save", 150);
//        deleteButton = createButton("Delete", 150);
//        backButton = createButton("Back", 150);
//
//        GridBagConstraints gbcButton = new GridBagConstraints();
//        gbcButton.insets = new Insets(20, 20, 20, 20);
//        gbcButton.anchor = GridBagConstraints.CENTER;
//
//        gbcButton.gridx = 0;
//        gbcButton.gridy = 7;
//        contentPanel.add(editButton, gbcButton);
//
//        gbcButton.gridx = 1;
//        contentPanel.add(saveButton, gbcButton);
//
//        gbcButton.gridx = 2;
//        contentPanel.add(deleteButton, gbcButton);
//
//        gbcButton.gridx = 1;
//        gbcButton.gridy = 8;
//        contentPanel.add(backButton, gbcButton);
//
//        // Disable Fields Initially
//        setFieldsEditable(false);
//
//        // Load Data
//        loadPetData();
//
//        // Button Actions
//        editButton.addActionListener(e -> setFieldsEditable(true));
//        saveButton.addActionListener(e -> savePetData());
//        deleteButton.addActionListener(e -> deletePetData());
//        backButton.addActionListener(e -> dispose());
//    }
//
//    private void addField(JPanel panel, String labelText, JTextField field, int y) {
//        GridBagConstraints gbcLabel = new GridBagConstraints();
//        gbcLabel.gridx = 0;
//        gbcLabel.gridy = y;
//        gbcLabel.anchor = GridBagConstraints.WEST;
//        gbcLabel.insets = new Insets(10, 10, 10, 10);
//
//        JLabel label = new JLabel(labelText);
//        label.setFont(new Font("Papyrus", Font.BOLD, 18));
//        panel.add(label, gbcLabel);
//
//        GridBagConstraints gbcField = new GridBagConstraints();
//        gbcField.gridx = 1;
//        gbcField.gridy = y;
//        gbcField.fill = GridBagConstraints.HORIZONTAL;
//        gbcField.insets = new Insets(10, 10, 10, 10);
//
//        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
//        panel.add(field, gbcField);
//    }
//
//    private JButton createButton(String text, int width) {
//        JButton button = new JButton(text);
//        button.setPreferredSize(new Dimension(width, 40));
//        return button;
//    }
//
//    private void setFieldsEditable(boolean editable) {
//        petNameField.setEditable(editable);
//        petAgeField.setEditable(editable);
//        petBreedField.setEditable(editable);
//        petAllergiesField.setEditable(editable);
//        petDiseasesField.setEditable(editable);
//        lastVetVisitField.setEditable(editable);
//    }
//
//    private void loadPetData() {
//        String query = "SELECT pet_name, pet_age, pet_breed, pet_allergies, pet_diseases, last_vet_visit FROM PetHealthRecords WHERE user_id = " + userId;
//        try (Connection conn = createConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
//            if (rs.next()) {
//                petNameField.setText(rs.getString("pet_name"));
//                petAgeField.setText(String.valueOf(rs.getInt("pet_age")));
//                petBreedField.setText(rs.getString("pet_breed"));
//                petAllergiesField.setText(rs.getString("pet_allergies"));
//                petDiseasesField.setText(rs.getString("pet_diseases"));
//                lastVetVisitField.setText(rs.getString("last_vet_visit"));
//            } else {
//                JOptionPane.showMessageDialog(this, "No pet record found for this user.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error loading pet data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private void savePetData() {
//        if (!validateFields()) {
//            return; // Exit if validation fails
//        }
//
//        String query = "UPDATE PetHealthRecords SET pet_name = ?, pet_age = ?, pet_breed = ?, pet_allergies = ?, pet_diseases = ?, last_vet_visit = ? WHERE user_id = ?";
//
//        try (Connection conn = createConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setString(1, petNameField.getText());
//            pstmt.setInt(2, Integer.parseInt(petAgeField.getText()));
//            pstmt.setString(3, petBreedField.getText());
//            pstmt.setString(4, petAllergiesField.getText());
//            pstmt.setString(5, petDiseasesField.getText());
//            pstmt.setString(6, lastVetVisitField.getText());
//            pstmt.setInt(7, userId);
//
//            int rows = pstmt.executeUpdate();
//            if (rows > 0) {
//                JOptionPane.showMessageDialog(this, "Pet data updated successfully.");
//            } else {
//                JOptionPane.showMessageDialog(this, "No record updated. Check user ID.", "Update Failed", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Print detailed error information
//            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//
//    private void deletePetData() {
//        String query = "DELETE FROM PetHealthRecords WHERE user_id = " + userId;
//        try (Connection conn = createConnection(); Statement stmt = conn.createStatement()) {
//            int rows = stmt.executeUpdate(query);
//            if (rows > 0) {
//                JOptionPane.showMessageDialog(this, "Pet record deleted successfully.");
//                dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "No record deleted. Check user ID.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private boolean validateFields() {
//        if (petNameField.getText().trim().isEmpty() ||
//                petAgeField.getText().trim().isEmpty() ||
//                petBreedField.getText().trim().isEmpty() ||
//                petAllergiesField.getText().trim().isEmpty() ||
//                petDiseasesField.getText().trim().isEmpty() ||
//                lastVetVisitField.getText().trim().isEmpty()) {
//
//            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Validation Error", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
//
//        try {
//            int age = Integer.parseInt(petAgeField.getText());
//            if (age < 0) {
//                JOptionPane.showMessageDialog(this, "Age must be a positive number!", "Validation Error", JOptionPane.WARNING_MESSAGE);
//                return false;
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Age must be a valid number!", "Validation Error", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
//
//        if (!isValidDate(lastVetVisitField.getText())) {
//            JOptionPane.showMessageDialog(this, "Last Vet Visit must be in YYYY-MM-DD format!", "Validation Error", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
//
//        return true;
//    }
//
//    private boolean isValidDate(String date) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            sdf.setLenient(false);
//            sdf.parse(date);
//            return true;
//        } catch (ParseException e) {
//            return false;
//        }
//    }
//
//    private Connection createConnection() throws SQLException {
//        // Replace with your database credentials and URL
//        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
//        String username = "Javeria";
//        String password = "JAVERIANOOR123";
//        return DriverManager.getConnection(url, username, password);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new PetHealthRecordTracker(1).setVisible(true));
//    }
//}
//

package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PetHealthRecordTracker extends JFrame {

    private JTextField petNameField, petAgeField, petBreedField, petAllergiesField, petDiseasesField, lastVetVisitField;
    private JButton editButton, deleteButton, saveButton, backButton;
    private final int userId;

    public PetHealthRecordTracker(int userId) {
        this.userId = userId;

        setTitle("Pet Health Record Tracker");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Custom JPanel for background
        Image backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\pic10.jpg").getImage();
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPanel.setLayout(new GridBagLayout());
        setContentPane(contentPanel);

        // Title Label
        JLabel titleLabel = new JLabel("Pet Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 30));
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;
        gbcTitle.insets = new Insets(20, 100, 70, 10);
        contentPanel.add(titleLabel, gbcTitle);

        // Add Fields
        addField(contentPanel, "Pet Name:", petNameField = new JTextField(20), 1);
        addField(contentPanel, "Pet Age:", petAgeField = new JTextField(20), 2);
        addField(contentPanel, "Pet Breed:", petBreedField = new JTextField(20), 3);
        addField(contentPanel, "Allergies:", petAllergiesField = new JTextField(20), 4);
        addField(contentPanel, "Diseases:", petDiseasesField = new JTextField(20), 5);
        addField(contentPanel, "Last Vet Visit:", lastVetVisitField = new JTextField(20), 6);

        // Add Buttons
        editButton = createButton("Edit", 150);
        saveButton = createButton("Save", 150);
        deleteButton = createButton("Delete", 150);
        backButton = createButton("Back", 150);

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(20, 20, 20, 20);
        gbcButton.anchor = GridBagConstraints.CENTER;

        gbcButton.gridx = 0;
        gbcButton.gridy = 7;
        contentPanel.add(editButton, gbcButton);

        gbcButton.gridx = 1;
        contentPanel.add(saveButton, gbcButton);

        gbcButton.gridx = 2;
        contentPanel.add(deleteButton, gbcButton);

        gbcButton.gridx = 1;
        gbcButton.gridy = 8;
        contentPanel.add(backButton, gbcButton);

        // Disable Fields Initially
        setFieldsEditable(false);

        // Load Data
        loadPetData();

        // Button Actions
        editButton.addActionListener(e -> setFieldsEditable(true));
        saveButton.addActionListener(e -> savePetData());
        deleteButton.addActionListener(e -> deletePetData());
        backButton.addActionListener(e -> dispose());  // Close only this window
    }

    private void addField(JPanel panel, String labelText, JTextField field, int y) {
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = y;
        gbcLabel.anchor = GridBagConstraints.WEST;
        gbcLabel.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Papyrus", Font.BOLD, 18));
        panel.add(label, gbcLabel);

        GridBagConstraints gbcField = new GridBagConstraints();
        gbcField.gridx = 1;
        gbcField.gridy = y;
        gbcField.fill = GridBagConstraints.HORIZONTAL;
        gbcField.insets = new Insets(10, 10, 10, 10);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(field, gbcField);
    }

    private JButton createButton(String text, int width) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, 40));
        return button;
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
        String query = "SELECT pet_name, pet_age, pet_breed, pet_allergies, pet_diseases, last_vet_visit FROM PetHealthRecords WHERE user_id = " + userId;
        try (Connection conn = createConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                petNameField.setText(rs.getString("pet_name"));
                petAgeField.setText(String.valueOf(rs.getInt("pet_age")));
                petBreedField.setText(rs.getString("pet_breed"));
                petAllergiesField.setText(rs.getString("pet_allergies"));
                petDiseasesField.setText(rs.getString("pet_diseases"));
                lastVetVisitField.setText(rs.getString("last_vet_visit"));
            } else {
                JOptionPane.showMessageDialog(this, "No pet record found for this user.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading pet data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void savePetData() {
        if (!validateFields()) {
            return; // Exit if validation fails
        }

        String query = "INSERT INTO PetHealthRecords (user_id, pet_name, pet_age, pet_breed, pet_allergies, pet_diseases, last_vet_visit) VALUES ("
                + userId + ", '"
                + petNameField.getText() + "', "
                + petAgeField.getText() + ", '"
                + petBreedField.getText() + "', '"
                + petAllergiesField.getText() + "', '"
                + petDiseasesField.getText() + "', '"
                + lastVetVisitField.getText() + "')";

        try (Connection conn = createConnection(); Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(query);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Pet data inserted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert pet data.", "Insert Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print detailed error information
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void deletePetData() {
        String query = "DELETE FROM PetHealthRecords WHERE user_id = " + userId;
        try (Connection conn = createConnection(); Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Pet record deleted successfully.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No record deleted. Check user ID.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (petNameField.getText().trim().isEmpty() ||
                petAgeField.getText().trim().isEmpty() ||
                petBreedField.getText().trim().isEmpty() ||
                petAllergiesField.getText().trim().isEmpty() ||
                petDiseasesField.getText().trim().isEmpty() ||
                lastVetVisitField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int age = Integer.parseInt(petAgeField.getText());
            if (age < 0) {
                JOptionPane.showMessageDialog(this, "Age must be a positive number!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!isValidDate(lastVetVisitField.getText())) {
            JOptionPane.showMessageDialog(this, "Last Vet Visit must be in YYYY-MM-DD format!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private Connection createConnection() throws SQLException {
        // Replace with your database credentials and URL
        String url = "jdbc:sqlserver://Javeria\\SQLEXPRESS;databaseName=PetApp;encrypt=false;trustServerCertificate=true;user=Javeria;password=JAVERIANOOR123";
        String username = "Javeria";
        String password = "JAVERIANOOR123";
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PetHealthRecordTracker(1).setVisible(true));
    }
}

