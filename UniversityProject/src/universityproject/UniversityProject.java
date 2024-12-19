/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityproject;

import java.util.*;
public class UniversityProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MasterMindGame game = new MasterMindGame();
        game.play();
    }
    
}
class MasterMindGame {
 
    private final int[] secretCode = new int[4];
    private final int[][] attempts = new int[10][4];
    private int attemptCount = 0;

    /**
     * Initializes a new MasterMindGame and generates a secret code.
     */
    public MasterMindGame() {
        generateSecretCode();
    }

    /**
     * Generates a unique secret code with 4 numbers between 0 and 5.
     */
    private void generateSecretCode() {
        Random random = new Random();
        ArrayList<Integer> usedNumbers = new ArrayList<>();

        int i = 0;
        while (i < 4) {
            int number = random.nextInt(6); // returns numbers between 0 and 5
            if (!usedNumbers.contains(number)) {
                usedNumbers.add(number);
                secretCode[i] = number;
                i++;
            }
        }
      
    }

    /**
     * Starts the game and handles user input and feedback.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MasterMind!");

        while (attemptCount < 10) {
            System.out.println("Attempt " + (attemptCount + 1) + ": Enter 4 numbers (0-5):");

            for (int i = 0; i < 4; i++) {
                attempts[attemptCount][i] = scanner.nextInt();
            }

            if (checkWin()) {
                System.out.println("Congratulations! You guessed the secret code in " + (attemptCount + 1) + " attempts.");
                return;
            } else {
                provideFeedback();
                attemptCount++;
            }
        }

        System.out.println("Game Over! You've used all attempts. The secret code was: " + Arrays.toString(secretCode));
    }

    /**
     * Checks if the current attempt matches the secret code.
     * @return true if the attempt is correct, false otherwise.
     */
    private boolean checkWin() {
        return Arrays.equals(attempts[attemptCount], secretCode);
    }

    /**
     * Provides feedback on the current attempt, indicating correct positions and numbers.
     */
    private void provideFeedback() {
        int correctPosition = 0;
        int correctNumber = 0;

        boolean[] secretUsed = new boolean[4];
        boolean[] attemptUsed = new boolean[4];

        // Check for correct positions
        for (int i = 0; i < 4; i++) {
            if (attempts[attemptCount][i] == secretCode[i]) {
                correctPosition++;
                secretUsed[i] = true;
                attemptUsed[i] = true;
            }
        }

        // Check for correct numbers in incorrect positions
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!secretUsed[j] && !attemptUsed[i] && attempts[attemptCount][i] == secretCode[j]) {
                    correctNumber++;
                    secretUsed[j] = true;
                    attemptUsed[i] = true;
                    break;
                }
            }
        }

        System.out.println("Feedback: " + correctPosition + " correct position(s), " + correctNumber + " correct number(s) in wrong position.");
    }


}