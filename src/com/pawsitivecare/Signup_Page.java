package com.pawsitivecare;


import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Signup_Page extends JFrame {
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private Image backgroundImage;

    public Signup_Page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 800);

        // Load the background image
        Image backgroundImage = new ImageIcon("C:\\Users\\IDEAL\\OneDrive\\Desktop\\img_paws.jpg").getImage();

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
        JLabel titleLabel = new JLabel("Signup Page");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 44));
        titleLabel.setForeground(new Color(191, 128, 64)); // Adjusted text color
        titleLabel.setBounds(-10, 50, 900, 80);  // Centered at the top
        contentPane.add(titleLabel);

        // Username label
        JLabel usernameLabel = new JLabel("Username*");
        usernameLabel.setFont(new Font("Papyrus", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(191, 128, 64)); // Adjusted text color
        usernameLabel.setBounds(300, 250, 100, 19);  // Centered in the left section
        contentPane.add(usernameLabel);

        // Username text field
        usernameField = new JTextField();
        usernameField.setBounds(300, 280, 300, 30);  // Centered in the left section
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        // Password label
        JLabel passwordLabel = new JLabel("Password*");
        passwordLabel.setFont(new Font("Papyrus", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(191, 128, 64)); // Adjusted text color
        passwordLabel.setBounds(300, 320, 100, 19);  // Centered in the left section
        contentPane.add(passwordLabel);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(300, 350, 300, 30);  // Centered in the left section
        contentPane.add(passwordField);

        // Email label
        JLabel emailLabel = new JLabel("Email*");
        emailLabel.setFont(new Font("Papyrus", Font.BOLD, 14));
        emailLabel.setForeground(new Color(191, 128, 64)); // Adjusted text color
        emailLabel.setBounds(300, 390, 100, 19);  // Centered in the left section
        contentPane.add(emailLabel);

        // Email text field
        emailField = new JTextField();
        emailField.setBounds(300, 420, 300, 30);  // Centered in the left section
        contentPane.add(emailField);
        emailField.setColumns(10);

        // Signup button
        JButton signupButton = new JButton("Signup");
        signupButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(new Color(0, 100, 200));
        signupButton.setBounds(300, 470, 100, 30);  // Positioned below the email field
        contentPane.add(signupButton);

        // Reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(220, 20, 60));
        resetButton.setBounds(500, 470, 100, 30);  // Positioned next to the signup button
        contentPane.add(resetButton);

        // Reset button action listener
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usernameField.setText(null);
                passwordField.setText(null);
                emailField.setText(null);
            }
        });

        // Add Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0,0,0));
        backButton.setBounds(400, 600, 100, 30);  // Positioned below the Reset button
        contentPane.add(backButton);

        // Back button action listener
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current signup page
                // Navigate to the previous page (you can instantiate a new login page or frame here)
                // Example:
                // new LoginPage().setVisible(true); // Add your navigation logic
            }
        });
    }
}
