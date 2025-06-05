/**
 * 
 */
/**
 * 
 */
module flashcards {
	requires com.google.gson;
	opens model to com.google.gson;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.engine;
    requires org.junit.platform.commons;
}