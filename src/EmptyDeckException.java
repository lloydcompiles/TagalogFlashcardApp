// Custom exception thrown when a flashcard deck contains no cards
public class EmptyDeckException extends Exception {

    public EmptyDeckException(String message) {
        super(message);
    }
}
