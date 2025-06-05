package logic;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.Flashcard;
import model.Deck;
import model.AbstractCard;
import logic.DeckManager;
import persistence.JsonStorage;

public class FlashcardManager {
	
	private Scanner myScanner = new Scanner(System.in);		//Scanner zum Einlesen von Eingaben
	private String question;	
	private String answer;
	private String category;
	
	DeckManager dManager; //Objekt von DeckManager, um auf Methoden zuzugreifen
	
	/**
	 * Konstruktor für FlashcardManager
	 * @param dManager als DeckManager
	 */
	public FlashcardManager(DeckManager dManager) {
		this.dManager = dManager;
	}
	
	/**
	 * erstellt eine neue Flashcard und fügt sie dem übergebenen Deck hinzu
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 */
	public void createFlashcard(Scanner myScanner) {
		Deck selectedDeck = null;
		try {
			selectedDeck = dManager.selectDeck(myScanner);
		} catch (IllegalArgumentException e1) {
		    System.out.println(e1.getMessage());
		} catch(NoSuchElementException e2) {
			System.out.println(e2.getMessage());
		}
		
		System.out.println("Geben Sie die Frage ein:");
		question = myScanner.nextLine();
		System.out.println("Geben Sie die Antwort ein:");
		answer = myScanner.nextLine();
		System.out.println("Geben Sie die Kategorie ein:");
		category = myScanner.nextLine();
		
		if(question.isBlank() || answer.isBlank() || category.isBlank())throw new IllegalArgumentException("Fehler: Eingabe darf nicht leer sein!");
		
		Flashcard newCard = new Flashcard(question, answer, category);
		selectedDeck.addCard(newCard);
		
		System.out.println("Karteikarte wurde erstellt!");
		
		JsonStorage.saveDecksToFile(dManager.getDecks());
	
	}
	
	/**
	 * dient zur Bearbeitung von Attributen einer Karteikarte
	 * und ermöglicht löschen einer Karteikarte
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 */
	public void manageFlashcards(Scanner myScanner){	
		Deck selectedDeck = null;
		try {
			selectedDeck = dManager.selectDeck(myScanner);
		} catch (IllegalArgumentException e1) {
		    System.out.println(e1.getMessage());
		} catch(NoSuchElementException e2) {
			System.out.println(e2.getMessage());
		}
		
		ArrayList<AbstractCard> cards = selectedDeck.getCards();	//ArrayListe als Deck für alle Karten
		int cardNumber;		//Nummer bzw. Index von Karteikarte
		
		if(cards.size() == 0) {
			throw new NoSuchElementException("Es sind noch keine Karteikarten vorhanden");
		}
		
		for(int i = 0; i < cards.size(); i++) {
			System.out.println((i + 1) + ". " + cards.get(i));
		}
		System.out.println("Wählen Sie die Nummer der Karteikarte aus, die Sie bearbeiten möchten");
		cardNumber = myScanner.nextInt();
		myScanner.nextLine();	//falsche Eingabe aus dem Puffer löschen
		
		if (cardNumber < 1 || cardNumber > cards.size()) {
			throw new NoSuchElementException("Fehler: Ungültige Kartennummer");
		}
		
		AbstractCard selectedCard = cards.get(cardNumber - 1);
		
		System.out.println("Wählen Sie eine Option");
		System.out.println("1. Karteikarte ändern");
		System.out.println("2. Karteikarte löschen");
		System.out.println("3. Zurück zum Menü");
		
		int choice = myScanner.nextInt();	//Nutzer gibt Option ein
		myScanner.nextLine();	//falsche Eingabe aus dem Puffer löschen
		
		switch (choice) {
		case 1:
			try {
				changeFlashcard(selectedCard,myScanner
						
						);
			} catch(InputMismatchException e1) {
				System.out.println(e1.getMessage());
			} catch(IllegalArgumentException e2) {
				System.out.println(e2.getMessage());
			}
			break;
		case 2:
			try {
				deleteFlashcard(selectedDeck, cardNumber - 1);
			} catch(NoSuchElementException e1) {
				System.out.println(e1.getMessage());
			}
				break;
		case 3:
			return;
		default:
			throw new InputMismatchException("Fehler: Bitte gültige Option wählen!");		//falls Zahl grösser als 3
		}
	}
	
	/**
	 * Entfernt eine Karte aus dem Deck an der angegebenen Position
	 * @param deck Deck, aus dem eine Karte entfernt wird
	 * @param index Index der zu entfernenden Karte
	 */
	public void deleteFlashcard(Deck deck, int index) {
		if(index >= deck.getCards().size() || index < 0 ) throw new NoSuchElementException("Dieses Deck ist nicht vorhanden");
		deck.removeCard(index);
	}
	
	/**
	 * ermöglicht änderung der Eigenschaften einer Karte
	 * @param card Karte, die geändert werden soll
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 */
	public void changeFlashcard(AbstractCard card, Scanner myScanner) {
				
		System.out.println("Welche Eigenschaft möchten Sie ändern?");
		System.out.println("1. Frage");
		System.out.println("2. Antwort");
		System.out.println("3. Kategorie");
	
		int choice = myScanner.nextInt();	//Nutzer gibt Option ein
		myScanner.nextLine(); //falsche Eingabe aus dem Puffer löschen
		
		System.out.println("Geben Sie die Änderung ein:");
		String change = myScanner.nextLine();	//Änderung des Nutzers
		
		if(change.isEmpty()) {
			throw new IllegalArgumentException("Name darf nicht leer sein!");
		}
		switch (choice) {
		case 1:
			card.setQuestion(change);
			break;
		case 2: 
			card.setAnswer(change);
			break;
		case 3:
			card.setCategory(change);
			break;
		//falls Zahl grösser als 3
		default:
			throw new InputMismatchException("Fehler: Bitte gültige Option wählen!");
			
		}
		JsonStorage.saveDecksToFile(dManager.getDecks());
	}
	
	
}
