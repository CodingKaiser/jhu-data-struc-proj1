/**
 * A parser that parses the language L4:
 * (A^nB^m)^p
 * Strings supported:
 * ''
 * 'AABB'
 * 'BBB'
 * 'AA'
 * 'ABABAB'
 * 'AAAABBBBBBBB'
 * 'ABBBBBBBB'
 *
 * @author Falko Noe
 * @version 1.0
 */
public class L4Parser implements LanguageParser {

  private StackLL<Character> inOrder;
  private StackLL<Character> repeatedPattern;
  private boolean patternIsLocked;
  private boolean patternMatch;
  private boolean wasErr;

  /**
   * The constructor for the application
   */
  public L4Parser() {
    this.inOrder = new StackLL<>();
    this.repeatedPattern = new StackLL<>();
    this.patternMatch = true;
    this.patternIsLocked = false;
    this.wasErr = false;
  }

  /**
   * Takes in a character and handles it according to L4
   * language constraints.
   * @param letter: The character currently being parsed.
   */
  public void handleLetter(Character letter) {
    try {
      if (letter.equals('A') || letter.equals('B')) {
        if (repeatedPattern.isEmpty() && inOrder.isEmpty()) {
          // We're at the beginning of the string
          repeatedPattern.push(letter);
        } else if (inOrder.isEmpty() &&
                   repeatedPattern.peek().equals('B') &&
                   letter.equals('A')) {
          // Pick the first string of A^mB^n as the template
          patternIsLocked = true;
          while (!repeatedPattern.isEmpty()) {
            /* inOrder stack will allow us to test subsequent strings
             * for the locked pattern in the order in which they are
             * parsed in. */
            inOrder.push(repeatedPattern.pop());
          }
          if (letter.equals(inOrder.peek())) {
            /* */
            repeatedPattern.push(inOrder.pop());
          } else {
            inOrder.push(letter);
          }
        } else if (inOrder.isEmpty() && !patternIsLocked) {
          // Build up initial pattern
          repeatedPattern.push(letter);
        } else if (!inOrder.isEmpty()) {
          /* While parsing strings past the initial pattern
           * pop letters back into repeatedPattern if the
           * current letter being parsed matches */
          if (letter.equals(inOrder.peek())) {
            repeatedPattern.push(inOrder.pop());
          } else {
            this.patternMatch = false;
          }
        } else {
          // Push invalid characters onto the stack
          this.patternMatch = false;
        }
      } else {
        // Push invalid characters onto the stack
        this.patternMatch = false;
      }
    } catch (StackUnderflowException e) {
      System.err.println(e);
      System.err.println("Algorithm ran into Stack underflow. " +
              "Disregard L4 parser's answers.");
      this.wasErr = true;
    }
  }

  /**
   * Checks whether the string of characters matches the L4 pattern
   * @return:True if the sequence of characters matches the
   * L4 pattern, false otherwise
   */
  public boolean isPatternMatch() {
    return this.patternMatch && inOrder.isEmpty();
  }

  /**
   * Resets the stacks of the application in preparation for further
   * input.
   */
  public void resetParser() {
    this.inOrder = new StackLL<>();
    this.repeatedPattern = new StackLL<>();
    this.patternMatch = true;
    this.patternIsLocked = false;
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
