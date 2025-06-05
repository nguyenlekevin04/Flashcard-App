package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import model.Deck;
import persistence.JsonStorage;
import model.AbstractCard;
import logic.FlashcardManager;
import logic.DeckManager;

/**
 * Das Programm handelt sich um einen Karteikarten-Manager, der dem Nutzer ermöglicht über die Konsole Decks zu erstellen, in denen Karteikarten gepspeichert werden sollen.
 * Nach jedem Start sind die Decks mit den Karteikarten wieder auffindbar und die Karten können bearbeitet werden mit Frage, Antwort und Kategorie.
 * Man kann die Attribute der Karteikarten ändern und auch die ganze Karteikarte löschen.
 * Außerdem gibt es einen Abfrage-Modus, in dem man die Karteikarten wiederholen kann und die Punktzahl gespeichert wird. Es ist möglich das ganze Deck oder auch nach Kategorie sortiert abzufragen.
 * Dazu ist es möglich den Fortschritt von jedem Deck zu sehen und auch nach Kategorie sortiert.
 * 
 * @author Kevin 
 * @version 1.0
 */
public class Main {
	/**
     * zeigt ein Menü an, um Karteikarten zu erstellen, bearbeiten, abzufragen oder Fortschritte anzuzeigen
     * @param args Kommandozeilenargumente 
     */
	public static void main(String[] args) {
		
		Scanner myScanner = new Scanner(System.in); //Scanner für Benutzereingaben
		int choice = 0;		//Variable für Menü-Auswah
		ArrayList<Deck> decks = JsonStorage.loadDecksFromFile();
	    if (decks == null) {
	    	decks = new ArrayList<>();
	    }
	        
	    DeckManager dManager = new DeckManager(decks);	//Obejekt des DeckManager, um auf Methoden zuzugreifen
		FlashcardManager fManager  = new FlashcardManager(dManager);	//Obejekt des FlashcardManager, um auf Methoden zuzugreifen
		
		do {
			//Einzelnen Menü-Punkte	
			System.out.println("\n");
			System.out.println("Karteikarten Manager");
			System.out.println("0. Neues Deck erstellen");
			System.out.println("1. Decks löschen");
			System.out.println("2. Karteikarten erstellen");
			System.out.println("3. Karteikarten bearbeiten (ändern/löschen)");
			System.out.println("4. Karteikarten abfragen");
			System.out.println("5. Fortschritt anzeigen");
			System.out.println("6. Karteikarten anzeigen");
			System.out.println("7. Programm beenden");
			
			try {
				choice = myScanner.nextInt();	//Auswahl des Nutzers
				myScanner.nextLine(); 	//falsche Eingabe aus dem Puffer löschen
		
			
				switch (choice) {
				case 0:
					try {
						dManager.createDeck(myScanner);
					}catch(IllegalArgumentException e1) {
						System.out.println(e1.getMessage());
					}
					break;
				case 1:
					dManager.deleteDeck(myScanner);
					break;
				case 2:
					try {
						fManager.createFlashcard(myScanner);
					} catch(IllegalArgumentException e1) {
						System.out.println(e1.getMessage());
					}
					break;
				case 3:
					try {
						fManager.manageFlashcards(myScanner);
					} catch(InputMismatchException e1) {
						System.out.println(e1.getMessage());
					} catch(NoSuchElementException e2) {
						System.out.println(e2.getMessage());
					} 
					break;
				case 4:
					try {
						dManager.quizMode(myScanner);
					}catch(InputMismatchException e1) {
						System.out.println("Fehler: " + e1.getMessage());
					}
					break;
				case 5:
					try {
						dManager.trackProgress(myScanner);
					}catch(InputMismatchException e1) {
						System.out.println("Fehler: Bitte gültige Option wählen!"); //falls Nutzer keinen Integer eingibt
					}
					break;
				case 6:
					try {
						dManager.showCards(myScanner);
					} catch(InputMismatchException e1) {
						System.out.println("Fehler: Bitte gültige Option wählen!");
					} catch(NoSuchElementException e2) {
						System.out.println(e2.getMessage());
					}
					break;
				case 7:
					JsonStorage.saveDecksToFile(decks);
					System.out.println("Programm wird beendet!");
					break;
				default:
					System.out.println("Fehler: Bitte gültige Option wählen!");		//falls Nutzer Zahl grösser als 7 eingibt
				}
				//falls Nutzer keinen Integer eingibt
			}catch(InputMismatchException e) {
				System.out.println("Fehler: Bitte gültige Option wählen!");
				myScanner.nextLine(); 	//falsche Eingabe aus dem Puffer löschen
				continue;
			}
		}while(choice != 7);
		
		myScanner.close();	//Scanner schliessen
	}

}
