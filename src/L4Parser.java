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

  /**
   * The constructor for the application
   */
  public L4Parser() {
    this.inOrder = new StackLL<>();
    this.repeatedPattern = new StackLL<>();
    this.patternIsLocked = false;
  }

  /**
   * Takes in a character and handles it according to L4
   * language constraints
   * @param letter: The character currently being parsed.
   */
  public void handleLetter(Character letter) {
    try {
      if (letter.equals('A') || letter.equals('B')) {
        if (repeatedPattern.isEmpty() && inOrder.isEmpty()) {
          repeatedPattern.push(letter);
        } else if (inOrder.isEmpty() && repeatedPattern.peek().equals('B') && letter.equals('A')) {
          patternIsLocked = true;
          while (!repeatedPattern.isEmpty()) {
            inOrder.push(repeatedPattern.pop());
          }
          if (letter.equals(inOrder.peek())) {
            repeatedPattern.push(inOrder.pop());
          } else {
            inOrder.push(letter);
          }
        } else if (inOrder.isEmpty() && !patternIsLocked) {
          repeatedPattern.push(letter);
        } else if (!inOrder.isEmpty()) {
          if (letter.equals(inOrder.peek())) {
            repeatedPattern.push(inOrder.pop());
          } else {
            inOrder.push('C');
          }
        } else {
          inOrder.push('C');
        }
      } else {
        inOrder.push('C');
      }
    } catch (StackUnderflowException e) {
      System.err.println(e);
      System.err.println("Shouldn't be trusted.");
    }
  }

  /**
   * Checks whether the string of characters matches the L4 pattern
   * @return:True if the sequence of characters matches the
   * L4 pattern, false otherwise
   */
  public boolean isPatternMatch() {
    return inOrder.isEmpty();
  }

  /**
   * Resets the stacks of the application in preparation for further
   * input.
   */
  public void resetParser() {
    this.inOrder = new StackLL<>();
    this.repeatedPattern = new StackLL<>();
    this.patternIsLocked = false;
  }
}
