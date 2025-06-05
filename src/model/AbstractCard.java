package model;

/**
 * Diese abstrakte Klasse ist eine Implementierung vom Interface Card und dient als Oberklasse für die Klasse Flashcard, um weitere Methoden zu implementieren.
 */
public abstract class AbstractCard implements Card{
    protected String question;
    protected String answer;
    protected String category;
    protected int correctCount;
    protected int incorrectCount;

    /**
     * Konstruktor für Card
     * @param question die Frage auf der Karteikarte als String
     * @param answer die Antwort zur Frage als String
     * @param category die Kategorie als String
     */
    public AbstractCard(String question, String answer, String category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }
    
    /**
     * Standardkonstruktor für leere Karteikarten
     */
    public AbstractCard() {
    	
    }
    
    /**
     * setzt die Frage der Karteikarte
     * @param question die neue Frage als String
     */
    @Override
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/**
	 * gibt die Frage der Karteikarte zurück
	 * @return Frage als String
	 */
    @Override
	public String getQuestion() {	
		return question;
	}
	    
	/**
	 * setzt die Antwort
	 * @param answer neue Antwort als String
	 */
    @Override
	public void setAnswer(String answer) {
        this.answer = answer;
    }
	    
	/**
	 * gibt die Antwort zurück
	 * @return Antwort als String
	 */
    @Override
	public String getAnswer() {
		return answer;
	}
	    
	/**
	 * setzt die Kategorie
	 * @param category neue Kategorie als String
	 */
    @Override
	public void setCategory(String category) {
		this.category = category;
	}
	    
	/**
	 * gibt die Kategorie zurück
	 * @return Kategorie als String
	 */
    @Override
	public String getCategory() {
		return category;
	}
	    
	/**
	 * gibt die Anzahl der korrekten Versuche zurück
	 * @return Anzahl korrekter Versuche als int
	 */
    @Override
	public int getCorrectCount() {
		return correctCount;
	}
	    
	/**
	 * gibt die Anzahl der falscher Versuche zurück
	 * @return Anzahl falscher Versuche als int
	 */
    @Override
	public int getIncorrectCount() {
		return incorrectCount;
	}
	
	/**
    * abstrakte Methode zum Zurücksetzen des Zählers für richtige Antworten
    * muss von Unterklassen implementiert werden
    */
	public abstract void resetCorrectCount();
	
	/**
	 * abstrakte Methode zum Zurücksetzen des Zählers für falsche Antworten
	 * muss von Unterklassen implementiert werden
	 */
	public abstract void resetIncorrectCount();
	
	/**
	 * abstrakte Methode zum erhöhen der Anzahl für korrekte Versuche
	 * muss von Unterklassen implementiert werden    
	 */
	public abstract void incrementCorrect();
	
	/**
	 * abstrakte Methode zum erhöhen der Anzahl für falsche Versuche
	 * muss von Unterklassen implementiert werden    
	 */
	public abstract void incrementIncorrect();
	
	/**
    * abstrakte Methode toString 
    * muss von Unterklassen implementiert werden    
    * @return String-Darstellung der Karteikarte
    */
	public abstract String toString();
}
