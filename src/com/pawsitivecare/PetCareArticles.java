package com.pawsitivecare;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PetCareArticles extends JFrame {


    // Map to track open article windows
    private final Map<String, JFrame> openArticles = new HashMap<>();

    // Search components
    private JTextField searchField;
    private JButton searchButton;
    private JButton resetButton;
    private JPanel contentPanel;



    public PetCareArticles() {
        setTitle("Pet Care Articles");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Custom background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\javer\\OneDrive\\Desktop\\pic12.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);  // Set the custom panel as the content pane

        // Title
        JLabel titleLabel = new JLabel("Pet Care Articles", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        titleLabel.setForeground(new Color(0, 100, 200));
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetSearch());

        searchField = new JTextField(70);
        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> filterArticles(searchField.getText().trim()));

        searchPanel.add(resetButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        backgroundPanel.add(searchPanel, BorderLayout.NORTH);

        // Content Panel
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(0, 2, 20, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        populateCategories();

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.PLAIN, 18));
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> dispose());
        backgroundPanel.add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }



    private void populateCategories() {
        // Populate categories with articles
        addCategory(contentPanel, "Dogs", new String[]{
                "Dog Training Basics",
                "Understanding Dog Behavior", // Added article
                "Best Diets for Dogs",
                "Common Health Issues in Dogs",
                "Grooming Tips for Dogs"
        });

        addCategory(contentPanel, "Cats", new String[]{
                "Caring for Your Cat",
                "Understanding Cat Behavior",
                "Common Health Issues in Cats",
                "Best Diets for Cats",
                "Grooming Tips for Cats"
        });

        addCategory(contentPanel, "Birds", new String[]{
                "Caring for Pet Birds",
                "Understanding Bird Behavior",
                "Feeding Your Birds",
                "Common Health Issues in Birds",
                "Training Pet Birds"
        });

        addCategory(contentPanel, "Fish", new String[]{
                "Setting Up an Aquarium",
                "Caring for Tropical Fish",
                "Feeding Your Fish",
                "Common Health Issues in Fish",
                "Water Quality Tips for Fish"
        });
    }


    private void addCategory(JPanel panel, String categoryName, String[] articles) {
        JPanel categoryPanel = new JPanel(new BorderLayout());
        categoryPanel.setBorder(BorderFactory.createTitledBorder(categoryName));
        categoryPanel.setOpaque(false);  // Make the category panel transparent

        JPanel linksPanel = new JPanel();
        linksPanel.setLayout(new BoxLayout(linksPanel, BoxLayout.Y_AXIS));
        linksPanel.setOpaque(false);  // Make the links panel transparent

        for (String article : articles) {
            JButton articleButton = new JButton(article);
            articleButton.setFont(new Font("Arial", Font.BOLD, 14));
            articleButton.setHorizontalAlignment(SwingConstants.LEFT);
            articleButton.setBorderPainted(false);
            articleButton.setBackground(new Color(255, 255, 255, 150)); // Slight transparency for buttons
            articleButton.setForeground(new Color(0, 102, 204));
            articleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            articleButton.addActionListener(e -> openArticle(categoryName, article));

            linksPanel.add(articleButton);
        }

        categoryPanel.add(linksPanel, BorderLayout.CENTER);
        panel.add(categoryPanel);
    }


    private void openArticle(String category, String articleTitle) {
        if (openArticles.containsKey(articleTitle)) {
            JFrame existingFrame = openArticles.get(articleTitle);
            if (existingFrame != null) {
                existingFrame.toFront();
                existingFrame.requestFocus();
                return;
            }
        }

        JFrame articleFrame;
        switch (articleTitle) {
            case "Understanding Dog Behavior":
                articleFrame = new Article_DogBehaviour(); // Open the Understanding Dog Behavior article
                break;
            case "Dog Training Basics":
                articleFrame = new Article_TrainingBasics(); // Open the Dog Training Basics article
                break;
            default:
                articleFrame = createDefaultArticleFrame(category, articleTitle);
                break;
        }

        articleFrame.setVisible(true);
        openArticles.put(articleTitle, articleFrame);

        articleFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                openArticles.remove(articleTitle);
            }
        });
    }

    private JFrame createDefaultArticleFrame(String category, String articleTitle) {
        JFrame articleFrame = new JFrame(articleTitle);
        articleFrame.setSize(600, 400);
        articleFrame.setLocationRelativeTo(null);
        articleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea articleContent = new JTextArea(
                "Category: " + category + "\n\n" +
                        "Article Title: " + articleTitle + "\n\n" +
                        "Content for this article will go here."
        );
        articleContent.setFont(new Font("Arial", Font.BOLD, 16));
        articleContent.setLineWrap(true);
        articleContent.setWrapStyleWord(true);
        articleContent.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(articleContent);
        articleFrame.add(scrollPane);

        return articleFrame;
    }


    // Method to filter articles based on search term
    private void filterArticles(String searchTerm) {
        Component[] components = contentPanel.getComponents();
        boolean anyResultsFound = false; // Flag to track if any results were found

        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel categoryPanel = (JPanel) component;
                Component[] articleButtons = ((JPanel) categoryPanel.getComponent(0)).getComponents();

                boolean anyVisible = false;
                for (Component articleComponent : articleButtons) {
                    if (articleComponent instanceof JButton) {
                        JButton articleButton = (JButton) articleComponent;
                        String buttonText = articleButton.getText().toLowerCase();
                        boolean matches = buttonText.contains(searchTerm.toLowerCase());

                        // Show matching article button, hide others
                        articleButton.setVisible(matches);
                        anyVisible = anyVisible || matches;
                    }
                }

                // Ensure the category panel remains visible if at least one button is visible
                categoryPanel.setVisible(anyVisible);
                anyResultsFound = anyResultsFound || anyVisible;
            }
        }

        // If no results are found, display a message
        if (!anyResultsFound) {
            contentPanel.removeAll(); // Clear existing content
            JLabel noResultsLabel = new JLabel("No search results found", SwingConstants.CENTER);
            noResultsLabel.setFont(new Font("Papyrus", Font.BOLD, 24));
            noResultsLabel.setForeground(Color.RED);
            contentPanel.add(noResultsLabel); // Add the message to the content panel
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }


    // Method to reset the search field and display all articles
    private void resetSearch() {
        searchField.setText("");
        filterArticles("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PetCareArticles::new);
    }
}



