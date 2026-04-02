// Entry point for the Tagalog Flashcard App - seeds the deck and displays all cards
public class Main {
    public static void main(String[] args) {
        FlashcardDeck myDeck = new FlashcardDeck();

        // Seed the deck with initial Tagalog vocabulary
        myDeck.addCard(new Flashcard("Salamat","Thank you","Greetings"));
        myDeck.addCard(new Flashcard("Kumusta","How are you","Greetings"));
        myDeck.addCard(new Flashcard("Oo","Yes","Basics"));
        myDeck.addCard(new Flashcard("Hindi","No","Basics"));
        myDeck.addCard(new Flashcard("Tubig","Water","Food & Drink"));

        System.out.println("Deck contains " + myDeck.getSize() +" cards");
        myDeck.displayAll();

    }
}