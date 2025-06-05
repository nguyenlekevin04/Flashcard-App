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

class DeckManagerTest1 {

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
	void createDeckThrowsException() {
		scanner = new Scanner("\n");
		assertThrows(IllegalArgumentException.class, () -> dM.createDeck(scanner));
	}
	
	@Test
	void createNewDeck() {
		scanner = new Scanner("Mathe\n");
		Deck deck = new Deck();
		
		assertNotNull(deck);
		assertEquals("Mathe", 	dM.createDeck(scanner).getName());
		assertEquals(1, decks.size());
	}
	
	@Test
	void selectDeckThrowsException1() {
		scanner = new Scanner("hallo\n");
		 
		decks.add(new Deck("Mathe\n"));
		 
		assertThrows(IllegalArgumentException.class, () -> dM.selectDeck(scanner));
	}
	
	@Test
	void selectDeckThrowsException2() {
		scanner = new Scanner("2\n");
		 
		decks.add(new Deck("Mathe\n"));
		 
		assertThrows(NoSuchElementException.class, () -> dM.selectDeck(scanner));
	}
	
	@Test
	void selectDeckThrowsException3() {
		scanner = new Scanner("1\n");
		 
		assertThrows(NoSuchElementException.class, () -> dM.selectDeck(scanner));
	}
	
	@Test
	void selectDeck() {
		scanner = new Scanner("2\n");
		
		decks.add(new Deck("Mathe\n"));
		decks.add(new Deck("Biologie\n"));
		
		Deck selectedDeck = dM.selectDeck(scanner);
		
		assertNotNull(selectedDeck);
		assertEquals(selectedDeck.getName(), "Biologie\n");
	}
	
	@Test
	void testDeleteDeck() {
		scanner = new Scanner("2\n");
		
		decks.add(new Deck("Mathe\n"));
		decks.add(new Deck("Biologie\n"));
		
		dM.deleteDeck(scanner);
		
		assertEquals(1, decks.size());
	}
	
	@Test
	void quizModeException() {
		scanner = new Scanner("1\nString\n");

		Deck deck1 = new Deck("Math");
		deck1.addCard(new Flashcard("1 + 1", "2", "rechnen"));
		decks.add(deck1);
		
		assertThrows(InputMismatchException.class, () -> {dM.quizMode(scanner);});
		
	}
	
	@Test
	void quizByCategoryException1() {
		scanner = new Scanner("\n");
		Scanner scannerSelected = new Scanner("1\n");
		
		decks.add(new Deck("Biologie"));
		
		Deck selectedDeck = dM.selectDeck(scannerSelected);

		assertThrows(IllegalArgumentException.class, () -> {dM.quizByCategory(selectedDeck, scanner);});
	}
	
	@Test
	void quizByCategoryException2() {
		scanner = new Scanner("\n");
		Scanner scannerSelected = new Scanner("1\n");
		
		decks.add(new Deck("Biologie"));
		
		Deck selectedDeck = dM.selectDeck(scannerSelected);
		
		selectedDeck.addCard(new Flashcard("biology", "Biologie","Vokabeln"));
		
		Scanner scannerCategory = new Scanner("Vokabeln\n\n");
		
		assertThrows(IllegalArgumentException.class, () -> {dM.quizByCategory(selectedDeck, scannerCategory);});
	}
	
	@Test 
	void filterByCategoryException() {
		
		String category = "Vokabeln";
		Deck deck = new Deck("Mathe");
		deck.addCard(new Flashcard("1 + 1", "2", "rechnen"));
		
		assertThrows(NoSuchElementException.class, () -> {dM.filterByCategory(deck, category);});	
	}
	
	@Test
	void filterByCategory() {
		String category = "rechnen";
		Deck deck = new Deck("Mathe");
		deck.addCard(new Flashcard("1 + 1", "2", "rechnen"));
		
		Deck filteredDeck = dM.filterByCategory(deck, category);
		
		assertEquals(deck.getCards(), filteredDeck.getCards());
	}
	
	@Test
	void addCardsByCategory() {
		Flashcard f1 = new Flashcard("apple", "Apfel", "Vokabeln");
		
		Deck deck = new Deck("Englisch");
		
		deck.addCard(f1);
		
		Deck filteredDeck = dM.filterByCategory(deck, "Vokabeln");
		assertEquals(filteredDeck.getCards().size(), 1);
	}
	
	
}
