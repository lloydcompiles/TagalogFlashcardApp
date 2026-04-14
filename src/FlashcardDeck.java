import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

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

    public ArrayList<Flashcard> getDeck() {
        return deck;
    }

    // filter the card deck by a chosen category
    public ArrayList<Flashcard> getDeckByCategory(String category) {
        ArrayList<Flashcard> deckCategory = deck.stream()
                .filter(flashcard -> flashcard.getCategory().equals(category))
                .collect(Collectors.toCollection(ArrayList::new));

        return deckCategory;
    }

    // extract the categories from the deck
    public ArrayList<String> getCategories() {
        Set<String> uniqueCategories = deck.stream()
                .map(flashcard -> flashcard.getCategory())
                .collect(Collectors.toSet());

        return new ArrayList<String>(uniqueCategories);
    }
}