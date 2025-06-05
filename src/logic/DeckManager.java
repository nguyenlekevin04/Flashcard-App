package logic;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import model.AbstractCard;
import model.Deck;
import persistence.JsonStorage;

/**
 * Diese Klasse beschreibt verschiedene Methoden zur Klasse Deck, um diese zu verwalten.
 * Dabei kann man Decks löschen, Decks abfragen und Fortschritte nachverfolgen.
 */
public class DeckManager {
	
	private Scanner myScanner = new Scanner(System.in);		//Scanner zum Einlesen von Eingaben
	private ArrayList<Deck> decks;	//ArrayList für alle Decks
	
	
	/**
	 * Kontruktor für DeckManager
	 * @param decks als ArrayList
	 */
	  public DeckManager(ArrayList<Deck> decks) {
	        this.decks = decks;
	  }
	
	
	 /**
     * Erstellung eines neuen Decks mit einem vom Benutzer eingegebenen Namen
     * @param myScanner Scanner zum Einlesen von Eingaben
     * @return ein neues Deck-Objekt mit dem eingegebenen Namen
     */
	public Deck createDeck(Scanner myScanner) {
		
		System.out.println("Gib den Namen des neuen Decks ein:");
		String deckName = myScanner.nextLine();		//Nutzer gibt Namen ein
		
		if(deckName.isBlank()) {
			throw new IllegalArgumentException("Fehler: Name darf nicht leer sein!");
		}
		
		Deck deck = new Deck(deckName);		//neues erstelltes Deck
		decks.add(deck);
		
		JsonStorage.saveDecksToFile(decks);	//Speicherung
		System.out.println("Neues Deck wurde erstellt!");
			
		return deck;	//Rückgabe des Decks
		
		
	}
	
	/**
	 * dient zum Löschen eines vom Nutzer ausgewählten Decks
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 */
	public void deleteDeck(Scanner myScanner) {
		Deck selectedDeck = null;
		try {
			selectedDeck = selectDeck(myScanner);
		} catch (IllegalArgumentException e1) {
		    System.out.println(e1.getMessage());
		    return;
		} catch(NoSuchElementException e2) {
			System.out.println(e2.getMessage());
		}
		
		decks.remove(selectedDeck);
		
		JsonStorage.saveDecksToFile(decks);	//Speicherung
		
		System.out.println("Deck wurde gelöscht!");
		
	}
    
	/**
	 * ermöglicht dem Nutzer ein Deck auszuwählen
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 * @return selectedDeck Deck, welches ausgewählt wurde
	 */
	public Deck selectDeck(Scanner myScanner) {
		try {
			if(decks.size() == 0) {
				throw new NoSuchElementException("Es sind noch keine Decks vorhanden! Bitte ein neues Deck erstellen!");
			}
			
			System.out.println("Bitte Deck auswählen");
			for(int i = 0; i < decks.size(); i++) {
				System.out.println((i + 1) + ". " + decks.get(i));
			}
		  
			int selected = myScanner.nextInt();		//ausgewähltes Deck des Nutzers
			myScanner.nextLine();		//falsche Eingabe aus dem Puffer löschen
			
			if(selected < 1 || selected > decks.size()) {
				throw new NoSuchElementException("Fehler: Angegebenes Deck existiert nicht!"); 
			}
			
			Deck selectedDeck = decks.get(selected - 1);
			
			return selectedDeck;
		} catch(InputMismatchException e1) {
			myScanner.nextLine();		//falsche Eingabe aus dem Puffer löschen
			throw new IllegalArgumentException("Fehler: Bitte gültige Option wählen!", e1);
		}
	}
	
	/**
	 * dient zur Abfragung von den Karteikarten
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 * @throws InputMismatchExcception wenn kein Integer eingegeben
	 */
	public void quizMode(Scanner myScanner) throws InputMismatchException{
		Deck selectedDeck = null;
		try {
			selectedDeck = selectDeck(myScanner);
		} catch (IllegalArgumentException e1) {
		    System.out.println(e1.getMessage());
		} catch(NoSuchElementException e2) {
			System.out.println(e2.getMessage());
		}
		
		Collections.shuffle(selectedDeck.getCards());
		
		System.out.println("1. Gesamtes Deck abfragen");
		System.out.println("2. Nach Kategorie sortieren");
		System.out.println("3. Zurück zum Menü");
		
		int choice = myScanner.nextInt();	//Nutzer gibt Option ein
		myScanner.nextLine();	//falsche Eingabe aus dem Puffer löschen
		
		switch(choice) {
		case 1:
			try {
				quizWithWholeDeck(selectedDeck, myScanner);
			}catch(IllegalArgumentException e1) {
				System.out.println("Fehler: " + e1.getMessage());
			}
			break;
		case 2:
			try {
				quizByCategory(selectedDeck, myScanner);
			}catch(IllegalArgumentException e1) {
				System.out.println("Fehler: " + e1.getMessage());
			}
			break;
		case 3:
			return;
		default:
			System.out.println("Bitte gültige Zahl eingeben!");
		}
	
	}
	
