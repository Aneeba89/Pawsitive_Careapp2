//package com.pawsitivecare;
//
//import java.io.*;
//import java.net.*;
//import java.util.HashMap;
//
//class FAQServer {
//    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(6000); // Server listens on port 6000
//        System.out.println("Pet Care FAQ Server is running...");
//
//        // Pet Care FAQs stored in a HashMap
//        HashMap<String, String> faqMap = new HashMap<>();
//        faqMap.put("What are common pet vaccinations?", "Common vaccinations include rabies, distemper, parvovirus, and leptospirosis for dogs; and FVRCP and rabies for cats.");
//        faqMap.put("How often should I feed my pet?", "Feeding frequency depends on the pet's age, size, and breed. Puppies and kittens may need 3-4 meals a day, while adult pets usually eat 1-2 times daily.");
//        faqMap.put("What is the best way to train my pet?", "Positive reinforcement with treats, praise, and consistency is the most effective way to train pets.");
//        faqMap.put("How do I groom my pet?", "Regular brushing, nail trimming, ear cleaning, and occasional baths are essential for pet grooming.");
//        faqMap.put("What should I do if my pet is sick?", "If your pet shows signs of illness, such as vomiting, diarrhea, or lethargy, consult a veterinarian immediately.");
//        faqMap.put("How do I prevent fleas and ticks?", "Use veterinarian-recommended flea and tick preventatives and regularly check your pet's coat for pests.");
//        faqMap.put("What is the best diet for my pet?", "A balanced diet with high-quality pet food appropriate for your pet's age, size, and health condition is recommended.");
//        faqMap.put("How much exercise does my pet need?", "Exercise needs vary by breed and age. Dogs typically need 30 minutes to 2 hours daily, while cats benefit from interactive play sessions.");
//        faqMap.put("What are signs of pet anxiety?", "Signs include excessive barking, destructive behavior, trembling, hiding, or changes in appetite or bathroom habits.");
//        faqMap.put("What should I do in case of a pet emergency?", "Stay calm and contact your veterinarian or an emergency animal clinic immediately.");
//        faqMap.put("Exit", "Goodbye! Stay safe with your furry friends!");
//
//        while (true) {
//            try (Socket clientSocket = serverSocket.accept();  // Accept a client connection
//                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
//
//                System.out.println("Client connected!");
//
//                while (true) {
//                    // Read question from the client
//                    String question = reader.readLine();
//                    System.out.println("Received question: " + question);
//
//                    if (question.equalsIgnoreCase("Exit")) {
//                        writer.write("Goodbye! Stay safe with your furry friends!\n");
//                        writer.flush();
//                        break;
//                    }
//
//                    // Respond with the answer or a default message
//                    String answer = faqMap.getOrDefault(question, "Sorry, I don't know the answer to that. Please consult a veterinarian for more information.");
//                    writer.write(answer + "\n");
//                    writer.flush();
//                }
//            } catch (IOException e) {
//                System.err.println("Connection error: " + e.getMessage());
//            }
//        }
//    }
//}
//

package com.pawsitivecare;

import java.io.*;
import java.net.*;
import java.util.*;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000); // Server listens on port 6000
        System.out.println("Pet Care FAQ Server is running...");

        // Pet Care FAQs stored in a HashMap
        HashMap<String, String> faqMap = new HashMap<>();
        faqMap.put("What are common pet vaccinations?", "Common vaccinations include rabies, distemper, parvovirus, and leptospirosis for dogs; and FVRCP and rabies for cats.");
        faqMap.put("How often should I feed my pet?", "Feeding frequency depends on the pet's age, size, and breed. Puppies and kittens may need 3-4 meals a day, while adult pets usually eat 1-2 times daily.");
        faqMap.put("What is the best way to train my pet?", "Positive reinforcement with treats, praise, and consistency is the most effective way to train pets.");
        faqMap.put("How do I groom my pet?", "Regular brushing, nail trimming, ear cleaning, and occasional baths are essential for pet grooming.");
        faqMap.put("What should I do if my pet is sick?", "If your pet shows signs of illness, such as vomiting, diarrhea, or lethargy, consult a veterinarian immediately.");

        List<String> questionList = new ArrayList<>(faqMap.keySet());

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                System.out.println("Client connected!");

                while (true) {
                    String question = reader.readLine();
                    System.out.println("Received: " + question);

                    if (question.equalsIgnoreCase("Get Questions")) {
                        for (int i = 0; i < questionList.size(); i++) {
                            writer.write((i + 1) + ". " + questionList.get(i) + "\n");
                        }
                        writer.write("END\n");
                        writer.flush();
                        continue;
                    }

                    // If the user enters a question number
                    try {
                        int questionNumber = Integer.parseInt(question);
                        if (questionNumber > 0 && questionNumber <= questionList.size()) {
                            String selectedQuestion = questionList.get(questionNumber - 1);
                            writer.write("Answer: " + faqMap.get(selectedQuestion) + "\n");
                        } else {
                            writer.write("Invalid question number. Please try again.\n");
                        }
                    } catch (NumberFormatException e) {
                        writer.write("Please enter a valid question number.\n");
                    }
                    writer.flush();
                }
            } catch (IOException e) {
                System.err.println("Connection error: " + e.getMessage());
            }
        }
    }
}

