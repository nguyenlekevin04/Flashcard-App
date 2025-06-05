package model;

/**
 * Die Klasse Flashcard ist eine Unterklasse von AbstractCard und beschreibt eine Karteikarte mit den Attributen Frage, Antwort und Kategorie. Ausserdem werden Punktzahl von richtig und falsch beantworteten Fragen gespeichert.
 * Es sind verschiedene Methoden implementiert, darunter Getter-/Setter-Methoden für die ganzen Attribute.
 * Diese sollen in Decks gespeichert werden.
 */
public class Flashcard extends AbstractCard{
	
	/**
	*Konstruktor zum Erstellen einer neuen Karteikarte 
	*@param question die Frage auf der Karteikarte
	*@param answer die Antwort auf die Frage
	*@param category zu welcher Kategorie sie gehört
	*/
	public Flashcard(String question, String answer, String category) {
		super(question, answer, category);
	}
	
	/**
	 *  Standardkonstruktor für leere Karteikarten
	 */
	public Flashcard() {
		super();
	}
	
	
	/**
	 * setzt die Anzahl richtiger Antworten auf 0 
	 */
	@Override
	public void resetCorrectCount() {
		this.correctCount = 0;
	}
	
	/**
	 * setzt die Anzahl richtiger Antworten auf Value
	 * @param value int, auf die die Anzahl richtiger Versuche gesetzt wird
	 */
	public void resetCorrectCount(int value) {
		this.correctCount = value;
	}
	

	
	/**
	 * setzt die Anzahl falscher Antworten auf 0 
	 */
	@Override
	public void resetIncorrectCount() {
		this.incorrectCount = 0;
	}
	
	/**
	 * setzt die Anzahl falscher Antworten auf Value
	 * @param value int, auf die die Anzahl falscher Versuche gesetzt wird
	 */
	public void resetIncorrectCount(int value) {
		this.incorrectCount = value;
	}
	
	/**
	 * erhöht die Anzahl korrekt beantworteter Fragen
	 */
	@Override
	public void incrementCorrect() {
		correctCount++;
	}
	/**
	 * erhöht die Anzahl korrekt beantworteter Fragen
	 * @param amount int, um das die Anzahl richtiger Versuche erhöht wird
	 */
	public void incrementCorrect(int amount) {
		correctCount += amount;
	}

	/**
	 * erhöht die Anzahl falsch beantworteter Fragen
	 */
	@Override
	public void incrementIncorrect() {
		incorrectCount++;
	}
	
	/**
	 * erhöht die Anzahl flasch beantworteter Fragen
	 * @param amount int, um das die Anzahl falscher Versuche erhöht wird
	 */
	public void incrementIncorrect(int amount) {
		incorrectCount += amount;
	}
	  /**
     * gibt eine String-Darstellung der Karteikarte mit allen Attributen zurück
     * @return String mit Informationen zur Karteikarte
     */
	@Override
	public String toString() {
		 return "Flashcard {" +
		           "Aufgabe = '" + question + '\'' +
		           ", Antwort = '" + answer + '\'' +
		           ", Kategorie = '" + category + '\'' +
		           ", Richtig beantwortet = " + correctCount +
		           ", Falsch beantwortet = " + incorrectCount +
		           '}';
	}

}
