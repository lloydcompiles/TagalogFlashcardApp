import java.io.*;
import java.util.ArrayList;

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
}
