/**
 * Created by falko on 24-06-17.
 */
public class L6Parser implements LanguageParser {

    StackLL<Character> all;
    StackLL<Character> aOnly;
    StackLL<Character> bOnly;

    public L6Parser() {
        this.all = new StackLL<>();
        this.aOnly = new StackLL<>();
        this.bOnly = new StackLL<>();
    }

    public void handleLetter(Character letter) {
        if (this.all.isEmpty() && this.bOnly.isEmpty()) {
            this.all.push(letter);
        } else if (!this.all.isEmpty() && !this.bOnly.isEmpty() && letter.equals('A')) {
            try {
                if (this.all.peek().equals(this.bOnly.peek())) {
                    this.all.pop();
                    this.bOnly.pop();
                }
            } catch (StackUnderflowException e) {
                System.err.println(e);

            }
            this.aOnly.push(letter);
        } else {
            this.all.push(letter);
            if (letter.equals('B')) {
                this.bOnly.push(letter);
            }
        }
    }

    public boolean isPatternMatch() {
        try {
            while (!this.all.isEmpty() && !this.aOnly.isEmpty()) {
                if (!this.all.pop().equals(this.aOnly.pop())) {
                    return false;
                }
            }
        } catch (StackUnderflowException e) {
            System.err.println(e);
            return false;
        }
        return (this.all.isEmpty() && this.aOnly.isEmpty() && this.bOnly.isEmpty());
    }

    public void resetParser() {
        this.all = new StackLL<>();
        this.aOnly = new StackLL<>();
        this.bOnly = new StackLL<>();
    }
}
