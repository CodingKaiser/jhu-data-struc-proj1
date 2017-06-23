/**
 * Created by falko on 20-06-17.
 */
public class L1Parser implements LanguageParser {

    private StackLL<Character> st;

    L1Parser() {
        this.st = new StackLL<>();
    }

    public void handleLetter(Character letter) {
        if (this.st.isEmpty()) {
            this.st.push(letter);
        } else if (letter.equals('A') && this.st.peek().equals('B') ||
                   letter.equals('B') && this.st.peek().equals('A')) {
            try {
                this.st.pop();
            } catch (StackUnderflowException e) {
                System.err.println(e);
                System.err.println("Algorithm doesn't handle all cases. Disregard L1 parser's answers.");
            }
        } else if (letter.equals('A') && this.st.peek().equals('A') ||
                   letter.equals('B') && this.st.peek().equals('B') ||
                   !letter.equals('A') && !letter.equals('B')) {
            this.st.push(letter);
        }
    }

    public boolean isPatternMatch() {
        return st.isEmpty();
    }

    public void resetParser() {
        this.st = new StackLL<>();
    }
}
