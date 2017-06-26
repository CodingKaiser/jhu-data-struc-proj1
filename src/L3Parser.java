/**
 * A parser that parses the language L3:
 * (A^nB^2n)
 * Strings supported:
 * ''
 * 'AAABBBBBB'
 * 'ABB'
 * 'AAAABBBBBBBB'
 *
 * @author Falko Noe
 * @version 1.0
 */
public class L3Parser implements LanguageParser {

  private StackLL<Character> all;
  private StackLL<Character> bOnly;

  /**
   * The constructor for the application
   */
  L3Parser() {
    all = new StackLL<>();
    bOnly = new StackLL<>();
  }

  /**
   * Takes in a character and handles it according to L3
   * language constraints
   * @param letter: The character currently being parsed.
   */
  public void handleLetter(Character letter) {
    if (letter.equals('B')) {
      bOnly.push(letter);
    }
    all.push(letter);
  }

  /**
   * Checks whether the string of characters matches the L3 pattern
   * @return:True if the sequence of characters matches the
   * L3 pattern, false otherwise
   */
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

  /**
   * Resets the stacks of the application in preparation for further
   * input.
   */
  public void resetParser() {
    all = new StackLL<>();
    bOnly = new StackLL<>();
  }
}
