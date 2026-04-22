import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
}
