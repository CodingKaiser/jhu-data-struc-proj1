/**
 * A parser that parses the language L2:
 * A^nB^n
 * Strings supported:
 * ''
 * 'AAABBB'
 * 'AB'
 * 'AAAAAAABBBBBBB'
 *
 * @author Falko Noe
 * @version 1.0
 */
public class L2Parser implements LanguageParser {
  private StackLL<Character> all;
  private boolean startedParsing;
  private boolean patternMatch;
  private boolean wasErr;

  /**
   * The constructor for the application
   */
  L2Parser() {
    this.all = new StackLL<>();
    this.startedParsing = false;
    this.patternMatch = true;
    this.wasErr = false;
  }

  /**
   * Takes in a character and handles it according to L2
   * language constraints
   * @param letter: The character currently being parsed.
   */
  public void handleLetter(Character letter) {
    if (this.all.isEmpty() && this.startedParsing) {
      // Needed for edge cases like 'AABBAB' where Stack is empty at end
      this.patternMatch = false;
      this.all.push(letter);
    } else if (this.all.isEmpty()) {
      this.startedParsing = true; // See first case
      this.all.push(letter);
    } else if (letter.equals('A')) {
      this.all.push(letter);
    } else if (letter.equals('B') && this.all.peek().equals('A')) {
      try {
        this.all.pop();
      } catch (StackUnderflowException e) {
        System.err.println(e);
        System.err.println("Algorithm ran into Stack underflow. " +
                "Disregard L2 parser's answers.");
        this.wasErr = true;
      }
    } else {
      this.all.push(letter);
    }
  }

  /**
   * Checks whether the string of characters matches the L2 pattern
   * @return:True if the sequence of characters matches the
   * L2 pattern, false otherwise
   */
  public boolean isPatternMatch() {
    return (patternMatch && this.all.isEmpty());
  }

  /**
   * Resets the stacks of the application in preparation for further
   * input.
   */
  public void resetParser() {
    all = new StackLL<>();
    this.patternMatch = true;
    this.startedParsing = false;
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
