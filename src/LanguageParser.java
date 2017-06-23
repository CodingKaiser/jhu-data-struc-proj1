/**
 * Created by falko on 20-06-17.
 */
public interface LanguageParser {
    boolean isPatternMatch();
    void handleLetter(Character letter);
    void resetParser();
}