	/**
	 * dient zur Abfrage der Karteikarten nach Kategorie
	 * @param selectedDeck Deck, welches vom Nutzer vorher ausgewählt wurde
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 */
	public void quizByCategory(Deck selectedDeck, Scanner myScanner) {
		int correct = 0;	//richtige Antwort
		int incorrect = 0;	//falsche Antwort
		
		System.out.println("Bitte Kategorie eingeben:");
		String userCategory = myScanner.nextLine();
		
		if(userCategory.isBlank()) {
			throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
		};
		
		try{
			Deck filteredDeck = filterByCategory(selectedDeck, userCategory);
		
		
		
			for(AbstractCard card : filteredDeck.getCards()) {
				System.out.println("Frage: " + card.getQuestion());
		        System.out.print("Antwort: ");
		        String userAnswer = myScanner.nextLine();	//Antwort des Nutzers
		        
		        if(userAnswer.equalsIgnoreCase(card.getAnswer())) {
		        	System.out.println("Richtig!");
		        	card.incrementCorrect();
		            correct++;
		        }else if(userAnswer.isBlank()) {
		        	throw new IllegalArgumentException("Eingabe darf nicht leer sein! Frage wird übersprungen!");
		        }else {
		        	System.out.println("Falsch!");
		        	card.incrementIncorrect();
		            incorrect++;
		        }
			}
			System.out.println("Es wurden " + correct + " Fragen richtig und " + incorrect + " Fragen falsch beantwortet.");
		} catch(NoSuchElementException e1) {
			System.out.println(e1.getMessage());
		}
	}

	
	/**
	 * gibt ein Deck nach Kategorie gefiltert zurück
	 * @param deck Deck, aus dem bestimmte Karteikarten nach Kategorie gefiltert werden
	 * @param category String, die ausgewählte Kategorie des Nutzers
	 * @return	filteredDeck Deck, das gefiltert ist nach Kategorie
	 */
	public Deck filterByCategory(Deck deck, String category) {
		Deck filteredDeck = new Deck(category);
		for(AbstractCard card : deck.getCards()) {
			if(card.getCategory().equalsIgnoreCase(category))filteredDeck.addCard(card);
		}
		
		if(filteredDeck.getCards().size() == 0) {
			 throw new NoSuchElementException("Keine Karteikarten aus dieser Kategorie vorhanden!");
		}
		return filteredDeck;
	}
	
	/**
	 * eine Hilfsmethode, die dazu dient das gesamte Deck abzufragen
	 * @param selectedDeck Deck, das abgefragt wird
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 */
	public void quizWithWholeDeck(Deck selectedDeck, Scanner myScanner) {
		int correct = 0;	//richtige Antwort
		int incorrect = 0;	//falsche Antwort
		
		for (AbstractCard card : selectedDeck.getCards()) {
			System.out.println("Frage: " + card.getQuestion());
	        System.out.print("Antwort: ");
	        String userAnswer = myScanner.nextLine();	//Antwort des Nutzers
	        
	        if(userAnswer.equalsIgnoreCase(card.getAnswer())) {
	        	System.out.println("Richtig!");
	        	card.incrementCorrect();
	            correct++;
	        }else if(userAnswer.isBlank()) {
	        	throw new IllegalArgumentException("Eingabe darf nicht leer sein! Frage wird übersprungen!");
	        }else {
	        	System.out.println("Falsch!");
	        	card.incrementIncorrect();
	            incorrect++;
	        }
		}
		System.out.println("Es wurden " + correct + " Fragen richtig und " + incorrect + " Fragen falsch beantwortet.");
	}
	
	/**
	 * ermöglicht Fortschritte von jedem Deck zu sehen und auch nach Kategorie zu filtern
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 * @throws InputMismatchException wenn kein Integer eingegeben wird
	 */
	public void trackProgress(Scanner myScanner) throws InputMismatchException{
		
		Deck selectedDeck = null;
		try {
			selectedDeck = selectDeck(myScanner);
		} catch (IllegalArgumentException e1) {
		    System.out.println(e1.getMessage());
		} catch(NoSuchElementException e2) {
			System.out.println(e2.getMessage());
		}
		
		if(selectedDeck.getCards().size() == 0) {
			System.out.println("Es sind noch keine Karteikarten vorhanden!");
			return;
		}
		System.out.println("1. Fortschritt vom gesamten Deck");
		System.out.println("2. Fortschritt nach Kategorie");
		System.out.println("3. Zurück zum Menü");
		
			int choice = myScanner.nextInt();	//Nutzer gibt Option ein
			myScanner.nextLine();	//falsche Eingabe aus dem Puffer löschen
			
			switch(choice) {
			case 1:
				try {
					totalProgress(selectedDeck);
				} catch(IllegalArgumentException e1) {
					System.out.println("Fehler: " + e1.getMessage());
				}
				break;
			case 2:
				try {
					progressByCategory(selectedDeck, myScanner);
				} catch(IllegalArgumentException e1) {
					System.out.println("Fehler: " + e1.getMessage());
				}
				break;
			case 3:
				return;
			default:
				System.out.println("Fehler: Bitte gültige Option wählen!");		//falls Nutzer Zahl grösser als 3 eingibt
			}
		
	}
	
