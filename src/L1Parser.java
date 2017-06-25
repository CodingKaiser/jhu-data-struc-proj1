/**
 * A parser that parses the language L1:
 * (equal # of As and Bs in any order)
 * Strings supported:
 * ''
 * 'ABABAAABBB'
 * 'AAABBB'
 * 'ABABAB'
 *
 * @author Falko Noe
 * @version 1.0
 */
public class L1Parser implements LanguageParser {

    private StackLL<Character> st;

    /**
     * The constructor for the application
     */
    L1Parser() {
        this.st = new StackLL<>();
    }

    /**
     * Takes in a character and handles it according to L1
     * language constraints
     * @param letter: The character currently being parsed.
     */
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

    /**
     * Checks whether the string of characters matches the L1 pattern
     * @return:True if the sequence of characters matches the
     * L1 pattern, false otherwise
     */
    public boolean isPatternMatch() {
        return st.isEmpty();
    }

    /**
     * Resets the stacks of the application in preparation for further
     * input.
     */
    public void resetParser() {
        this.st = new StackLL<>();
    }
}
