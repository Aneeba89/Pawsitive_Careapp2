//package com.pawsitivecare;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.net.Socket;
//
//class FAQFrame extends JFrame {
//    private JPanel contentPane;
//    private JTextField inputField;
//    private JTextArea responseArea;
//    private JButton submitButton;
//    private Image backgroundImage;
//    private Socket socket;
//    private BufferedReader reader;
//    private BufferedWriter writer;
//
//    public FAQFrame() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(0, 0, 900, 800);
//
//        // Load the background image
//        backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Pictures\\pet-care-background.jpg").getImage();
//
//        // Set up the content pane
//        contentPane = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//
//                // Create a gradient overlay
//                Graphics2D g2d = (Graphics2D) g;
//                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 0, 150), 0, getHeight(), new Color(0, 0, 0, 50));
//                g2d.setPaint(gradient);
//                g2d.fillRect(0, 0, getWidth(), getHeight());
//            }
//        };
//
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//
//        // Response area
//        responseArea = new JTextArea();
//        responseArea.setEditable(false);
//        responseArea.setFont(new Font("Helvetica", Font.PLAIN, 16));
//        responseArea.setLineWrap(true);
//        responseArea.setWrapStyleWord(true);
//        responseArea.setBounds(200, 50, 500, 400);
//        responseArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        contentPane.add(responseArea);
//
//        // Input field
//        inputField = new JTextField();
//        inputField.setBounds(200, 500, 500, 30);
//        contentPane.add(inputField);
//
//        // Submit button
//        submitButton = new JButton("Submit");
//        submitButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
//        submitButton.setForeground(Color.WHITE);
//        submitButton.setBackground(new Color(0, 100, 200));
//        submitButton.setBounds(200, 550, 100, 30);
//        contentPane.add(submitButton);
//
//        // Set up server connection
//        setupServerConnection();
//
//        // Button action listener
//        submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String query = inputField.getText();
//                if (query.isEmpty()) {
//                    responseArea.setText("Please enter a question.");
//                    return;
//                }
//
//                // Start a new thread to handle server communication
//                new Thread(() -> sendQueryToServer(query)).start();
//            }
//        });
//    }
//
//    private void setupServerConnection() {
//        try {
//            socket = new Socket("localhost", 6000); // Connect to the server
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            responseArea.setText("Connected to the Pet Care FAQ Server!");
//        } catch (IOException e) {
//            responseArea.setText("Failed to connect to the server. Please try again.");
//        }
//    }
//
//    private void sendQueryToServer(String query) {
//        try {
//            // Send the query to the server
//            writer.write(query + "\n");
//            writer.flush();
//
//            // Read the response from the server
//            String response = reader.readLine();
//
//            // Update the UI on the Event Dispatch Thread
//            SwingUtilities.invokeLater(() -> {
//                if (response != null) {
//                    responseArea.append("You: " + query + "\n"); // Add the user's query
//                    responseArea.append("Server: " + response + "\n\n"); // Add the server's response
//                } else {
//                    responseArea.append("No response received from the server.\n");
//                }
//                inputField.setText(""); // Clear input field
//            });
//
//            if (query.equalsIgnoreCase("Exit")) {
//                closeConnection();
//            }
//        } catch (IOException e) {
//            SwingUtilities.invokeLater(() -> responseArea.append("Error communicating with the server.\n"));
//        }
//    }
//
//    private void closeConnection() {
//        try {
//            if (socket != null) socket.close();
//            if (reader != null) reader.close();
//            if (writer != null) writer.close();
//            SwingUtilities.invokeLater(() -> responseArea.setText("Disconnected from the server."));
//        } catch (IOException e) {
//            SwingUtilities.invokeLater(() -> responseArea.setText("Error while closing the connection."));
//        }
//    }
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                FAQFrame frame = new FAQFrame();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}
//



package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

class FAQFrame extends JFrame {
    private JPanel contentPane;
    private JTextField inputField;
    private JTextArea responseArea;
    private JButton submitButton;
    private Image backgroundImage;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public FAQFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 900, 800);

        // Load the background image
        backgroundImage = new ImageIcon("\"C:\\Users\\javer\\OneDrive\\Desktop\\Aneeba Project 062\\SCDproject\\img_paws.jpg\"").getImage();

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

        // Response area
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        responseArea.setFont(new Font("Helvetica", Font.PLAIN, 16));
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        responseArea.setBounds(200, 50, 500, 400);
        responseArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        contentPane.add(responseArea);

        // Input field
        inputField = new JTextField();
        inputField.setBounds(200, 500, 500, 30);
        contentPane.add(inputField);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(0, 100, 200));
        submitButton.setBounds(200, 550, 100, 30);
        contentPane.add(submitButton);

        // Set up server connection
        setupServerConnection();

        // Button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = inputField.getText();
                if (query.isEmpty()) {
                    responseArea.setText("Please enter a question.");
                    return;
                }

                // Start a new thread to handle server communication
                new Thread(() -> sendQueryToServer(query)).start();
            }
        });
    }

    private void setupServerConnection() {
        try {
            socket = new Socket("localhost", 6000); // Connect to the server
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            responseArea.setText("Connected to the Pet Care FAQ Server!");
        } catch (IOException e) {
            responseArea.setText("Failed to connect to the server. Please try again.");
        }
    }

    private void sendQueryToServer(String query) {
        try {
            // Send the query to the server
            writer.write(query + "\n");
            writer.flush();

            // Read the response from the server
            String response = reader.readLine();

            // Update the UI on the Event Dispatch Thread
            SwingUtilities.invokeLater(() -> {
                if (response != null) {
                    responseArea.append("You: " + query + "\n"); // Add the user's query
                    responseArea.append("Server: " + response + "\n\n"); // Add the server's response
                } else {
                    responseArea.append("No response received from the server.\n");
                }
                inputField.setText(""); // Clear input field
            });

            if (query.equalsIgnoreCase("Exit")) {
                closeConnection();
            }
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> responseArea.append("Error communicating with the server.\n"));
        }
    }

    private void closeConnection() {
        try {
            if (socket != null) socket.close();
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            SwingUtilities.invokeLater(() -> responseArea.setText("Disconnected from the server."));
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> responseArea.setText("Error while closing the connection."));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FAQFrame frame = new FAQFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

