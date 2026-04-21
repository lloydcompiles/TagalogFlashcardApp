import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

// Manages a collection of Flashcard objects, supporting add, display, and size operations
public class FlashcardDeck {
    private final ArrayList<Card> deck;

    public FlashcardDeck() {
        deck = new ArrayList<>();
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void displayAll() {
        for (Card card : deck) {
            card.display();
        }
    }

    public int getSize() {
        return deck.size();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    // filter the card deck by a chosen category
    public ArrayList<Card> getDeckByCategory(String category) {
        ArrayList<Card> deckCategory = deck.stream()
                .filter(card -> card.getCategory().equals(category))
                .collect(Collectors.toCollection(ArrayList::new));

        return deckCategory;
    }

    // extract the categories from the deck
    public ArrayList<String> getCategories() {
        Set<String> uniqueCategories = deck.stream()
                .map(card -> card.getCategory())
                .collect(Collectors.toSet());

        return new ArrayList<String>(uniqueCategories);
    }

    // throw an exception if the deck has no cards in it
    public void validateDeck(ArrayList<Card> deck) throws EmptyDeckException{
        if(deck.isEmpty()){
            throw new EmptyDeckException("Unfortunately the category you selected has no cards. Game over.");
        }
    }
}