// Represents a single Tagalog flashcard
public class TagalogCard extends Card {

    public TagalogCard(String front, String back, String category) {
        super(front, back, category);
    }

    @Override
    public String getLanguageLabel() {
        return "Tagalog";
    }
}
