package model;

import java.util.ArrayList;

/**
 * Dieses Interface dient als Vorlage für die Klasse Deck und wird von dieser implementiert.
 */
public interface DeckInterface {
		/**
		 * gibt den Namen des Decks zurück
		 * @return Name des Decks als String
		 */
		public String getName();
		
		/**
		 * setzt den Namen des Decks
		 * @param Name des Decks als String
		 */
		public void setName(String name);
		
		/**
		 * fügt Karten zum Deck hinzu
		 * @param Karteikarte als Card
		 */
		public void addCard(AbstractCard card);

		/**
		 * löscht Karten vom Deck 
		 * @param Karteikarte als Card
		 */
		public void removeCard(int index);
		
		/**
		 * gibt die Karteikarten im Deck zurück
		 * @return Karten im Deck als ArrayList<Card>
		 */
		public ArrayList<AbstractCard> getCards();
		
		/**
		 * mischt die Karten im Deck
		 */
		public void shuffle();
	
}
