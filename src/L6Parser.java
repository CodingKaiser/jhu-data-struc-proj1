/**
 * A parser that parses the language L6:
 * A^nB^nA^n
 * Strings supported:
 * ''
 * 'ABA'
 * 'AABBAA'
 * 'AAABBBAAA'
 *
 * @author Falko Noe
 * @version 1.0
 */
public class L6Parser implements LanguageParser {

  private StackLL<Character> all;
  private StackLL<Character> aOnly;
  private StackLL<Character> bOnly;
  private boolean wasErr;

  /**
   * The constructor for the application
   */
  public L6Parser() {
    this.all = new StackLL<>();
    this.aOnly = new StackLL<>();
    this.bOnly = new StackLL<>();
    this.wasErr = false;
  }

  /**
   * Takes in a character and handles it according to L6
   * language constraints
   * @param letter: The character currently being parsed.
   */
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
        System.err.println("Algorithm ran into Stack underflow. " +
                "Disregard L6 parser's answers.");
        this.wasErr = true;
      }
      this.aOnly.push(letter);
    } else {
      this.all.push(letter);
      if (letter.equals('B')) {
        this.bOnly.push(letter);
      }
    }
  }

  /**
   * Checks whether the string of characters matches the L6 pattern
   * @return:True if the sequence of characters matches the
   * L6 pattern, false otherwise
   */
  public boolean isPatternMatch() {
    try {
      while (!this.all.isEmpty() && !this.aOnly.isEmpty()) {
        if (!this.all.pop().equals(this.aOnly.pop())) {
          return false;
        }
      }
    } catch (StackUnderflowException e) {
      System.err.println(e);
      System.err.println("Algorithm ran into Stack underflow. " +
              "Disregard L2 parser's answers.");
      this.wasErr = true;
    }
    return (this.all.isEmpty() && this.aOnly.isEmpty() && this.bOnly.isEmpty());
  }

  /**
   * Resets the stacks of the application in preparation for further
   * input.
   */
  public void resetParser() {
    this.all = new StackLL<>();
    this.aOnly = new StackLL<>();
    this.bOnly = new StackLL<>();
    this.wasErr = false;
  }

  /**
   * Gets the boolean value which reflects if the algorithm encountered
   * an error during runtime.
   * @return True if the algorithm encountered an error, false otherwise
   */
  public boolean isErrorOccurred() {
    return this.wasErr;
  }
}
