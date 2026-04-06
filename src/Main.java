import java.util.Scanner;

// Entry point for the Tagalog Flashcard App - runs an interactive quiz with retry logic
public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        FlashcardDeck myDeck = new FlashcardDeck();

        // Seed the deck with initial Tagalog vocabulary
        myDeck.addCard(new Flashcard("Salamat","Thank you","Greetings"));
        myDeck.addCard(new Flashcard("Kumusta","How are you","Greetings"));
        myDeck.addCard(new Flashcard("Oo","Yes","Basics"));
        myDeck.addCard(new Flashcard("Hindi","No","Basics"));
        myDeck.addCard(new Flashcard("Tubig","Water","Food & Drink"));

        System.out.println("Deck contains " + myDeck.getSize() +" cards");

        for (Flashcard card : myDeck.getDeck()) {
            int guessTotal = 2;
            System.out.println("Please type the English word for: " + card.getTagalog());
            String englishTranslation = userInput.nextLine();

            while(!englishTranslation.equalsIgnoreCase(card.getEnglish()) && guessTotal > 0) {
                System.out.println("'" + englishTranslation + "' is not our English translation for '" + card.getTagalog() +"'. Please try again. You have " + guessTotal + " guess(s) left.");
                guessTotal --;
                System.out.println("Please type the English word for: " + card.getTagalog());
                englishTranslation = userInput.nextLine();
            }
            if (englishTranslation.equalsIgnoreCase(card.getEnglish())) {
                System.out.println("You translated '" + card.getTagalog() + "' to '" + englishTranslation + "' which matches our translation 👍");
                System.out.println();
            } else if (guessTotal == 0) {
                System.out.println("Unfortunately you translated '" + card.getTagalog() + "' incorrectly 👎");
                System.out.println("Our translation of '" + card.getTagalog() + "' is '" + card.getEnglish() + "' in English. Let's try another word.");
                System.out.println();
            }
        }

        System.out.println("No more words left to translate. Thanks for playing! Paalam!");

    }
}