package com.pawsitivecare;
//
//
//import javax.swing.*;
//        import java.awt.*;
//        import java.util.HashMap;
//import java.util.Map;
//
//public class PetCareArticles extends JFrame {
//
//    // Map to keep track of open article windows
//    private final Map<String, JFrame> openArticles = new HashMap<>();
//
//    // Map to store articles with their categories
//    private final Map<String, String> articlesMap = new HashMap<>();
//
//    // Main content panel to display articles
//    private final JPanel contentPanel = new JPanel();
//
//    public PetCareArticles() {
//        setTitle("Pet Care Articles");
//        setSize(900, 600);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null); // Center the frame
//
//        // Main layout
//        setLayout(new BorderLayout());
//
//        // Title label
//        JLabel titleLabel = new JLabel("Pet Care Articles", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 24));
//        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
//        titleLabel.setForeground(new Color(0, 100, 200));
//        add(titleLabel, BorderLayout.NORTH);
//
//        // Add Search Bar Panel
//        JPanel searchPanel = new JPanel(new BorderLayout());
//        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JTextField searchField = new JTextField();
//        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
//        searchPanel.add(searchField, BorderLayout.CENTER);
//
//        JButton searchButton = new JButton("Search");
//        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
//        searchButton.setFocusable(false);
//
//        searchPanel.add(searchButton, BorderLayout.EAST);
//
//        JButton resetButton = new JButton("Reset");
//        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
//        resetButton.setFocusable(false);
//
//        searchPanel.add(resetButton, BorderLayout.WEST);
//
//        add(searchPanel, BorderLayout.NORTH);
//
//        // Main content panel
//        contentPanel.setLayout(new GridLayout(0, 2, 20, 20)); // 2 columns, dynamic rows
//        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // Add categories with articles
//        addCategory(contentPanel, "Dogs", new String[]{
//                "Dog Training Basics",
//                "Understanding Dog Behavior",
//                "Best Diets for Dogs",
//                "Common Health Issues in Dogs",
//                "Grooming Tips for Dogs"
//        });
//
//        addCategory(contentPanel, "Cats", new String[]{
//                "Caring for Your Cat's Coat",
//                "Why Do Cats Purr?",
//                "Feeding Your Cat the Right Way",
//                "Dealing with Cat Anxiety",
//                "Indoor vs Outdoor Cats"
//        });
//
//        addCategory(contentPanel, "Birds", new String[]{
//                "How to Train Your Parrot",
//                "Common Bird Illnesses",
//                "Choosing the Right Bird Cage",
//                "What to Feed Your Bird",
//                "Keeping Your Bird Happy"
//        });
//
//        addCategory(contentPanel, "Fish", new String[]{
//                "Setting Up Your Aquarium",
//                "Caring for Goldfish",
//                "Maintaining Water Quality",
//                "Feeding Your Fish",
//                "Aquarium Decoration Ideas"
//        });
//
//        addCategory(contentPanel, "Rabbits", new String[]{
//                "Creating a Rabbit-Friendly Home",
//                "Rabbit Diet Essentials",
//                "How to Handle Your Rabbit",
//                "Common Rabbit Diseases",
//                "Bonding with Your Rabbit"
//        });
//
//        // Add content panel to scrollable pane
//        JScrollPane scrollPane = new JScrollPane(contentPanel);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Back button
//        JButton backButton = new JButton("Back");
//        backButton.setFont(new Font("Papyrus", Font.PLAIN, 18));
//        backButton.setBackground(new Color(0, 0, 0));
//        backButton.setForeground(Color.WHITE);
//        backButton.addActionListener(e -> dispose()); // Close the articles page
//        backButton.setFocusable(false);
//
//        add(backButton, BorderLayout.SOUTH);
//
//        // Search button action
//        searchButton.addActionListener(e -> {
//            String searchQuery = searchField.getText().trim().toLowerCase();
//            contentPanel.removeAll();
//
//            // Add filtered articles
//            for (Map.Entry<String, String> entry : articlesMap.entrySet()) {
//                String article = entry.getKey();
//                String category = entry.getValue();
//
//                if (article.toLowerCase().contains(searchQuery)) {
//                    addCategory(contentPanel, category, new String[]{article});
//                }
//            }
//
//            // Refresh the UI
//            contentPanel.revalidate();
//            contentPanel.repaint();
//        });
//
//        // Reset button action
//        resetButton.addActionListener(e -> {
//            searchField.setText("");
//            contentPanel.removeAll();
//            addAllCategories();
//            contentPanel.revalidate();
//            contentPanel.repaint();
//        });
//
//        setVisible(true);
//    }
//
//    private void addCategory(JPanel panel, String categoryName, String[] articles) {
//        JPanel categoryPanel = new JPanel(new BorderLayout());
//        categoryPanel.setBorder(BorderFactory.createTitledBorder(categoryName));
//
//        JPanel linksPanel = new JPanel();
//        linksPanel.setLayout(new BoxLayout(linksPanel, BoxLayout.Y_AXIS));
//
//        for (String article : articles) {
//            articlesMap.put(article, categoryName); // Add articles to the map
//
//            JButton articleButton = new JButton(article);
//            articleButton.setFont(new Font("Arial", Font.BOLD, 14));
//            articleButton.setHorizontalAlignment(SwingConstants.LEFT);
//            articleButton.setBorderPainted(false);
//            articleButton.setBackground(Color.WHITE);
//            articleButton.setForeground(new Color(0, 102, 204));
//            articleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//            articleButton.addActionListener(e -> openArticle(categoryName, article));
//            linksPanel.add(articleButton);
//        }
//
//        categoryPanel.add(linksPanel, BorderLayout.CENTER);
//        panel.add(categoryPanel);
//    }
//
//    private void openArticle(String category, String articleTitle) {
//        if (openArticles.containsKey(articleTitle)) {
//            JFrame openFrame = openArticles.get(articleTitle);
//            if (openFrame != null) {
//                openFrame.toFront();
//                openFrame.requestFocus();
//                return;
//            }
//        }
//
//        JFrame articleFrame = new JFrame(articleTitle);
//        articleFrame.setSize(600, 400);
//        articleFrame.setLocationRelativeTo(null);
//        articleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        articleFrame.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosed(java.awt.event.WindowEvent e) {
//                openArticles.remove(articleTitle);
//            }
//        });
//
//        JTextArea articleContent = new JTextArea(
//                "Category: " + category + "\n\n" +
//                        "Article Title: " + articleTitle + "\n\n" +
//                        "Content for this article will go here. You can add detailed information about the article in this window."
//        );
//        articleContent.setFont(new Font("Arial", Font.BOLD, 16));
//        articleContent.setLineWrap(true);
//        articleContent.setWrapStyleWord(true);
//        articleContent.setEditable(false);
//
//        JScrollPane scrollPane = new JScrollPane(articleContent);
//        articleFrame.add(scrollPane);
//        articleFrame.setVisible(true);
//
//        openArticles.put(articleTitle, articleFrame);
//    }
//
//    private void addAllCategories() {
//        addCategory(contentPanel, "Dogs", new String[]{
//                "Dog Training Basics",
//                "Understanding Dog Behavior",
//                "Best Diets for Dogs",
//                "Common Health Issues in Dogs",
//                "Grooming Tips for Dogs"
//        });
//
//        addCategory(contentPanel, "Cats", new String[]{
//                "Caring for Your Cat's Coat",
//                "Why Do Cats Purr?",
//                "Feeding Your Cat the Right Way",
//                "Dealing with Cat Anxiety",
//                "Indoor vs Outdoor Cats"
//        });
//
//        addCategory(contentPanel, "Birds", new String[]{
//                "How to Train Your Parrot",
//                "Common Bird Illnesses",
//                "Choosing the Right Bird Cage",
//                "What to Feed Your Bird",
//                "Keeping Your Bird Happy"
//        });
//
//        addCategory(contentPanel, "Fish", new String[]{
//                "Setting Up Your Aquarium",
//                "Caring for Goldfish",
//                "Maintaining Water Quality",
//                "Feeding Your Fish",
//                "Aquarium Decoration Ideas"
//        });
//
//        addCategory(contentPanel, "Rabbits", new String[]{
//                "Creating a Rabbit-Friendly Home",
//                "Rabbit Diet Essentials",
//                "How to Handle Your Rabbit",
//                "Common Rabbit Diseases",
//                "Bonding with Your Rabbit"
//        });
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(PetCareArticles::new);
//    }
//}


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PetCareArticles extends JFrame {

    // Map to keep track of open article windows
    private final Map<String, JFrame> openArticles = new HashMap<>();

    // Map to store articles with their categories
    private final Map<String, String> articlesMap = new HashMap<>();

    // Main content panel to display articles
    private final JPanel contentPanel = new JPanel();

    public PetCareArticles() {
        setTitle("Pet Care Articles");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Set pastel lavender background color for the frame
        getContentPane().setBackground(new Color(255, 127, 80)); // Pastel lavender color

        // Main layout
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Pet Care Articles", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        titleLabel.setForeground(new Color(0, 100, 200));
        add(titleLabel, BorderLayout.NORTH);

        // Add Search Bar Panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchPanel.add(searchField, BorderLayout.CENTER);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.setFocusable(false);

        searchPanel.add(searchButton, BorderLayout.EAST);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setFocusable(false);

        searchPanel.add(resetButton, BorderLayout.WEST);

        add(searchPanel, BorderLayout.NORTH);

        // Set pastel background for the content panel (articles grid)
        contentPanel.setLayout(new GridLayout(0, 2, 20, 20)); // 2 columns, dynamic rows
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(255, 240, 245)); // Soft pastel pink background

        // Add categories with articles
        addCategory(contentPanel, "Dogs", new String[]{
                "Dog Training Basics",
                "Understanding Dog Behavior",
                "Best Diets for Dogs",
                "Common Health Issues in Dogs",
                "Grooming Tips for Dogs"
        });

        addCategory(contentPanel, "Cats", new String[]{
                "Caring for Your Cat's Coat",
                "Why Do Cats Purr?",
                "Feeding Your Cat the Right Way",
                "Dealing with Cat Anxiety",
                "Indoor vs Outdoor Cats"
        });

        addCategory(contentPanel, "Birds", new String[]{
                "How to Train Your Parrot",
                "Common Bird Illnesses",
                "Choosing the Right Bird Cage",
                "What to Feed Your Bird",
                "Keeping Your Bird Happy"
        });

        addCategory(contentPanel, "Fish", new String[]{
                "Setting Up Your Aquarium",
                "Caring for Goldfish",
                "Maintaining Water Quality",
                "Feeding Your Fish",
                "Aquarium Decoration Ideas"
        });

        addCategory(contentPanel, "Rabbits", new String[]{
                "Creating a Rabbit-Friendly Home",
                "Rabbit Diet Essentials",
                "How to Handle Your Rabbit",
                "Common Rabbit Diseases",
                "Bonding with Your Rabbit"
        });

        // Add content panel to scrollable pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.PLAIN, 18));
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> dispose()); // Close the articles page
        backButton.setFocusable(false);

        add(backButton, BorderLayout.SOUTH);

        // Search button action
        searchButton.addActionListener(e -> {
            String searchQuery = searchField.getText().trim().toLowerCase();
            contentPanel.removeAll();

            // Add filtered articles
            for (Map.Entry<String, String> entry : articlesMap.entrySet()) {
                String article = entry.getKey();
                String category = entry.getValue();

                if (article.toLowerCase().contains(searchQuery)) {
                    addCategory(contentPanel, category, new String[]{article});
                }
            }

            // Refresh the UI
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        // Reset button action
        resetButton.addActionListener(e -> {
            searchField.setText("");
            contentPanel.removeAll();
            addAllCategories();
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        setVisible(true);
    }

    private void addCategory(JPanel panel, String categoryName, String[] articles) {
        JPanel categoryPanel = new JPanel(new BorderLayout());
        categoryPanel.setBorder(BorderFactory.createTitledBorder(categoryName));

        JPanel linksPanel = new JPanel();
        linksPanel.setLayout(new BoxLayout(linksPanel, BoxLayout.Y_AXIS));

        for (String article : articles) {
            articlesMap.put(article, categoryName); // Add articles to the map

            JButton articleButton = new JButton(article);
            articleButton.setFont(new Font("Arial", Font.BOLD, 14));
            articleButton.setHorizontalAlignment(SwingConstants.LEFT);
            articleButton.setBorderPainted(false);
            articleButton.setBackground(Color.WHITE);
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
            JFrame openFrame = openArticles.get(articleTitle);
            if (openFrame != null) {
                openFrame.toFront();
                openFrame.requestFocus();
                return;
            }
        }

        JFrame articleFrame = new JFrame(articleTitle);
        articleFrame.setSize(600, 400);
        articleFrame.setLocationRelativeTo(null);
        articleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        articleFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                openArticles.remove(articleTitle);
            }
        });

        JTextArea articleContent = new JTextArea(
                "Category: " + category + "\n\n" +
                        "Article Title: " + articleTitle + "\n\n" +
                        "Content for this article will go here. You can add detailed information about the article in this window."
        );
        articleContent.setFont(new Font("Arial", Font.BOLD, 16));
        articleContent.setLineWrap(true);
        articleContent.setWrapStyleWord(true);
        articleContent.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(articleContent);
        articleFrame.add(scrollPane);
        articleFrame.setVisible(true);

        openArticles.put(articleTitle, articleFrame);
    }

    private void addAllCategories() {
        addCategory(contentPanel, "Dogs", new String[]{
                "Dog Training Basics",
                "Understanding Dog Behavior",
                "Best Diets for Dogs",
                "Common Health Issues in Dogs",
                "Grooming Tips for Dogs"
        });

        addCategory(contentPanel, "Cats", new String[]{
                "Caring for Your Cat's Coat",
                "Why Do Cats Purr?",
                "Feeding Your Cat the Right Way",
                "Dealing with Cat Anxiety",
                "Indoor vs Outdoor Cats"
        });

        addCategory(contentPanel, "Birds", new String[]{
                "How to Train Your Parrot",
                "Common Bird Illnesses",
                "Choosing the Right Bird Cage",
                "What to Feed Your Bird",
                "Keeping Your Bird Happy"
        });

        addCategory(contentPanel, "Fish", new String[]{
                "Setting Up Your Aquarium",
                "Caring for Goldfish",
                "Maintaining Water Quality",
                "Feeding Your Fish",
                "Aquarium Decoration Ideas"
        });

        addCategory(contentPanel, "Rabbits", new String[]{
                "Creating a Rabbit-Friendly Home",
                "Rabbit Diet Essentials",
                "How to Handle Your Rabbit",
                "Common Rabbit Diseases",
                "Bonding with Your Rabbit"
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PetCareArticles::new);
    }
}
