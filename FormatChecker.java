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
    private static void printUsage ( ) {
        System.out.println("Usage: java FormatChecker <file_name>");
        System.out.println("\tYou may enter more than one file at once using the same syntax.");
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        // Display how to use this program to the terminal
        printUsage();

        // Easy way to take in string arguments
        for (String testFile : args) {
            System.out.println(testFile);
            Checker testChecker = new Checker(testFile);
        }
    }
}