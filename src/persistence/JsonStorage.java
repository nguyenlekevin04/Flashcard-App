package persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import model.Deck;

/**
 * Diese Klasse dient zum Speichern und Laden von Daten aus einer Json-Datei, die alle Decks und Karten speichert.
 * Karten werden beim Programmstart geladen und beim erstellen von Objekten werden diese direkt gespeichert.
 */
public class JsonStorage {
	
    private static final String FILENAME = "decks.json";	//Name der json-Datei
    
    /**
     * dient zur Speicherung von allen Decks und darin enthaltenen Karteikarten
     * @param decks ArrayList, die alle erstellten Decks speichert
     */
	public static void saveDecksToFile(ArrayList<Deck> decks) {
		Gson gson = new Gson();
		try(FileWriter writer = new FileWriter(FILENAME)){
			gson.toJson(decks,writer);
			System.out.println("Änderungen wurden gespeichert.");
		}catch (IOException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}
	
	/**
	 * lädt die erstellten Decks aus der Json Datei
	 * @return die geladenen Decks in einer neuen ArrayList
	 */
	public static ArrayList<Deck> loadDecksFromFile(){
		Gson gson = new Gson();
		try(FileReader reader = new FileReader(FILENAME)){
			Type listType = new TypeToken<ArrayList<Deck>>() {}.getType();
			return gson.fromJson(reader, listType);
		}catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
            return new ArrayList<>(); // leere Liste zurückgeben bei Fehler
        }
	}
}
