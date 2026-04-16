// Represents a single flashcard with a front, back, and a word category
public abstract class Card {
    private String frontCard;
    private String backCard;
    private String category;

    public Card(String frontCard, String backCard, String category) {
        this.frontCard = frontCard;
        this.backCard = backCard;
        this.category = category;
    }

    public String getFrontCard() {
        return frontCard;
    }

    public void setFrontCard(String frontCard) {
        this.frontCard = frontCard;
    }

    public String getBackCard() {
        return backCard;
    }

    public void setBackCard(String backCard) {
        this.backCard = backCard;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Get the language
    public abstract String getLanguageLabel();

    // Display the card
    public void display() {
        System.out.println("[" + getLanguageLabel() + "] " + category + " " + frontCard + " = " + backCard);
    }
}
