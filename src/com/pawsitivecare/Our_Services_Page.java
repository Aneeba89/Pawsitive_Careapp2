////
////package com.pawsitivecare;
////
////import javax.swing.*;
////import java.awt.*;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////
////public class Our_Services_Page extends JFrame {
////
////    // Constructor to set up the "Our Services" page
////    public Our_Services_Page() {
////        setTitle("Our Services - Pawsitive Care");
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        setSize(900, 800);  // Set the size to 900x800
////        setLocationRelativeTo(null); // Center the frame
////
////        // Set background color
////        JPanel backgroundPanel = new JPanel();
////        backgroundPanel.setLayout(new BorderLayout());
////
////        // Add title
////        JLabel titleLabel = new JLabel("Our Services", SwingConstants.CENTER);
////        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 36));
////        titleLabel.setForeground(new Color(0, 0, 0)); // Green color for text
////        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
////        backgroundPanel.add(titleLabel, BorderLayout.NORTH);
////
////        // Add button panel
////        JPanel buttonPanel = new JPanel();
////        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20)); // 2 rows, 2 columns
////        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
////        buttonPanel.setOpaque(false);
////
////        // Create buttons for services
////        JButton healthRecordButton = new JButton("Pet Health Record Tracker");
////        JButton careTipsButton = new JButton("Pet Care Tips & Articles");
////        JButton emergencyContactsButton = new JButton("Emergency Rescue Contacts");
////        JButton symptomCheckerButton = new JButton("Symptom Checker");
////
////        // Button styles
////        Font buttonFont = new Font("Papyrus", Font.BOLD, 26);
////        Color buttonColor = new Color(172, 115, 57); // Orange color for buttons
////        Color textColor = Color.WHITE;
////
////        JButton[] buttons = {healthRecordButton, careTipsButton, emergencyContactsButton, symptomCheckerButton};
////        for (JButton button : buttons) {
////            button.setFont(buttonFont);
////            button.setBackground(buttonColor);
////            button.setForeground(textColor);
////            button.setFocusPainted(false);
////            button.setBorder(BorderFactory.createRaisedBevelBorder());
////            buttonPanel.add(button);
////        }
////
////        // Add button panel to the center of the frame
////        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
////
////        // Add action listeners for each button
////        healthRecordButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Navigate to Pet Health Record Tracker Page"));
////
////        careTipsButton.addActionListener(e -> {
////            PetCareArticles.main(new String[]{}); // Open the "pet articles" page
////        });
////
////        emergencyContactsButton.addActionListener(e -> {
////            Emrgency_Rescue_Page.main(new String[]{}); // Open the "Emergency Rescue Contacts" page
////        });
////
////        symptomCheckerButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Navigate to Symptom Checker Page"));
////
////        // Add "Back" button
////        JButton backButton = new JButton("Back");
////        backButton.setFont(buttonFont);
////        backButton.setBackground(new Color(0, 0, 0)); // Crimson color
////        backButton.setForeground(Color.WHITE);
////        backButton.setFocusPainted(false);
////        backButton.setBorder(BorderFactory.createRaisedBevelBorder());
////        backButton.setPreferredSize(new Dimension(100, 50));
////
////        backButton.addActionListener(e -> {
////            dispose(); // Close the current page
////            // Navigate to the previous page (add navigation logic here)
////        });
////
////        JPanel backPanel = new JPanel();
////        backPanel.setOpaque(false);
////        backPanel.add(backButton);
////
////        backgroundPanel.add(backPanel, BorderLayout.SOUTH);
////
////        // Set the content pane and make the frame visible
////        setContentPane(backgroundPanel);
////    }
////
////    public static void main(String[] args) {
////        SwingUtilities.invokeLater(() -> new Our_Services_Page().setVisible(true));
////    }
////}
////
////
//
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
//        titleLabel.setForeground(new Color(0, 0, 0)); // Green color for text
//        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
//        backgroundPanel.add(titleLabel, BorderLayout.NORTH);
//
//        // Add button panel
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20)); // 2 rows, 2 columns
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
//        Color buttonColor = new Color(172, 115, 57); // Orange color for buttons
//        Color textColor = Color.WHITE;
//
//        // Button setup
//        JButton[] buttons = {healthRecordButton, careTipsButton, emergencyContactsButton, symptomCheckerButton};
//        for (JButton button : buttons) {
//            button.setFont(buttonFont);
//            button.setBackground(buttonColor);
//            button.setForeground(textColor);
//            button.setFocusPainted(false);
//            button.setBorder(BorderFactory.createRaisedBevelBorder());
//            buttonPanel.add(button);
//        }
//
//        // Add button panel to the center of the frame
//        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
//
//        // Add action listeners for each button
//        healthRecordButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Navigate to Pet Health Record Tracker Page"));
//        careTipsButton.addActionListener(e -> PetCareArticles.main(new String[]{})); // Open the "pet articles" page
//        emergencyContactsButton.addActionListener(e -> Emrgency_Rescue_Page.main(new String[]{})); // Open the "Emergency Rescue Contacts" page
//     // action listener for Symptom Checker button
//        symptomCheckerButton.addActionListener(e -> {
//            Symptom_Checker symptomCheckerPage = new Symptom_Checker(this); // Pass the current frame as the previous frame
//            symptomCheckerPage.setVisible(true); // Show the Symptom Checker page
//            setVisible(false); // Hide the current frame
//        });
//
//        // Add "Back" button
//        JButton backButton = new JButton("Back");
//        backButton.setFont(buttonFont);
//        backButton.setBackground(new Color(0, 0, 0)); // Crimson color
//        backButton.setForeground(Color.WHITE);
//        backButton.setFocusPainted(false);
//        backButton.setBorder(BorderFactory.createRaisedBevelBorder());
//        backButton.setPreferredSize(new Dimension(100, 50));
//
//        backButton.addActionListener(e -> {
//            dispose(); // Close the current page
//            // Navigate to the previous page (add navigation logic here)
//        });
//
//        JPanel backPanel = new JPanel();
//        backPanel.setOpaque(false);
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
//

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

        // Using the Factory to create buttons
        ServiceButtonFactory buttonFactory = new ConcreteServiceButtonFactory();
        ServiceButton healthRecordButton = buttonFactory.createButton("Pet Health Record Tracker");
        ServiceButton careTipsButton = buttonFactory.createButton("Pet Care Tips & Articles");
        ServiceButton emergencyContactsButton = buttonFactory.createButton("Emergency Rescue Contacts");
        ServiceButton symptomCheckerButton = buttonFactory.createButton("Symptom Checker");

        // Button styles
        Font buttonFont = new Font("Papyrus", Font.BOLD, 26);
        Color buttonColor = new Color(172, 115, 57); // Orange color for buttons
        Color textColor = Color.WHITE;

        // Button setup
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
        healthRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pass the userId when creating the PetHealthRecordTracker page
                int userId = 1;  // Replace this with the actual userId from your application context
                PetHealthRecordTracker tracker = new PetHealthRecordTracker(userId);
                tracker.setVisible(true);  // Show the Pet Health Record Tracker page
                dispose();  // Close the current "Our Services" page
            }
        });

        careTipsButton.addActionListener(e -> PetCareArticles.main(new String[]{})); // Open the "pet articles" page
        emergencyContactsButton.addActionListener(e -> Emrgency_Rescue_Page.main(new String[]{})); // Open the "Emergency Rescue Contacts" page

        // Action listener for Symptom Checker button
        symptomCheckerButton.addActionListener(e -> {
            Symptom_Checker symptomCheckerPage = new Symptom_Checker(this); // Pass the current frame as the previous frame
            symptomCheckerPage.setVisible(true); // Show the Symptom Checker page
            setVisible(false); // Hide the current frame
        });

        // Add "Back" button
        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setBackground(new Color(0, 0, 0)); // Crimson color
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createRaisedBevelBorder());
        backButton.setPreferredSize(new Dimension(100, 50));

        backButton.addActionListener(e -> {
            dispose(); // Close the current page
            // Navigate to the previous page (add navigation logic here)
        });

        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        backPanel.add(backButton);

        backgroundPanel.add(backPanel, BorderLayout.SOUTH);

        // Set the content pane and make the frame visible
        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Our_Services_Page().setVisible(true));
    }
}
