

package com.pawsitivecare;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



    public class Symptom_CheckerTest {

        @Test
        public void testCheckValidSymptom() {
            // Arrange
            Symptom_Checker symptomChecker = new Symptom_Checker(null);
            String validSymptom = "Vomiting";

            // Set input
            symptomChecker.symptomInput.setText(validSymptom);

            // Act
            symptomChecker.checkSymptoms(validSymptom);

            // Assert
            String resultText = symptomChecker.resultArea.getText();
            assertTrue(resultText.contains("Disease:"));
            assertTrue(resultText.contains("Precautions:"));
        }

        @Test
        public void testInvalidSymptomInput() {
            // Arrange
            Symptom_Checker symptomChecker = new Symptom_Checker(null);
            String invalidSymptom = "RandomSymptom123";

            // Act
            symptomChecker.checkSymptoms(invalidSymptom);

            // Assert
            assertEquals("No matching symptoms found in the database.", symptomChecker.resultArea.getText());
        }


    }