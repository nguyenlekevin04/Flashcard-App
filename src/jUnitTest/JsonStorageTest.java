package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Deck;
import persistence.JsonStorage;

class JsonStorageTest {
	
	static JsonStorage jS;
	static ArrayList<Deck> decks;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		jS = new JsonStorage();
		decks = new ArrayList<>();
		decks.add(new Deck("Mathe"));
		decks.add(new Deck("Biologie"));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		decks.clear();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void saveDecksToFileException() {
		assertDoesNotThrow(() -> {jS.saveDecksToFile(decks);});
	}
	
	@Test
	void loadDecksFromFile() {
		assertDoesNotThrow(() -> {jS.loadDecksFromFile();});
	}
}
