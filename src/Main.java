import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Entry point for the Tagalog Flashcard App - runs an interactive quiz with retry logic
public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        FlashcardDeck myDeck = new FlashcardDeck();

        // load saved deck
        ArrayList<Card> loadedDeck = DeckFileManager.loadDeck("flashcards.csv");
        if (!loadedDeck.isEmpty()) {
            for (Card card : loadedDeck) {
                myDeck.addCard(card);
            }
        } else {
            // Seed the deck with initial Tagalog vocabulary
            myDeck.addCard(new TagalogCard("Salamat","Thank you","Greetings"));
            myDeck.addCard(new TagalogCard("Kumusta","How are you","Greetings"));
            myDeck.addCard(new TagalogCard("Oo","Yes","Basics"));
            myDeck.addCard(new TagalogCard("Hindi","No","Basics"));
            myDeck.addCard(new TagalogCard("Tubig","Water","Food & Drink"));

            // Save myDeck to csv
            DeckFileManager.saveDeck(myDeck.getDeck(),"flashcards.csv");
            System.out.println("Welcome to flashcard Deck! Your deck has been saved.");
        }

        // simple menu system to loop the App
        boolean running = true;

        while (running) {

            // Main Menu
            System.out.println();
            System.out.println("=== Tagalog Flashcard App ===");
            System.out.println("1. Quiz me");
            System.out.println("2. Add new cards");
            System.out.println("3. View deck");
            System.out.println("4. Quit");
            System.out.println();
            System.out.println("Please select an option:");

            String menuSelection = userInput.nextLine();

            boolean validMenuSelection = false;
            while (!validMenuSelection) {
                // check if choice is not empty
                if (menuSelection.isBlank()) {
                    System.out.println("Unfortunately you hit enter without typing anything. Try again.");
                    System.out.println("Please enter a whole number that corresponds to a menu option and hit enter.");
                    menuSelection = userInput.nextLine();
                } else {
                    // check if choice is an integer
                    try {
                        int menuInt = Integer.parseInt(menuSelection);
                        if (!(menuInt < 1 || menuInt > 4)) {

                            validMenuSelection = true;
                            // Menu option 1. Quiz me
                            if (menuSelection.equals("1")) {
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

                                                // Validate deck to ensure it has cards
                                                try {
                                                    myDeck.validateDeck(myDeckByCategory);
                                                    // Shuffle the deck
                                                    Collections.shuffle(myDeckByCategory);

                                                    // Store cards that the User got wrong
                                                    ArrayList<Card> failedCards = new ArrayList<>();

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
                                                            // Update the list of failed cards
                                                            failedCards.add(card);
                                                        }
                                                    }

                                                    System.out.println("No more words left to translate.");

                                                    // Ask User if they want to repeat the words they got wrong
                                                    if (!failedCards.isEmpty()) {
                                                        System.out.println("You got " + failedCards.size() + " word(s) wrong. Would you like to retry them one last time? (Type Y or N and hit enter)");
                                                        String yesOrNo;
                                                        yesOrNo = userInput.nextLine();
                                                        if (yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("yes")) {

                                                            // Count correct re-quiz answers
                                                            int correctReQuizCount = 0;

                                                            for (Card card : failedCards) {

                                                                Quizzable quizzable = (Quizzable) card;

                                                                System.out.println("Please type the English word for: " + quizzable.getQuestion());
                                                                String englishTranslation = userInput.nextLine();

                                                                if (quizzable.checkAnswer(englishTranslation)) {
                                                                    System.out.println("You translated '" + quizzable.getQuestion() + "' to '" + englishTranslation + "' which matches our translation 👍");
                                                                    System.out.println();
                                                                    //Update correct count
                                                                    correctReQuizCount++;
                                                                } else {
                                                                    System.out.println("Unfortunately you translated '" + quizzable.getQuestion() + "' incorrectly 👎");
                                                                    System.out.println("Our translation of '" + quizzable.getQuestion() + "' is '" + card.getBackCard() + "' in English.");
                                                                    System.out.println();
                                                                }
                                                            }

                                                            // save the score for the re-quiz
                                                            DeckFileManager.saveScore(myDeckCategories.get(selectedCategory - 1), (correctCount + correctReQuizCount), myDeckByCategory.size());
                                                            System.out.println("You got " + correctReQuizCount + " correct on the re-quiz.");
                                                            System.out.println("You got " + (correctCount + correctReQuizCount) + " of " + myDeckByCategory.size() + " correct overall.");
                                                            System.out.println("******** Game Over ********");
                                                        } else {
                                                            // save the current score
                                                            DeckFileManager.saveScore(myDeckCategories.get(selectedCategory - 1), correctCount, myDeckByCategory.size());

                                                            System.out.println("You got " + correctCount + " of " + myDeckByCategory.size() + " correct.");
                                                            System.out.println("******** Game Over ********");
                                                        }
                                                    } else {
                                                        // save the current score
                                                        DeckFileManager.saveScore(myDeckCategories.get(selectedCategory - 1), correctCount, myDeckByCategory.size());

                                                        System.out.println("You got " + correctCount + " of " + myDeckByCategory.size() + " correct.");
                                                        System.out.println("******** Game Over ********");
                                                    }

                                                } catch (EmptyDeckException e) {
                                                    System.out.println(e.getMessage());
                                                }

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
                            // Menu option 2. Add new cards
                            } else if (menuSelection.equals("2")) {
                                // Ask if User wants to add a new card
                                DeckFileManager.addNewCards(myDeck, userInput);

                            // Menu option 3. View deck
                            } else if (menuSelection.equals("3")) {
                                myDeck.displayAll();
                                System.out.println();
                                System.out.println("Deck contains " + myDeck.getSize() +" cards");

                            // Menu option 4. Quit
                            } else if (menuSelection.equals("4")) {
                                System.out.println();
                                System.out.println("You have selected option 4, so the app will now Quit. Thanks for playing 👍");
                                System.out.println();
                                System.out.println("Goodbye! Paalam! 👋 🇵🇭");
                                System.out.println("***************************************************************************");
                                running = false;
                            }

                        } else {
                            System.out.println(menuSelection + " was entered and is not a menu option. Please enter a whole number that corresponds to a menu option and hit enter.");
                            menuSelection = userInput.nextLine();
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("'" + menuSelection + "' is not valid. Try again.");
                        System.out.println("Please enter a whole number that corresponds to a menu option and hit enter.");
                        menuSelection = userInput.nextLine();
                    }
                }
            }
        }
    }

}