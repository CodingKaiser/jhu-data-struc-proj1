import java.util.Stack;

/**
 * Created by falko on 20-06-17.
 */
public class L2Parser implements LanguageParser {
    private StackLL<Character> all;
    private StackLL<Character> bOnly;

    L2Parser() {
        this.all = new StackLL<>();
        this.bOnly = new StackLL<>();
    }

    public void handleLetter(Character letter) {
        if (letter.equals('B')) {
            this.bOnly.push(letter);
        }
        this.all.push(letter);
//        if (this.st.isEmpty()) {
//            this.st.push(letter);
//        } else if (letter.equals('A')) {
//            this.st.push(letter);
//        } else if (letter.equals('B') && this.st.peek().equals('A')) {
//            this.st.pop();
//        } else {
//            this.st.push(letter); // Ensures stack fills with invalid characters
//        }
    }

    public boolean isPatternMatch() {
        try {
            while (!this.all.isEmpty() && this.all.peek().equals('B')) {
                this.all.pop();
            }
            while (!this.all.isEmpty() && !this.bOnly.isEmpty() &&
                    this.all.peek().equals('A') && this.bOnly.peek().equals('B')) {
                this.all.pop();
                this.bOnly.pop();
            }
        } catch (StackUnderflowException e) {
            System.err.println(e);
            System.err.println("Algorithm doesn't handle all cases. Disregard L2 parser's answers.");
        }
        return this.all.isEmpty() && this.bOnly.isEmpty();
    }

    public void resetParser() {
        all = new StackLL<>();
        bOnly = new StackLL<>();
    }
}
