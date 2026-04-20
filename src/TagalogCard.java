// Represents a single Tagalog flashcard
public class TagalogCard extends Card implements Quizzable{

    public TagalogCard(String front, String back, String category) {
        super(front, back, category);
    }

    @Override
    public String getLanguageLabel() {
        return "Tagalog";
    }

    @Override
    public String getQuestion() {
        return getFrontCard();
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(getBackCard());
    }
}
