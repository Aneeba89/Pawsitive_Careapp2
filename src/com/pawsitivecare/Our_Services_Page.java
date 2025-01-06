//package com.pawsitivecare;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//// ServiceButton class
//class ServiceButton extends JButton {
//    public ServiceButton(String text) {
//        super(text);
//    }
//}
//
//// Factory class for creating buttons
//abstract class ServiceButtonFactory {
//    public abstract ServiceButton createButton(String serviceName);
//}
//
//class ConcreteServiceButtonFactory extends ServiceButtonFactory {
//    @Override
//    public ServiceButton createButton(String serviceName) {
//        return new ServiceButton(serviceName);
//    }
//}
//
//public class Our_Services_Page extends JFrame {
//
//    // Constructor to set up the "Our Services" page
//    public Our_Services_Page() {
//        setTitle("Our Services - Pawsitive Care");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(900, 800);  // Set the size to 900x800
//        setLocationRelativeTo(null); // Center the frame
//
//        // Set background color
//        JPanel backgroundPanel = new JPanel();
//        backgroundPanel.setLayout(new BorderLayout());
//
//        // Add title
//        JLabel titleLabel = new JLabel("Our Services", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 36));
//        titleLabel.setForeground(new Color(0, 0, 0));
//        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
//        backgroundPanel.add(titleLabel, BorderLayout.NORTH);
//
//        // Add button panel (VERTICAL arrangement now)
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(4, 1, 20, 20)); // 4 rows, 1 column for vertical alignment
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200)); // Centered with padding
//        buttonPanel.setOpaque(false);
//
//        // Using the Factory to create buttons
//        ServiceButtonFactory buttonFactory = new ConcreteServiceButtonFactory();
//        ServiceButton healthRecordButton = buttonFactory.createButton("Pet Health Record Tracker");
//        ServiceButton careTipsButton = buttonFactory.createButton("Pet Care Tips & Articles");
//        ServiceButton emergencyContactsButton = buttonFactory.createButton("Emergency Rescue Contacts");
//        ServiceButton symptomCheckerButton = buttonFactory.createButton("Symptom Checker");
//
//        // Button styles
//        Font buttonFont = new Font("Papyrus", Font.BOLD, 26);
//        Color buttonColor = new Color(172, 115, 57);
//        Color textColor = Color.WHITE;
//
//        // Button setup (resized and centered vertically)
//        JButton[] buttons = {healthRecordButton, careTipsButton, emergencyContactsButton, symptomCheckerButton};
//        for (JButton button : buttons) {
//            button.setFont(buttonFont);
//            button.setBackground(buttonColor);
//            button.setForeground(textColor);
//            button.setFocusPainted(false);
//            button.setBorder(BorderFactory.createRaisedBevelBorder());
//            button.setPreferredSize(new Dimension(90, 45));  // Adjusted button size for vertical layout
//            buttonPanel.add(button);
//        }
//
//        // Add button panel to the center of the frame
//        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
//
//        // Add action listeners for each button
//        healthRecordButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int userId = 1;  // Replace this with the actual userId from your application context
//                PetHealthRecordTracker tracker = new PetHealthRecordTracker(userId);
//                tracker.setVisible(true);
//                dispose();
//            }
//        });
//
//        careTipsButton.addActionListener(e -> PetCareArticles.main(new String[]{}));
//        emergencyContactsButton.addActionListener(e -> Emrgency_Rescue_Page.main(new String[]{}));
//
//        symptomCheckerButton.addActionListener(e -> {
//            Symptom_Checker symptomCheckerPage = new Symptom_Checker(this);
//            symptomCheckerPage.setVisible(true);
//            setVisible(false);
//        });
//
//        // Add "Back" button at the bottom
//        JButton backButton = new JButton("Back");
//        backButton.setFont(buttonFont);
//        backButton.setBackground(new Color(0, 0, 0));
//        backButton.setForeground(Color.WHITE);
//        backButton.setFocusPainted(false);
//        backButton.setBorder(BorderFactory.createRaisedBevelBorder());
//        backButton.setPreferredSize(new Dimension(150, 50));  // Larger back button for consistency
//
//        backButton.addActionListener(e -> {
//            dispose(); // Close the current page
//        });
//
//        // Back button panel with padding
//        JPanel backPanel = new JPanel();
//        backPanel.setOpaque(false);
//        backPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
//        backPanel.add(backButton);
//
//        backgroundPanel.add(backPanel, BorderLayout.SOUTH);
//
//        // Set the content pane and make the frame visible
//        setContentPane(backgroundPanel);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new Our_Services_Page().setVisible(true));
//    }
//}

