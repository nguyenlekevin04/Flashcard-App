Flashcard App

Beschreibung
Dieses Projekt ist eine Java-Konsolenanwendung zum Verwalten von Karteikarten-Decks angelehnt an Anki.
Es bietet Funktionen wie Erstellen, Löschen, Abfragen und Fortschrittsverfolgung von Decks, sowie Bearbeiten von Karteikarten innerhalb der Decks.
Die Daten werden in JSON-Dateien gespeichert und geladen.

Voraussetzungen
Java JDK 11 oder höher
Gson Bibliothek (für JSON-Verarbeitung)
JUnit 5 (für Unit-Tests)

Setup
Gson hinzufügen
*Lade die Gson-Bibliothek (jar) herunter:
 https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
 Füge die gson-2.10.1.jar zu deinem Projektclasspath hinzu.
*In IDEs wie Eclipse: Rechtsklick auf Projekt → Properties → Java Build Path → Libraries → Add External JARs → Gson-JAR auswählen
*In IntelliJ: File → Project Structure → Modules → Dependencies → + → JARs or directories → Gson-JAR auswählen

JUnit 5 einrichten
Lade die JUnit 5 Plattform JARs oder verwende Build-Tools wie Maven oder Gradle.
Falls manuell: Mindestens folgende JARs nötig:
 junit-jupiter-api-5.x.x.jar
 junit-jupiter-engine-5.x.x.jar
 apiguardian-api-x.x.x.jar
 opentest4j-x.x.x.jar
 commons-logging-x.x.jar
Füge die JUnit 5 JARs deinem Test-Classpath hinzu.
In IDEs sind JUnit 5 meist bereits integriert oder können einfach über "Add Framework Support" aktiviert werden.

Projektstruktur
logic – Steuerung, Menüführung, Benutzerinteraktion
model – Datenklassen wie Deck, AbstractCard
persistence – Dateizugriff und Speicherung (JsonStorage)
tests – JUnit-Testklassen (optional)

