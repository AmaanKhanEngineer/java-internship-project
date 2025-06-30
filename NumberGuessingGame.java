import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int roundsWon = 0;
        boolean playAgain;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        do {
            int numberToGuess = random.nextInt(100) + 1; // Generates number between 1 to 100
            int attemptsLeft = 7;
            boolean guessedCorrectly = false;

            System.out.println("\nI've picked a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts. Try to guess it!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("✅ Congratulations! You guessed the number correctly.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }

                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Sorry! You ran out of attempts.");
                System.out.println("The correct number was: " + numberToGuess);
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("\n🏁 Game Over! Total Rounds Won: " + roundsWon);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