package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ServiceButton class
class ServiceButton extends JButton {
    public ServiceButton(String text) {
        super(text);
    }
}

// Factory class for creating buttons
abstract class ServiceButtonFactory {
    public abstract ServiceButton createButton(String serviceName);
}

class ConcreteServiceButtonFactory extends ServiceButtonFactory {
    @Override
    public ServiceButton createButton(String serviceName) {
        return new ServiceButton(serviceName);
    }
}

public class Our_Services_Page extends JFrame {

    // Constructor to set up the "Our Services" page
    public Our_Services_Page() {
        setTitle("Our Services - Pawsitive Care");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);  // Set the size to 900x800
        setLocationRelativeTo(null); // Center the frame

        // Create a custom JPanel with a background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\pic10.jpg"); // Replace with actual path to your image
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this); // Stretch image to fit the panel
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Add title
        JLabel titleLabel = new JLabel("Our Services", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 38));
        titleLabel.setForeground(new Color(0, 0, 0)); // Black color for text
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // Add button panel (VERTICAL arrangement now)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 20, 20)); // 4 rows, 1 column for vertical alignment
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200)); // Centered with padding
        buttonPanel.setOpaque(false);

        // Using the Factory to create buttons
        ServiceButtonFactory buttonFactory = new ConcreteServiceButtonFactory();
        ServiceButton healthRecordButton = buttonFactory.createButton("Pet Health Record Tracker");
        ServiceButton careTipsButton = buttonFactory.createButton("Pet Care Tips & Articles");
        ServiceButton emergencyContactsButton = buttonFactory.createButton("Emergency Rescue Contacts");
        ServiceButton symptomCheckerButton = buttonFactory.createButton("Symptom Checker");

        // Button styles
        Font buttonFont = new Font("Papyrus", Font.BOLD, 26);
        Color buttonColor = new Color(172, 115, 57);
        Color textColor = Color.WHITE;

        // Button setup (resized and centered vertically)
        JButton[] buttons = {healthRecordButton, careTipsButton, emergencyContactsButton, symptomCheckerButton};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(buttonColor);
            button.setForeground(textColor);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createRaisedBevelBorder());
            button.setPreferredSize(new Dimension(90, 45));  // Adjusted button size for vertical layout
            buttonPanel.add(button);
        }

        // Add button panel to the center of the frame
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners for each button
        healthRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = 1;  // Replace this with the actual userId from your application context
                PetHealthRecordTracker tracker = new PetHealthRecordTracker(userId);
                tracker.setVisible(true);
                dispose();
            }
        });

        careTipsButton.addActionListener(e -> PetCareArticles.main(new String[]{}));
        emergencyContactsButton.addActionListener(e -> Emrgency_Rescue_Page.main(new String[]{}));

        symptomCheckerButton.addActionListener(e -> {
            Symptom_Checker symptomCheckerPage = new Symptom_Checker(this);
            symptomCheckerPage.setVisible(true);
            setVisible(false);
        });

        // Add "Back" button at the bottom
        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createRaisedBevelBorder());
        backButton.setBounds(400, 460, 100, 30);
        backButton.setPreferredSize(new Dimension(150, 50));  // Larger back button for consistency

        backButton.addActionListener(e -> {
            dispose(); // Close the current page
        });

        // Back button panel with padding
        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        backPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        backPanel.add(backButton);

        backgroundPanel.add(backPanel, BorderLayout.SOUTH);

        // Set the content pane and make the frame visible
        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Our_Services_Page().setVisible(true));
    }
}
