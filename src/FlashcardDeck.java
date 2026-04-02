import java.util.ArrayList;

// Manages a collection of Flashcard objects, supporting add, display, and size operations
public class FlashcardDeck {
    private final ArrayList<Flashcard> deck;

    public FlashcardDeck() {
        deck = new ArrayList<>();
    }

    public void addCard(Flashcard card) {
        deck.add(card);
    }

    public void displayAll() {
        for (Flashcard card : deck) {
            card.display();
        }
    }

    public int getSize() {
        return deck.size();
    }
}