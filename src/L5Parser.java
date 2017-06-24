import java.util.Stack;

/**
 * Created by falko on 24-06-17.
 */
public class L5Parser implements LanguageParser {

    StackLL<Character> st;

    public L5Parser() {
        this.st = new StackLL<>();
    }

    public void handleLetter(Character letter) {
        if (this.st.isEmpty()) {
            this.st.push(letter);
        } else if (this.st.peek().equals('C')) {
            try {
                Character temp = this.st.pop();
                if (!this.st.isEmpty() && this.st.peek().equals(letter)) {
                    this.st.pop();
                    this.st.push(temp);
                }
            } catch (StackUnderflowException e) {
                System.err.println(e);
            }
        } else {
            this.st.push(letter);
        }
    }

    public boolean isPatternMatch() {
        Character top = 'B'; // placeholder
        if (this.st.isEmpty()) {
            return false;
        }
        try {
            return (this.st.pop().equals('C') && this.st.isEmpty());
        } catch (StackUnderflowException e) {
            System.err.println(e);
        }
        return false;
    }

    public void resetParser() {
        this.st = new StackLL<>();
    }
}
