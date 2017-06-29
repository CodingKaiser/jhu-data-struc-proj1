/**
 * An interface that defines the behavior for the language
 * parsers. All language parsers will implement this interface.
 * @author Falko Noe
 * @version 1.0
 */
public interface LanguageParser {
  boolean isPatternMatch();
  void handleLetter(Character letter);
  void resetParser();
  boolean isErrorOccurred();
}
