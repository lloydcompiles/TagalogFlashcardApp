// Represents a single flashcard with a Tagalog word, its English translation, and a category
public  class Flashcard {
    private String tagalog;
    private String english;
    private String category;

    public Flashcard(String tagalog, String english, String category) {
        this.tagalog = tagalog;
        this.english = english;
        this.category = category;
    }

    public String getTagalog() {
        return tagalog;
    }
    public String getEnglish() {
        return english;
    }
    public String getCategory() {
        return category;
    }
    public void display() {
        System.out.println(category + " " + tagalog + " = " + english);
    }
}
