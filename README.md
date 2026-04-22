# TagalogFlashcardApp

A Java console application for studying Tagalog vocabulary using flashcards.

## About
This app was built as a hands-on Java learning project. It demonstrates core
object-oriented programming concepts including inheritance, encapsulation,
polymorphism, collections, and streams.

## Features (current)
- Abstract base `Card` class with `TagalogCard` subclass using inheritance
- `FlashcardDeck` class managing a collection of `Card` objects
- Interactive quiz mode with user input and retry logic
- Score tracking — correct count displayed at end of quiz
- Deck shuffle using `Collections.shuffle()`
- Category filter with numbered menu and input validation
- Seeded deck with Tagalog vocabulary across multiple categories
- Deck saved to `flashcards.csv` on startup using file I/O

## Planned Features
- Load flashcards from CSV file on startup
- Save quiz scores
- Add new cards during a session
- Support for additional languages (SpanishCard, FrenchCard)

## Built With
- Java
- IntelliJ IDEA

## How to Run
1. Clone the repo: `git clone https://github.com/lloydcompiles/TagalogFlashcardApp.git`
2. Open the project in IntelliJ IDEA
3. Run `Main.java`

## Author
lloydcompiles
