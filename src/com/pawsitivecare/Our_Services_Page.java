package com.pawsitivecare;






         import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
         import java.awt.event.ActionListener;

public class Our_Services_Page {
    public static void main(String[] args) {
        // Create the frame with the same size as the Login page (900x800)
        JFrame frame = new JFrame("Our Services - Pawsitive Care");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 800);  // Set the size to 900x800
        frame.setLocationRelativeTo(null); // Center the frame

        // Set background color
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // Add title
        JLabel titleLabel = new JLabel("Our Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 0, 0)); // Green color for text
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // Add button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20)); // 2 rows, 2 columns
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setOpaque(false);

        // Create buttons for services
        JButton healthRecordButton = new JButton("Pet Health Record Tracker");
        JButton careTipsButton = new JButton("Pet Care Tips & Articles");
        JButton emergencyContactsButton = new JButton("Emergency Rescue Contacts");
        JButton symptomCheckerButton = new JButton("Symptom Checker");

        // Button styles
        Font buttonFont = new Font("Papyrus", Font.BOLD, 26);
        Color buttonColor = new Color(172, 115, 57); // Orange color for buttons
        Color textColor = Color.WHITE;

        JButton[] buttons = {healthRecordButton, careTipsButton, emergencyContactsButton, symptomCheckerButton};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(buttonColor);
            button.setForeground(textColor);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createRaisedBevelBorder());
            buttonPanel.add(button);
        }

        // Add button panel to the center of the frame
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners for each button
        healthRecordButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Navigate to Pet Health Record Tracker Page"));

        careTipsButton.addActionListener(e -> {
            PetCareArticles.main(new String[]{}); // Open the "pet articles " page
        });

        emergencyContactsButton.addActionListener(e -> {
            Emrgency_Rescue_Page.main(new String[]{}); // Open the "Emergency Rescue Contacts" page
        });
        symptomCheckerButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Navigate to Symptom Checker Page"));

        // Add "Back" button
        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setBackground(new Color(0,0,0)); // Crimson color
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createRaisedBevelBorder());
        backButton.setPreferredSize(new Dimension(100, 50));

        backButton.addActionListener(e -> {
            frame.dispose(); // Close the current page
            // Navigate to the previous page (add navigation logic here)
        });

        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        backPanel.add(backButton);

        backgroundPanel.add(backPanel, BorderLayout.SOUTH);

        // Set the content pane and make the frame visible
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}