import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Entry point for the Tagalog Flashcard App - runs an interactive quiz with retry logic
public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        FlashcardDeck myDeck = new FlashcardDeck();

        // Seed the deck with initial Tagalog vocabulary
        myDeck.addCard(new TagalogCard("Salamat","Thank you","Greetings"));
        myDeck.addCard(new TagalogCard("Kumusta","How are you","Greetings"));
        myDeck.addCard(new TagalogCard("Oo","Yes","Basics"));
        myDeck.addCard(new TagalogCard("Hindi","No","Basics"));
        myDeck.addCard(new TagalogCard("Tubig","Water","Food & Drink"));

        System.out.println("Deck contains " + myDeck.getSize() +" cards");

        // Get and display categories to user, get number of categories, ask to select category
        ArrayList<String> myDeckCategories = myDeck.getCategories();

        int numberOfCategories = myDeckCategories.size();
        int catNo = 1;
        for (String category : myDeckCategories) {
            System.out.println(catNo + ". " + category);
            catNo++;
        }
        System.out.println("Please type the number that corresponds to the category you would like to select and hit enter.");
        String choice = userInput.nextLine();

        boolean validChoice = false;
        while (!validChoice) {
            // check if choice is not empty
            if (choice.isBlank()) {
                System.out.println("Unfortunately you hit enter without typing anything. Try again.");
                System.out.println("Please enter a whole number that corresponds to your chosen category and hit enter.");
                choice = userInput.nextLine();
            } else {
                // check if choice is an integer
                try {
                    int selectedCategory = Integer.parseInt(choice);
                    if (selectedCategory > 0 && selectedCategory <= numberOfCategories) {

                        validChoice = true;
                        // Play the game with the chosen category

                        // Get the deck by chosen category
                        ArrayList<Card> myDeckByCategory = myDeck.getDeckByCategory(myDeckCategories.get(selectedCategory-1));
                        // Shuffle the deck
                        Collections.shuffle(myDeckByCategory);

                        // Count correct answers
                        int correctCount = 0;

                        for (Card card : myDeckByCategory) {

                            Quizzable quizzable = (Quizzable) card;

                            int guessTotal = 2;
                            System.out.println("Please type the English word for: " + quizzable.getQuestion());
                            String englishTranslation = userInput.nextLine();

                            while (!quizzable.checkAnswer(englishTranslation) && guessTotal > 0) {
                                System.out.println("'" + englishTranslation + "' is not our English translation for '" + quizzable.getQuestion() + "'. Please try again. You have " + guessTotal + " guess(s) left.");
                                guessTotal--;
                                System.out.println("Please type the English word for: " + quizzable.getQuestion());
                                englishTranslation = userInput.nextLine();
                            }
                            if (quizzable.checkAnswer(englishTranslation)) {
                                System.out.println("You translated '" + quizzable.getQuestion() + "' to '" + englishTranslation + "' which matches our translation 👍");
                                System.out.println();
                                //Update correct count
                                correctCount++;
                            } else if (guessTotal == 0) {
                                System.out.println("Unfortunately you translated '" + quizzable.getQuestion() + "' incorrectly 👎");
                                System.out.println("Our translation of '" + quizzable.getQuestion() + "' is '" + card.getBackCard() + "' in English.");
                                System.out.println();
                            }
                        }

                        System.out.println("No more words left to translate. You got " + correctCount + " correct. Thanks for playing! Paalam!");

                    } else {
                        System.out.println(selectedCategory + " was entered and is not an option. Please type the number that corresponds to the category you would like to select and hit enter.");
                        choice = userInput.nextLine();
                    }

                } catch (NumberFormatException e) {
                    System.out.println("'" + choice + "' is not valid. Try again.");
                    System.out.println("Please enter a whole number that corresponds to your chosen category and hit enter.");
                    choice = userInput.nextLine();
                }
            }
        }
    }
}