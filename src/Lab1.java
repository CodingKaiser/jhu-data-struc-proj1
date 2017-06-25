import java.io.*;

/**
 * Lab 1: Parses input and checks whether or not lines of text match
 * the pattern specified by specific languages.
 * @author Falko Noe
 * @version 1.0
 */
public class Lab1 {

    private L1Parser l1Parser;
    private L2Parser l2Parser;
    private L3Parser l3Parser;
    private L4Parser l4Parser;
    private L5Parser l5Parser;
    private L6Parser l6Parser;

    /**
     * The constructor for the Lab1 object, which initializes the parsers
     */
    public Lab1() {
        this.l1Parser = new L1Parser();
        this.l2Parser = new L2Parser();
        this.l3Parser = new L3Parser();
        this.l4Parser = new L4Parser();
        this.l5Parser = new L5Parser();
        this.l6Parser = new L6Parser();
    }

    /**
     * The main entry-point to the program. Runs and executes all logic of the program:
     * Instantiates the parsers, sets up the I/O logic, and writes the output to a file
     * specified by the user's input.
     * @param args Holds the command-line arguments passed into the program,
     *             which should only be composed of the path to an input file
     *             and the path to an output file.
     */
    public static void main(String[] args) {

        BufferedReader input;
        BufferedWriter output;
        Lab1 lab;

        if (args.length != 2) {
            System.err.println("Usage:  java Lab1 [input file pathname]" +
                    " [output file pathname]");
            System.exit(1);
        }

        try {
            input = new BufferedReader(new FileReader(args[0]));
            output = new BufferedWriter(new FileWriter(args[1]));
        } catch (IOException e) {
            System.err.println("Make sure the input/output path is correct.");
            return;
        }

        lab = new Lab1();
        lab.parseLinesInInput(input, output);

        try {
            input.close();
            output.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Method which:
     * 1. iterates through the lines in the input file,
     * 2. calls a method which checks whether the line matched any of the languages
     *    and writes the output for that line,
     *    and gets the parsers ready for the next line.
     * @param in The BufferedReader which contains the information from the input file
     * @param out The BufferedWriter which will write the output
     */
    private void parseLinesInInput(BufferedReader in, BufferedWriter out) {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                char[] charactersInLine = line.toCharArray();
                parseLine(charactersInLine);
                checkTruthinessAndWriteOut(charactersInLine, out);
                resetAllParsers();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Iterates through character in the line, and hands them
     * off to each individual parser for specific handling
     * @param line: A character array corresponding to each line
     *            of the input. Each element in the array corresponds
     *            to a character in the line
     */
    private void parseLine(char[] line) {
        for (char letter : line) {
            if (((Character) letter).equals(' ')) {
                System.err.println("Line: " + (new String(line)) +
                                    " contains whitespace. Trimming...");
            } else {
                this.l1Parser.handleLetter(letter);
                this.l2Parser.handleLetter(letter);
                this.l3Parser.handleLetter(letter);
                this.l4Parser.handleLetter(letter);
                this.l5Parser.handleLetter(letter);
                this.l6Parser.handleLetter(letter);
            }
        }
    }

    /**
     * Resets all of the parsers to make them ready for
     * another line of input.
     */
    private void resetAllParsers() {
        this.l1Parser.resetParser();
        this.l2Parser.resetParser();
        this.l3Parser.resetParser();
        this.l4Parser.resetParser();
        this.l5Parser.resetParser();
        this.l6Parser.resetParser();
    }

    /**
     * Checks each parser for whether the input line matched and
     * writes the result to the BufferedWriter object in a
     * human-readable format.
     * @param line: The line that was just parsed.
     * @param out: The BufferedWriter object which will write
     *           the formatted results to stdout.
     */
    private void checkTruthinessAndWriteOut(char[] line, BufferedWriter out) {
        try {
            out.write("'");
            out.write(line);
            out.write("'");
            out.newLine();
            out.write("Matches:");
            boolean atLeastOneMatch = false;
//            LanguageParser[] allParsers;
            if (this.l1Parser.isPatternMatch()) {
                atLeastOneMatch = true;
                out.write(" L1");
            }
            if (this.l2Parser.isPatternMatch()) {
                atLeastOneMatch = true;
                out.write(" L2");
            }
            if (this.l3Parser.isPatternMatch()) {
                atLeastOneMatch = true;
                out.write(" L3");
            }
            if (this.l4Parser.isPatternMatch()) {
                atLeastOneMatch = true;
                out.write(" L4");
            }
            if (this.l5Parser.isPatternMatch()) {
                atLeastOneMatch = true;
                out.write(" L5");
            }
            if (this.l6Parser.isPatternMatch()) {
                atLeastOneMatch = true;
                out.write(" L6");
            }
            if (!atLeastOneMatch) {
                out.write(" None");
            }
            out.newLine();
            out.newLine();
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("Couldn't write the result to out.");
        }
    }
}
