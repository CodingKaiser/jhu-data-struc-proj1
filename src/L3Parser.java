import java.util.Stack;

/**
 * Created by falko on 21-06-17.
 */
public class L3Parser implements LanguageParser {

    private StackLL<Character> all;
    private StackLL<Character> bOnly;

    L3Parser() {
        all = new StackLL<>();
        bOnly = new StackLL<>();
    }

    public void handleLetter(Character letter) {
        if (letter.equals('B')) {
            bOnly.push(letter);
        }
        all.push(letter);
    }

    public boolean isPatternMatch() {
        while (!this.all.isEmpty() && this.all.peek().equals('B')) {
            try {
                this.all.pop();
            } catch (StackUnderflowException e) {
                System.err.println(e);
                System.err.println("Algorithm doesn't handle all cases. Disregard L3 parser's answers.");
            }
        }
        while (!all.isEmpty()) {
            try {
                Character allTop = all.pop();
                if (allTop.equals('A')) {
                    if (bOnly.isEmpty()) {
                        return false;
                    } else {
                        Character bTop = bOnly.pop();
                        if (bOnly.isEmpty()) {
                            return false;
                        } else {
                            bOnly.pop();
                        }
                    }
                } else {
                    return false;
                }
            } catch (StackUnderflowException e) {
                System.err.println(e);
                System.err.println("Algorithm doesn't handle all cases. Disregard L3 parser's answers.");
                break;
            }
        }
        return bOnly.isEmpty();
    }

    public void resetParser() {
        all = new StackLL<>();
        bOnly = new StackLL<>();
    }
}
