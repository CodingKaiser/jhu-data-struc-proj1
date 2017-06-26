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
  private StackLL<Character> bOnly;

  /**
   * The constructor for the application
   */
  L2Parser() {
    this.all = new StackLL<>();
    this.bOnly = new StackLL<>();
  }

  /**
   * Takes in a character and handles it according to L2
   * language constraints
   * @param letter: The character currently being parsed.
   */
  public void handleLetter(Character letter) {
    if (letter.equals('B')) {
      this.bOnly.push(letter);
    }
    this.all.push(letter);
  }

  /**
   * Checks whether the string of characters matches the L2 pattern
   * @return:True if the sequence of characters matches the
   * L2 pattern, false otherwise
   */
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

  /**
   * Resets the stacks of the application in preparation for further
   * input.
   */
  public void resetParser() {
    all = new StackLL<>();
    bOnly = new StackLL<>();
  }
}
