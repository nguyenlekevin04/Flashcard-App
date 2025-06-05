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
import logic.FlashcardManager;
import model.AbstractCard;
import model.Deck;
import model.Flashcard;

class FlashcardManagerTest {

	static ArrayList<Deck> decks;
	static Scanner scanner;
	static DeckManager dM;
	static FlashcardManager fM;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 decks = new ArrayList<>();
		 dM = new DeckManager(decks);
		 fM = new FlashcardManager(dM);
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
	void createFlashcardException() {
		scanner = new Scanner("1\n\n\n");
		
		assertThrows(IllegalArgumentException.class, () -> {fM.createFlashcard(scanner);});
	}
	
	@Test
	void createFlashcard() {
		scanner = new Scanner("1\n1+1\n2\nrechnen\n");
		
		Deck deck = new Deck("mathe");
		decks.add(deck);
		fM.createFlashcard(scanner);
		
		assertEquals(1, deck.getCards().size());
	}
	
	@Test
	void manageFlashcardException1() {
		scanner = new Scanner("1\n");
		decks.add(new Deck("Mathe"));
		
		assertThrows(NoSuchElementException.class, () -> {fM.manageFlashcards(scanner);});
	}
	
	@Test
	void manageFlashcardException2() {
		scanner = new Scanner("1\n2\n");
		decks.add(new Deck("Mathe"));
		
		assertThrows(NoSuchElementException.class, () -> {fM.manageFlashcards(scanner);});
	}
	
	@Test
	void manageFlashcardException3() {
		scanner = new Scanner("1\n2\n4\n");
		decks.add(new Deck("Mathe"));
		
		assertThrows(NoSuchElementException.class, () -> {fM.manageFlashcards(scanner);});
	}
	
	@Test
	void deleteFlashcardException() {
		Deck deck = new Deck("Mathe");
		deck.addCard(new Flashcard("1 * 1", "1", "rechnen"));
		int index = 5;
		
		assertThrows(NoSuchElementException.class, () -> {fM.deleteFlashcard(deck, index);});
	}
	
	@Test
	void deleteFlashcard() {
		Deck deck = new Deck("Mathe");
		deck.addCard(new Flashcard("1 * 1", "1", "rechnen"));
		int index = 0;
		
		fM.deleteFlashcard(deck, index);
		ArrayList<AbstractCard> cards = deck.getCards();
		
		assertEquals(cards.size(), 0);
	}
	
	@Test
	void changeFlashcardException1() {
		scanner = new Scanner("5\nhallo\n");
		Deck deck = new Deck("Mathe");
		deck.addCard(new Flashcard("1 * 1", "1", "rechnen"));
		
		AbstractCard card = deck.getCards().get(0);
		
		assertThrows(InputMismatchException.class, () -> {fM.changeFlashcard(card, scanner);});
	}
	
	@Test
	void changeFlashcardException2() {
		scanner = new Scanner("2\n\n");
		Deck deck = new Deck("Mathe");
		deck.addCard(new Flashcard("1 * 1", "1", "rechnen"));
		
		AbstractCard card = deck.getCards().get(0);
		
		assertThrows(IllegalArgumentException.class, () -> {fM.changeFlashcard(card, scanner);});
	}
	
	@Test
	void changeFlashcard() {
		scanner = new Scanner("2\n4\n");
		Deck deck = new Deck("Mathe");
		deck.addCard(new Flashcard("1 * 1", "1", "rechnen"));
		
		AbstractCard card = deck.getCards().get(0);
		
		fM.changeFlashcard(card, scanner);
		
		assertEquals("4", card.getAnswer());
	}
}
