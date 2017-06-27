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
  private boolean startedParsing;
  private boolean patternMatch;

  /**
   * The constructor for the application
   */
  L3Parser() {
    all = new StackLL<>();
    this.startedParsing = false;
    this.patternMatch = true;
  }

  /**
   * Takes in a character and handles it according to L3
   * language constraints
   * @param letter: The character currently being parsed.
   */
  public void handleLetter(Character letter) {
    if (this.all.isEmpty() && this.startedParsing) {
      // Needed for edge cases like 'AABBBBABB' where Stack is empty at end
      this.patternMatch = false;
      this.all.push(letter);
    } else if (this.all.isEmpty()) {
      this.startedParsing = true; // see first case
      this.all.push(letter);
    } else if (letter.equals('A')) {
      this.all.push(letter);
    } else if (letter.equals('B')) {
      try {
        if (this.all.peek().equals('A')) {
          // only push an additional B if only one B is on Stack
          this.all.push(letter);
        } else if (this.all.peek().equals('B')) {
          // Pop the stack, to check whether 2nd-from-top matches pattern
          Character top = this.all.pop();
          if (!this.all.isEmpty() && this.all.peek().equals('A')) {
            this.all.pop();
          } else {
            this.all.push(letter);
          }
        }
      } catch (StackUnderflowException e) {
        System.err.println(e);
        this.patternMatch = false;
      }
    }
  }

  /**
   * Checks whether the string of characters matches the L3 pattern
   * @return:True if the sequence of characters matches the
   * L3 pattern, false otherwise
   */
  public boolean isPatternMatch() {
    return (this.all.isEmpty() && this.patternMatch);
  }

  /**
   * Resets the stacks of the application in preparation for further
   * input.
   */
  public void resetParser() {
    all = new StackLL<>();
    this.patternMatch = true;
    this.startedParsing = false;
  }
}
