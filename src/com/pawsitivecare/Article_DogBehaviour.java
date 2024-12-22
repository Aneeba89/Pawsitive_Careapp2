package com.pawsitivecare;
import javax.swing.*;
        import java.awt.*;

public class Article_DogBehaviour extends JFrame {

    public Article_DogBehaviour() {
        // Set up the frame
        setTitle("Understanding Dog Behavior");
        setSize(800, 700); // Match size with other pages
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Main layout
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Understanding Dog Behavior", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Papyrus", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 100, 200));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Article content
        JTextArea articleContent = new JTextArea();
        articleContent.setFont(new Font("Arial", Font.PLAIN, 18));
        articleContent.setLineWrap(true);
        articleContent.setWrapStyleWord(true);
        articleContent.setEditable(false);
        articleContent.setText(
                "Understanding Dog Behavior\n\n" +
                        "Dogs are complex creatures with their own ways of communicating, expressing emotions, " +
                        "and responding to their environment. Understanding your dog's behavior is key to building " +
                        "a strong bond and ensuring their well-being.\n\n" +
                        "1. **Body Language:**\n" +
                        "   Dogs use their bodies to express emotions. A wagging tail can indicate happiness, " +
                        "   while a tucked tail may signal fear or submission. Observe their ears, posture, " +
                        "   and eye contact to better interpret their feelings.\n\n" +
                        "2. **Vocalizations:**\n" +
                        "   Barking, growling, whining, or howling can each mean something different. Pay attention " +
                        "   to the pitch, frequency, and context. For example, barking can signal excitement, " +
                        "   a warning, or a need for attention.\n\n" +
                        "3. **Social Behavior:**\n" +
                        "   Dogs are naturally social animals and rely on their pack instincts. They may exhibit " +
                        "   dominance or submission in interactions with humans and other dogs. Proper socialization " +
                        "   is essential for a well-behaved pet.\n\n" +
                        "4. **Tail Wagging Myths:**\n" +
                        "   While tail wagging is often associated with happiness, it’s not always positive. The " +
                        "   speed, direction, and posture of the tail can indicate a range of emotions, including " +
                        "   fear or aggression.\n\n" +
                        "5. **Chewing and Digging:**\n" +
                        "   These are natural behaviors that help dogs explore and relieve stress. Providing toys, " +
                        "   exercise, and a stimulating environment can reduce destructive tendencies.\n\n" +
                        "6. **Understanding Fear and Aggression:**\n" +
                        "   Dogs often display aggression or fear when they feel threatened or insecure. Recognizing " +
                        "   triggers and providing consistent training can help alleviate such behaviors.\n\n" +
                        "7. **Training and Positive Reinforcement:**\n" +
                        "   Positive reinforcement helps dogs learn desired behaviors. Rewards such as treats, " +
                        "   praise, and affection encourage them to repeat good behavior.\n\n" +
                        "8. **Building Trust:**\n" +
                        "   Dogs thrive in a consistent and loving environment. Spend quality time with your pet, " +
                        "   maintain a routine, and be patient in understanding their unique personality.\n\n" +
                        "Remember, every dog is different. By observing and responding to your dog's specific needs, " +
                        "you can foster a healthy and loving relationship that lasts a lifetime."
        );

        // Add the content to a scroll pane
        JScrollPane scrollPane = new JScrollPane(articleContent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Papyrus", Font.PLAIN, 18));
        backButton.setBackground(new Color(0, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.addActionListener(e -> dispose()); // Close the article page
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Article_DogBehaviour::new);
    }
}