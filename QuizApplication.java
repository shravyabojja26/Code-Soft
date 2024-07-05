import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static final int NUM_QUESTIONS = 5; // Number of quiz questions
    private static final int TIME_LIMIT_SECONDS = 60; // Time limit for the quiz in seconds

    private static String[] questions = {
        "1. What is the capital of France?",
        "2. Who wrote 'Romeo and Juliet'?",
        "3. What is the largest planet in our solar system?",
        "4. Who painted the Mona Lisa?",
        "5. How many continents are there in the world?"
    };

    private static String[] options = {
        "A. London   B. Paris   C. Berlin   D. Madrid",
        "A. Charles Dickens   B. William Shakespeare   C. Jane Austen   D. Mark Twain",
        "A. Earth   B. Jupiter   C. Saturn   D. Mars",
        "A. Vincent van Gogh   B. Leonardo da Vinci   C. Pablo Picasso   D. Michelangelo",
        "A. 5   B. 6   C. 7   D. 8"
    };

    private static char[] answers = {'B', 'B', 'B', 'B', 'C'}; // Correct answers

    private static Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static boolean[] answered = new boolean[NUM_QUESTIONS];
    private static Timer timer = new Timer();
    private static boolean quizFinished = false;

    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds to complete " + NUM_QUESTIONS + " questions.");

        // Start the timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Quiz is over.");
                quizFinished = true;
                displayResults();
                System.exit(0); // Exit the program after displaying results
            }
        }, TIME_LIMIT_SECONDS * 1000);

        // Process questions
        while (!quizFinished) {
            for (int i = 0; i < NUM_QUESTIONS; i++) {
                if (!answered[i]) {
                    displayQuestion(i);
                    char userAnswer = getUserAnswer();
                    if (userAnswer == answers[i]) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Incorrect. The correct answer is " + answers[i]);
                    }
                    answered[i] = true;
                }
            }
        }

        // Cancel the timer when quiz finishes
        timer.cancel();
        scanner.close();
    }

    private static void displayQuestion(int index) {
        System.out.println("\nQuestion " + (index + 1) + ":");
        System.out.println(questions[index]);
        System.out.println(options[index]);
    }

    private static char getUserAnswer() {
        System.out.print("Enter your answer (A/B/C/D): ");
        char answer = Character.toUpperCase(scanner.next().charAt(0));
        while (answer != 'A' && answer != 'B' && answer != 'C' && answer != 'D') {
            System.out.print("Invalid input. Please enter A, B, C, or D: ");
            answer = Character.toUpperCase(scanner.next().charAt(0));
        }
        return answer;
    }

    private static void displayResults() {
        System.out.println("\n----- Quiz Results -----");
        System.out.println("Your score: " + score + " out of " + NUM_QUESTIONS);
        if (score == NUM_QUESTIONS) {
            System.out.println("Congratulations! You got all questions correct.");
        } else {
            System.out.println("Better luck next time!");
        }
    }
}