	/**
	 * ermöglicht Fortschritt nach Kategorie zu sehen
	 * @param selectedDeck Deck, das vom Nutzer ausgewählt wird
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 */
	public void progressByCategory(Deck selectedDeck, Scanner myScanner) {
		System.out.println("Bitte Kategorie eingebebn:");
		String userCategory = myScanner.nextLine();
		
		if(userCategory.isBlank())throw new IllegalArgumentException("Eingabe darf nicht leer sein!");
		
		try {
			Deck filteredDeck = filterByCategory(selectedDeck, userCategory); //Deck nach Kategorie gefiltert
			
			int totalCorrect = 0 ;	//Anzahl aller korrekten Antworten
			int totalIncorrect = 0;	//Anzahl aller falschen Antworten
			
			for(AbstractCard card: filteredDeck.getCards()) {
				totalCorrect += card.getCorrectCount();
				totalIncorrect += card.getIncorrectCount();
			}
			
			//falls noch keine Abfrage durch den Quiz-Mode stattfand
			if(totalCorrect == 0 && totalIncorrect == 0) {
				throw new NoSuchElementException("Diese Kategorie wurde noch nicht abgefragt!");
			}
			double progress = ((double) totalCorrect / (totalCorrect + totalIncorrect)) * 100;
			System.out.println("Kategorie: " + userCategory + "	Anzahl richtig: " + totalCorrect + "	Anzahl falsch: " + totalIncorrect + "	Fortschritt: " + progress + "%");
		} catch(NoSuchElementException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	/**
	 * der Fortschritt eines Decks kann verfolgt werden
	 * @param decks ArrayList, von denen eine bestimmte ausgewählt wird, um den Fortschritt anzuzeigen
	 */
	public void totalProgress(Deck selectedDeck){
		int totalCorrect = 0 ;	//Anzahl aller korrekten Antworten
		int totalIncorrect = 0;	//Anzahl aller falschen Antworten

		for(AbstractCard card: selectedDeck.getCards()) {
			totalCorrect += card.getCorrectCount();
			totalIncorrect += card.getIncorrectCount();
		}
		
		//falls noch keine Abfrage durch den Quiz-Mode stattfand
		if(totalCorrect == 0 && totalIncorrect == 0) {
			System.out.println("Dieses Deck wurde noch nicht abgefragt");
			return;
		}
		double progress = ((double) totalCorrect / (totalCorrect + totalIncorrect)) * 100;
		System.out.println("Name: " + selectedDeck.getName() + "	Anzahl richtig: " + totalCorrect + "	Anzahl falsch: " + totalIncorrect + "	Fortschritt: " + progress + "%");
	}
	
	/**
	 * Speicherung von Decks
	 */
	public void saveDecks() {
	    JsonStorage.saveDecksToFile(decks);
	}
	
	/**
	 * gibt die Decks zurück
	 * @return decks ArrayList, die alle Decks enthält
	 */
	public ArrayList<Deck> getDecks() {
	    return decks;
	}
	
	/**
	 * Zeigt alle Karten an und bietet die Möglichkeit nach alphabet zu sortieren
	 * @param myScanner Scanner zum Einlesen von Eingaben
	 * @throws InputMismatchException wenn kein Integer eingegeben wird
	 */
	public void showCards(Scanner myScanner) throws InputMismatchException{
	
		Deck selectedDeck = null;
		try {
			selectedDeck = selectDeck(myScanner);
		} catch (IllegalArgumentException e1) {
		    System.out.println(e1.getMessage());
		} catch(NoSuchElementException e2) {
			System.out.println(e2.getMessage());
		}
		
		ArrayList<AbstractCard> cards = selectedDeck.getCards();	//ArrayListe als Deck für alle Karten
		
		if(cards.size() == 0) {
			throw new NoSuchElementException("Es sind noch keine Karteikarten vorhanden!");
		}
		
		System.out.println("1. Karten in Reihenfolge anzeigen");
		System.out.println("2. Karten nach alphabet sortiert anzeigen");
		System.out.println("3. Zurück zum Menü");
		
		int choice = myScanner.nextInt();	//Nutzer gibt Option ein
		myScanner.nextLine();	//falsche Eingabe aus dem Puffer löschen
		
		switch(choice) {
		case 1:
			for(int i = 0; i < cards.size(); i++) {
				System.out.println((i + 1) + ". " + cards.get(i));
			}
			break;
		case 2:
			cards.sort(Comparator.comparing(AbstractCard::getCategory, String.CASE_INSENSITIVE_ORDER));		//sortiert die Karten nach Kategorie alphabetisch unabhängig von Gross-/Kleinschreibung
			for(int i = 0; i < cards.size(); i++) {
				System.out.println((i + 1) + ". " + cards.get(i));
			}
			break;
		case 3:
			return;
		default:
			System.out.println("Fehler: Bitte gültige Option wählen!");
		}
		
	}

}
