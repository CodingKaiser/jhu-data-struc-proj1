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
        try {
            if (letter.equals('A') || letter.equals('B')) {
                if (repeatedPattern.isEmpty() && inOrder.isEmpty()) {
                    repeatedPattern.push(letter);
                } else if (inOrder.isEmpty() && repeatedPattern.peek().equals('B') && letter.equals('A')) {
                    patternIsLocked = true;
                    while (!repeatedPattern.isEmpty()) {
                        inOrder.push(repeatedPattern.pop());
                    }
                    if (letter.equals(inOrder.peek())) {
                        repeatedPattern.push(inOrder.pop());
                    } else {
                        inOrder.push(letter);
                    }
                } else if (inOrder.isEmpty() && !patternIsLocked) {
                    repeatedPattern.push(letter);
                } else if (!inOrder.isEmpty()) {
                    if (letter.equals(inOrder.peek())) {
                        repeatedPattern.push(inOrder.pop());
                    } else {
                        inOrder.push('C');
                    }
                } else {
                    inOrder.push('C');
                }
            } else {
                inOrder.push('C');
            }
        } catch (StackUnderflowException e) {
            System.err.println(e);
            System.err.println("Shouldn't be trusted.");
        }

    }

    public boolean isPatternMatch() {
        return inOrder.isEmpty();
    }

    public void resetParser() {
        this.inOrder = new StackLL<>();
        this.repeatedPattern = new StackLL<>();
        this.patternIsLocked = false;
    }
}
