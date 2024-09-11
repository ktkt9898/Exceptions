import java.io.File;
import java.io.FileNotFoundException;

/**
 * This acts as the driver
 * 
 * @author Kyle Truschel
 */

public class FormatChecker {

    public static void printUsage ( ) {
        System.out.println("Usage: java FormatChecker <file_name>");
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Checker test = new Checker("invalid2.dat");
        // for (int i = 0; i < args.length; i++) {
        //     String testingFile = args[i];
            
        // }
    }
}