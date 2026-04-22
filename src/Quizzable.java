// Interface defining the contract for any quizzable card type
public interface Quizzable {
    String getQuestion();
    boolean checkAnswer(String userAnswer);
}
