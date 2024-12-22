package com.pawsitivecare;
import javax.swing.*;
import java.awt.*;






public class Article_TrainingBasics extends JFrame {
    public Article_TrainingBasics() {
        // Set the frame properties
        setTitle("Dog Training Basics - Pawsitive Care");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Add title label
        JLabel titleLabel = new JLabel("Dog Training Basics", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a text area to display the article
        JTextArea articleText = new JTextArea();
        articleText.setEditable(false);
        articleText.setLineWrap(true);
        articleText.setWrapStyleWord(true);
        articleText.setFont(new Font("Serif", Font.BOLD, 16));
        articleText.setForeground(Color.DARK_GRAY);
        articleText.setBackground(new Color(255, 255, 255)); // White background
        articleText.setText(
                "Dog training is an essential part of responsible pet ownership. " +
                        "It helps ensure that your furry friend behaves appropriately and adapts well to your household and lifestyle. " +
                        "Here are some fundamental tips for training your dog:\n\n" +
                        "1. **Start with Basic Commands:** Teach simple commands like 'sit,' 'stay,' 'come,' and 'heel.' " +
                        "Use treats and positive reinforcement to reward good behavior.\n\n" +
                        "2. **Be Consistent:** Consistency is key in dog training. " +
                        "Ensure that all family members use the same commands and rules to avoid confusion.\n\n" +
                        "3. **Use Positive Reinforcement:** Reward your dog with praise, treats, or toys when they follow commands. " +
                        "Avoid punishment, as it can lead to fear and anxiety.\n\n" +
                        "4. **Practice Patience:** Training takes time and effort. " +
                        "Be patient with your dog and understand that learning new behaviors is a gradual process.\n\n" +
                        "5. **Socialize Early:** Introduce your dog to different people, animals, and environments from a young age. " +
                        "This helps them become well-rounded and confident.\n\n" +
                        "Remember, training should be a positive and enjoyable experience for both you and your dog. " +
                        "Building trust and a strong bond with your pet is the foundation of successful training."
        );

        // Add a scroll pane to the text area
        JScrollPane scrollPane = new JScrollPane(articleText);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add a back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.BOLD, 16));
        backButton.setBackground(new Color(0, 0, 0)); // Black background
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createRaisedBevelBorder());
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> dispose()); // Close the window on click

        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        backPanel.add(backButton);
        mainPanel.add(backPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Article_TrainingBasics frame = new Article_TrainingBasics();
            frame.setVisible(true);
        });
    }
}