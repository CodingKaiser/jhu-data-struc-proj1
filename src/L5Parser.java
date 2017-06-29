/**
 * A parser that parses the language L5:
 * xCy
 * Strings supported:
 * 'ACA'
 * 'BCB'
 * 'C'
 * 'ABACABA'
 * 'BABCBAB'
 *
 * @author Falko Noe
 * @version 1.0
 */
public class L5Parser implements LanguageParser {

  StackLL<Character> st;
  private boolean wasErr;

  /**
   * The constructor for the application
   */
  public L5Parser() {
    this.st = new StackLL<>();
    this.wasErr = false;
  }

  /**
   * Takes in a character and handles it according to L5
   * language constraints
   * @param letter: The character currently being parsed.
   */
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
        System.err.println("Algorithm ran into Stack underflow. " +
                "Disregard L5 parser's answers.");
        this.wasErr = true;
      }
    } else {
      this.st.push(letter);
    }
  }

  /**
   * Checks whether the string of characters matches the L5 pattern
   * @return:True if the sequence of characters matches the
   * L5 pattern, false otherwise
   */
  public boolean isPatternMatch() {
    Character top = 'B'; // placeholder
    if (this.st.isEmpty()) {
      return false;
    }
    try {
      return (this.st.pop().equals('C') && this.st.isEmpty());
    } catch (StackUnderflowException e) {
      System.err.println(e);
      System.err.println("Algorithm ran into Stack underflow. " +
              "Disregard L5 parser's answers.");
      this.wasErr = true;
    }
    return false;
  }

  /**
   * Resets the stacks of the application in preparation for further
   * input.
   */
  public void resetParser() {
    this.st = new StackLL<>();
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
