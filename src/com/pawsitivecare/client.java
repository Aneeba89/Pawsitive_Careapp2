package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class client extends JFrame {
    private JPanel contentPane;
    private JTextField inputField;
    private JTextArea responseArea;
    private JButton submitButton;
    private JButton backButton;
    private Image backgroundImage;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public client() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 900, 800);

        // Load the background image
        backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\dogpic10.jpg").getImage();

        // Set up the content pane
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                // Create a gradient overlay
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 0, 150), 0, getHeight(), new Color(0, 0, 0, 50));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Response area setup
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        responseArea.setFont(new Font("Helvetica", Font.PLAIN, 16));
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        responseArea.setBounds(200, 50, 500, 400);
        responseArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        contentPane.add(responseArea);

        // Input field setup
        inputField = new JTextField();
        inputField.setBounds(200, 500, 500, 30);
        contentPane.add(inputField);

        // Submit button setup (slightly bigger and repositioned)
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Helvetica", Font.PLAIN, 16));  // Slightly larger font
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(0, 100, 200));
        submitButton.setBounds(250, 550, 150, 40);  // Larger size and shifted right
        contentPane.add(submitButton);

        // Back button setup (slightly bigger and repositioned)
        backButton = new JButton("Back");
        backButton.setFont(new Font("Helvetica", Font.PLAIN, 16));   // Slightly larger font
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(200, 50, 50));
        backButton.setBounds(450, 550, 150, 40);  // Larger size and shifted right
        contentPane.add(backButton);

        // Server connection setup
        setupServerConnection();

        // Button action listeners
        submitButton.addActionListener(e -> {
            String query = inputField.getText();
            if (query.isEmpty()) {
                responseArea.setText("Please enter a question number.");
                return;
            }
            new Thread(() -> sendQueryToServer(query)).start();
        });

        // Back button now only closes the window
        backButton.addActionListener(e -> {
            dispose(); // Close the current window only
        });
    }

    private void setupServerConnection() {
        try {
            socket = new Socket("localhost", 6000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Request questions from the server
            writer.write("Get Questions\n");
            writer.flush();

            responseArea.setText("Connected to the Pet Care FAQ Server!\n\n");
            String line;
            while ((line = reader.readLine()) != null && !line.equals("END")) {
                responseArea.append(line + "\n");
            }
            responseArea.append("\nPlease enter the number of the question to receive an answer.\n");

        } catch (IOException e) {
            responseArea.setText("Failed to connect to the server. Please try again.");
        }
    }

    private void sendQueryToServer(String query) {
        try {
            writer.write(query + "\n");
            writer.flush();
            String response = reader.readLine();

            SwingUtilities.invokeLater(() -> {
                if (response != null) {
                    responseArea.append("\nYou selected: " + query + "\n");
                    responseArea.append(response + "\n\n");
                } else {
                    responseArea.append("No response received from the server.\n");
                }
                inputField.setText("");
            });
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> responseArea.append("Error communicating with the server.\n"));
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                client frame = new client();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}