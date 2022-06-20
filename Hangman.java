import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {
        "+---+\n" +
        "|   |\n" +
        "    |\n" +
        "    |\n" +
        "    |\n" +
        "    |\n" +
        "=========\n",

        "+---+\n" +
        "|   |\n" +
        "O   |\n" +
        "    |\n" +
        "    |\n" +
        "    |\n" +
        "=========\n",

        "+---+\n" +
        "|   |\n" +
        "O   |\n" +
        "|   |\n" +
        "    |\n" +
        "    |\n" +
        "=========\n",

        " +---+\n" +
        " |   |\n" +
        " O   |\n" +
        "/|   |\n" +
        "     |\n" +
        "     |\n" +
        " =========\n",

        " +---+\n" +
        " |   |\n" +
        " O   |\n" +
        "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
        "     |\n" +
        "     |\n" +
        " =========\n",

        " +---+\n" +
        " |   |\n" +
        " O   |\n" +
        "/|\\  |\n" +
        "/    |\n" +
        "     |\n" +
        " =========\n",

        " +---+\n" +
        " |   |\n" +
        " O   |\n" +
        "/|\\  |\n" + 
        "/ \\  |\n" +
        "     |\n" +
        " =========\n"
    };

    public static void main(String[] args) {
        String randomWord = randomWord();
        char[] placeholder = new char[randomWord.length()];
        String misses = "";
        int counter = 0;

        for (int i = 0; i < randomWord.length(); i++) {
            placeholder[i] = '_';
        }
        
        System.out.println("\n" + gallows[counter]);
        System.out.println(printPlaceholders(placeholder) + "\n");

        Scanner scan = new Scanner(System.in);

        while(!(Arrays.toString(placeholder).equals(Arrays.toString(randomWord.toCharArray())))) {
            System.out.println(printMissedGuesses(misses));
            System.out.print("\nGuess:   ");
            char guess = scan.next().charAt(0);
            
            boolean checkGuess = checkGuess(guess, randomWord, placeholder);
            if (checkGuess) {
                updatePlaceholders(placeholder, checkGuess, guess, randomWord);
                System.out.println("\n" + printPlaceholders(placeholder) + "\n");
            } else {
                misses += guess;
                counter++;
                System.out.println("\n" + printPlaceholders(placeholder) + "\n");
            }
            System.out.println(gallows[counter]);

            if (misses.length() > randomWord.length()) {
                System.out.println("RIP!");
                System.out.println("\nThe word was: '" + randomWord + "'\n");
                break;
            } else if (Arrays.toString(placeholder).equals(Arrays.toString(randomWord.toCharArray()))) {
                System.out.println(printPlaceholders(placeholder));
                System.out.println("\nGOOD WORK!\n");
            }
        }
        scan.close();
    }

    public static String randomWord () {
        return words[(int)(Math.random() * words.length)];
    }

    public static boolean checkGuess (char guess, String randomWord, char[] placeholder) {
        for (int i = 0; i < randomWord.length(); i++) {
            if (placeholder[i] == '_') {
                if(randomWord.charAt(i) == guess) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void updatePlaceholders(char[] placeholder, boolean checkGuess, char guess, String randomWord) {
        if (checkGuess) {
            for (int i = 0; i < randomWord.length(); i++) {
                if (placeholder[i] == '_') {
                    if(randomWord.charAt(i) == guess) {
                        placeholder[i] = guess;
                    }
                }
            }
        }
    }

    public static String printPlaceholders(char[] placeholder) {
        String sPlaceholder = "";
        for (int i = 0; i < placeholder.length; i++) {
            sPlaceholder += placeholder[i];
            if (i == placeholder.length-1) {
                break;
            }
            sPlaceholder += ' ';
        }
        return "Word:   " + sPlaceholder;
    }

    public static String printMissedGuesses(String misses) {
        String pMisses = "";
        for (int i = 0; i < misses.length(); i++) {
            pMisses += misses.charAt(i);
        }
        return "Misses:   " + pMisses;
    }
}