import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Handles file I/O for the flashcard deck — save and load operations
public class DeckFileManager {

    public static void saveDeck(ArrayList<Card> card, String fileName) {

        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {

            for (Card c : card) {
                out.print(c.getFrontCard() + "," + c.getBackCard() + "," + c.getCategory());
                out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Card> loadDeck(String fileName) {
        ArrayList<Card> deck = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                deck.add(new TagalogCard(parts[0], parts[1], parts[2]));
            }

        } catch (FileNotFoundException e) {
            // file doesn't exist yet — first run, return empty deck
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deck;
    }

    public static void addNewCards(FlashcardDeck newDeck, Scanner inputNewCards){
        String yesOrNo;
        String inputTagalogWord;
        String inputEnglishTranslation;
        String inputCategory;

        boolean keepEntering = true;
        while (keepEntering) {
            System.out.println("Please enter the Tagalog word you would like to add.");
            inputTagalogWord = inputNewCards.nextLine();
            System.out.println("Please enter the English translation of " + inputTagalogWord + ".");
            inputEnglishTranslation = inputNewCards.nextLine();
            System.out.println("Please enter the Category, for example Greetings or Basics or Food & Drink");
            inputCategory = inputNewCards.nextLine();
            newDeck.addCard(new TagalogCard(inputTagalogWord, inputEnglishTranslation, inputCategory));

            // Ask User if they want to add more
            System.out.println("Would you like to add another card? (Type Y or N and hit enter)");
            yesOrNo = inputNewCards.nextLine();
            if (!(yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("yes"))) {
                keepEntering = false;
            }
        }
            // Save the new card(s)
            DeckFileManager.saveDeck(newDeck.getDeck(),"flashcards.csv");
            System.out.println("You have successfully added your card(s) and they have been saved.");
    }

    public static void saveScore(String category, int correct, int total) {

        try (PrintWriter pw = new PrintWriter(new FileWriter("score_history.txt", true))) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String formattedDate = now.format(formatter);

            pw.println(formattedDate + " | Category: " + category + " | Score: " + correct + "/" + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
