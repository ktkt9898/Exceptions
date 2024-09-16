import java.io.FileNotFoundException;

/**
 * This class acts as the driver. It first prints out a usage message and will take
 * command line arguments, for files to be validated.
 * 
 * @author Kyle Truschel
 */

public class FormatChecker {

    /**
     * This method simply prints a helpful message for the user to run the program
     */
    private static void printUsage () {
        System.out.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
    }
    
    public static void main(String[] args) throws FileNotFoundException {

        // Efficient technique to take in as many files, as arguments, as the user desires
        if (args.length == 0) {
            printUsage();
        }
        for (String testFile : args) {
            System.out.println(testFile);
            Checker testChecker = new Checker(testFile);
        }
    }
}