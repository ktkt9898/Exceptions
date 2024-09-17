import java.io.FileNotFoundException;

/**
 * This class acts as the driver. It first prints out a usage message and will
 * take
 * command line arguments, for files to be validated.
 * 
 * @author Kyle Truschel
 */

public class FormatChecker {

    /**
     * This method will print a helpful usage message at the start
     * and in the event that the user does NOT enter any values
     * Void and takes in no parameters
     */
    private static void printUsage() {
        System.out.println("Usage: $ java FormatChecker file1 [file2 ... fileN]");
    }

    /**
     * The main method which takes in command line arguments to be passed into
     * the for-each loop which will then create a Checker object to test if a file
     * is valid.
     * 
     * @param args String array of command line arguments
     * @throws FileNotFoundException this exception is thrown is a file cannot be
     *                               read
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            printUsage();
        }
        // Efficient technique to take in as many files, as arguments, as the user
        // desires
        for (String testFile : args) {
            System.out.println(testFile);
            Checker testChecker = new Checker(testFile);
        }
    }
}