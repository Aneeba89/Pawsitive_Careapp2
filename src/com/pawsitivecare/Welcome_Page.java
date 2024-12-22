package com.pawsitivecare;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome_Page extends JFrame {

    public Welcome_Page() {
        setTitle("Welcome to PawsitiveCare!");
        setSize(800, 700);  // Set the size to 900x800
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Load background image
        Image backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\Aneeba Project 062\\SCDproject\\PET_IMG7.jpg").getImage();

        // Create a custom JPanel with background painting
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        contentPane.setLayout(new BorderLayout()); // Set layout for the content pane
        setContentPane(contentPane); // Set the custom panel as the content pane

        // Add title label (Centered at the top)
        JLabel titleLabel = new JLabel("Welcome to PawsitiveCare!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 36));
        titleLabel.setForeground(new Color(191, 128, 64));  // Adjusted text color
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(255, 255, 255, 150)); // Semi-transparent white background
        titleLabel.setPreferredSize(new Dimension(900, 100));  // Adjusted width to 900
        contentPane.add(titleLabel, BorderLayout.NORTH);

        // Add description label (Centered below title)
        JLabel descriptionLabel = new JLabel("Your one-stop destination for pet care services.", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Papyrus", Font.ITALIC, 20));
        descriptionLabel.setForeground(new Color(255, 255, 255, 150));  // White color for text
        descriptionLabel.setOpaque(true);  // Make the label opaque to show background color
        descriptionLabel.setBackground(new Color(255, 255, 255, 150)); // Semi-transparent white background
        descriptionLabel.setPreferredSize(new Dimension(900, 60));  // Adjusted width to 900
        contentPane.add(descriptionLabel, BorderLayout.CENTER);

        // Create a panel to hold buttons (Login and Sign Up)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the panel transparent
        buttonPanel.setLayout(new GridBagLayout()); // Use GridBagLayout to center the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 100, 20, 100); // Add padding around the buttons

        // Create buttons for Login and Sign Up
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        // Button styling
        Font buttonFont = new Font("Papyrus", Font.BOLD, 26); // Increased font size for better readability
        Color buttonColor = new Color(0, 0, 0); // Black color for buttons
        Color textColor = new Color(255, 255, 255, 150); // White text

        // Set button font and color
        loginButton.setFont(buttonFont);
        signupButton.setFont(buttonFont);
        loginButton.setBackground(buttonColor);
        signupButton.setBackground(buttonColor);
        loginButton.setForeground(textColor);
        signupButton.setForeground(textColor);
        signupButton.setFocusable(false);
        loginButton.setFocusable(false);

        // Set preferred size for buttons (making them bigger)
        loginButton.setPreferredSize(new Dimension(200, 50));
        signupButton.setPreferredSize(new Dimension(200, 50));

        // Add login button to center of panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(loginButton, gbc);

        // Add signup button to center of panel, below login button
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(signupButton, gbc);

        // Create another panel for other buttons (Our Services and Contact Us)
        JPanel otherButtonsPanel = new JPanel();
        otherButtonsPanel.setOpaque(false); // Make it transparent
        otherButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Center the buttons with space

        // Buttons for "Our Services" and "Contact Us"
//        JButton servicesButton = new JButton("Our Services");
        JButton contactButton = new JButton("Contact Us");

        // Set the font and color for the buttons
//        servicesButton.setFont(buttonFont);
        contactButton.setFont(buttonFont);
//        servicesButton.setBackground(buttonColor);
        contactButton.setBackground(buttonColor);
//        servicesButton.setForeground(textColor);
        contactButton.setForeground(textColor);
//        servicesButton.setFocusable(false);
        contactButton.setFocusable(false);

        // Add buttons to the other panel
//        otherButtonsPanel.add(servicesButton);
        otherButtonsPanel.add(contactButton);

        // Add the button panels to the background label
        contentPane.add(buttonPanel, BorderLayout.CENTER);  // Put login and signup in the center
        contentPane.add(otherButtonsPanel, BorderLayout.SOUTH);  // Put other buttons at the bottom

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_Page loginFrame = new Login_Page();
                loginFrame.setVisible(true);
            }
        });

        // Action listener for signup button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Signup_Page signup = new Signup_Page();
                signup.setVisible(true);
            }
        });

        // Action listener for services button
//        servicesButton.addActionListener(e -> {
//            Our_Services_Page.main(new String[]{}); // Open the "Our Services" page
//        });

        // Action listener for contact us button
        contactButton.addActionListener(e -> {
            Contact_Us_Page.main(new String[]{}); // Open the "Contact Us" page
        });
    }

    public void display() {
        setVisible(true); // Display the Welcome page
    }

    public static void main(String[] args) {
        Welcome_Page welcomePage = new Welcome_Page();
        welcomePage.display();
    }
}
