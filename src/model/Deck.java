package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Die Klasse Deck ist eine Implementierung vom Interface DeckInterface und beschreibt ein Deck mit dessen Namen und eine darin enthaltene ArrayList von Flashcards.
 * Diese werden in einer Json Datei gespeichert und können jederzeit wieder aufgerufen werden.
 */
public class Deck implements DeckInterface{

	private String name;	//Name des Decks
	private ArrayList<Flashcard> cards; //Array in dem die Karteikarten gespeichert werden
	
	/**
	 * Konstruktor der neue Decks erstellt
	 * @param Name des Decks als String
	 */
	public Deck(String name) {
		this.name = name;
		this.cards = new ArrayList<>();
	}
	
	/**
	 * Konstruktor ohne Parameter
	 */
	public Deck() {
	    this.cards = new ArrayList<>();
	}
	/**
	 * gibt den Namen des Decks zurück
	 * @return Name des Decks als String
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * setzt den Namen des Decks
	 * @param Name des Decks als String
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * fügt Karten zum Deck hinzu
	 * @param Karteikarte als Card
	 */
	@Override
	public void addCard(AbstractCard card) {
	        cards.add((Flashcard) card);
	}

	/**
	 * löscht Karten vom Deck 
	 * @param Karteikarte als Card
	 */
	@Override
	public void removeCard(int index) {
		 if(index >= 0 && index < cards.size()) {
		        cards.remove(index);
		 }
	}
	
	/**
	 * gibt die Karteikarten im Deck zurück
	 * @return Karten im Deck als ArrayList<Flashcard>
	 */
	@Override
	public ArrayList<AbstractCard> getCards(){
		return new ArrayList<AbstractCard>(cards);
	}
	
	/**
	 * mischt die Karten im Deck
	 */
	@Override
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * gibt eine String-Darstellung des Decks und ihren Attributen zurück
     * @return String mit Informationen zum Deck
	 */
    @Override
    public String toString() {
        return "Deck '" + name + "' mit " + cards.size() + " Karte(n).";
    }
}
