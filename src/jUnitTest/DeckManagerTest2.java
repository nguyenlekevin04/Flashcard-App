package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.DeckManager;
import model.Deck;
import model.Flashcard;

class DeckManagerTest2 {

	static ArrayList<Deck> decks;
	static Scanner scanner;
	static DeckManager dM;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 decks = new ArrayList<>();
		 dM = new DeckManager(decks);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		scanner.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		decks.clear();
	}

	@AfterEach
	
	void tearDown() throws Exception {
	}

	@Test
	void quizWithWholeDeckException() {
		scanner = new Scanner("1\n");
		Scanner scannerQuiz = new Scanner("\n\n");
		
		decks.add(new Deck("Mathe"));
		
		Deck selectedDeck = dM.selectDeck(scanner);
		
		
		selectedDeck.addCard(new Flashcard("1 + 1", "2", "Rechnen"));
		
		assertThrows(IllegalArgumentException.class, () -> {dM.quizWithWholeDeck(selectedDeck, scannerQuiz);});
	}
	
	@Test
	void testTrackProgress() {
		scanner = new Scanner("1\nString\n\n\n");
		
		Deck deck1 = new Deck("Mathe");
		deck1.addCard(new Flashcard("1 + 1", "2", "rechnen"));
		decks.add(deck1);
		
		assertThrows(InputMismatchException.class, () -> {dM.trackProgress(scanner);});
	}
	
	@Test
	void progressByCategoryException1() {
		Scanner scannerSelect = new Scanner("1\n");
		scanner = new Scanner("\n");
		
		Deck deck = new Deck("mathe");
		decks.add(deck);
		Deck selectedDeck = dM.selectDeck(scannerSelect);
		
		assertThrows(IllegalArgumentException.class, () -> {dM.progressByCategory(selectedDeck, scanner);});
	}
	
	@Test
	void progressByCategoryException2() {
		Scanner scannerSelect = new Scanner("1\n");
		scanner = new Scanner("Vokabeln");
		
		Deck deck = new Deck("mathe");
		deck.addCard(new Flashcard("1 + 1", "2", "rechnen"));
		decks.add(deck);
		Deck selectedDeck = dM.selectDeck(scannerSelect);
		
		assertThrows(NoSuchElementException.class, () -> {dM.progressByCategory(selectedDeck, scannerSelect);});
	}
	
	@Test
	void showCardsException() {
		scanner = new Scanner("1\nabc\n");
		
		Deck deck1 = new Deck("Mathe");
		decks.add(deck1);
		deck1.addCard(new Flashcard("1 + 1", "2", "rechnen"));
		
		assertThrows(InputMismatchException.class,() -> {dM.showCards(scanner);});
	}
}
