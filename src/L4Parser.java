/**
 * Created by falko on 22-06-17.
 */
public class L4Parser implements LanguageParser {

    private StackLL<Character> inOrder;
    private StackLL<Character> repeatedPattern;
    private boolean patternIsLocked;

    public L4Parser() {
        this.inOrder = new StackLL<>();
        this.repeatedPattern = new StackLL<>();
        this.patternIsLocked = false;
    }

    public void handleLetter(Character letter) {
        if (letter.equals('A') || letter.equals('B')) {
            if (repeatedPattern.isEmpty() && inOrder.isEmpty()) {
                repeatedPattern.push(letter);
            } else if (inOrder.isEmpty() && repeatedPattern.peek().equals('B') && letter.equals('A')) {
                patternIsLocked = true;
                while (!repeatedPattern)
            }
        } else {
            inOrder.push(letter);
        }
    }

    public boolean isPatternMatch() {
        return inOrder.isEmpty();
    }

    public void reesetParser() {
        this.inOrder = new StackLL<>();
        this.repeatedPattern = new StackLL<>();
        this.patternIsLocked = false;
    }
}
