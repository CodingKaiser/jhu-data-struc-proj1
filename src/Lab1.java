import java.io.*;

/**
 * Created by falko on 17-06-17.
 */
public class Lab1 {

    private L1Parser l1Parser;
    private L2Parser l2Parser;
    private L3Parser l3Parser;

    public Lab1() {
        this.l1Parser = new L1Parser();
        this.l2Parser = new L2Parser();
        this.l3Parser = new L3Parser();
    }

    /**
     * The main entry-point to the program. Runs and executes all logic of the program
     * @param args Holds the command-line arguments passed into the program,
     *             which should only be composed of the path to an input file
     *             and the path to an output file.
     */
    public static void main(String[] args) {

        BufferedReader input;
        BufferedWriter output;
        Lab1 lab;

        if (args.length != 2) {
            System.err.println("Usage:  java Project0 [input file pathname]" +
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
* and gets the parsers ready for the next line.
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

    private void parseLine(char[] line) {
        for (char letter : line) {
            this.l1Parser.handleLetter(letter);
            this.l2Parser.handleLetter(letter);
            this.l3Parser.handleLetter(letter);
        }
    }

    private void resetAllParsers() {
        this.l1Parser.resetParser();
        this.l2Parser.resetParser();
        this.l3Parser.resetParser();
    }

    private void checkTruthinessAndWriteOut(char[] line, BufferedWriter out) {
        try {
            System.out.println(this.l1Parser.isPatternMatch());
            out.write(line);
//            LanguageParser[] allParsers;
            if (this.l1Parser.isPatternMatch()) {
                out.write(' ');
                out.write("L1");
            }
            if (this.l2Parser.isPatternMatch()) {
                out.write(' ');
                out.write("L2");
            }
            if (this.l3Parser.isPatternMatch()) {
                out.write(' ');
                out.write("L3");
            }
            out.newLine();
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("Couldn't write the result to out.");
        }
    }
}
