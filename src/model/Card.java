package model;

/**
 * Das Interface Card dient zur Vorlage für die Klasse AbstractCard und soll von dieser implementiert werden.
 */
public interface Card {
	/**
	*setzt die Frage auf der Karteikarte
	*@param question neue Frage als String
	*/
	public void setQuestion(String question);
	
	/**
	*gibt die Frage auf der Karteikarte zurück 
	*@return Frage als String
	*/
	public String getQuestion();
	
	/**
	*setzt die Antwort auf der Karteikarte
	*@param answer neue Antwort als String
	*/
	public void setAnswer(String answer);

	/**
	*gibt die Antwort auf der Karteikarte zurück 
	*@return Antwort als String
	*/
	public String getAnswer();
	
	/**
	*setzt die Kategorie auf der Karteikarte
	*@param category neue Kategorie als String
	*/
	public void setCategory(String category);
	
	/**
	*gibt die Kategorie auf der Karteikarte zurück 
	*@return Kategorie als String
	*/
	public String getCategory();
	
	/**
	*gibt die Anzahl richtiger Versuche der Karteikarte zurück 
	*@return Korrekte Punkzahl als Integer
	*/
	public int getCorrectCount();
	
	/**
	*gibt die Anzahl falscher Versuche der Karteikarte zurück 
	*@return falsche Punkzahl als Integer
	*/
	public int getIncorrectCount();
	
	/**
	 * erhöht die Anzahl korrekt beantworteter Fraen
	 */
	public void incrementCorrect();

	/**
	 * erhöht die Anzahl falsch beantworteter Fraen
	 */
	public void incrementIncorrect();
}
