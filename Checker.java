import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.NumberFormatException;

/**
 * This class performs the valid checking of files
 * 
 * @author Kyle Truschel
 */
public class Checker {
    private String stringFileName;

    /**
     * This constructor takes in a String fileName as a parameter and passes
     * it to the readFile method, which handles the valid file checking
     * 
     * @param fileName takes a String, that the user would have entered from the
     *                 command line arguments
     * @throws FileNotFoundException this exception is thrown is a file cannot be
     *                               read
     */
    public Checker(String fileName) throws FileNotFoundException {
        readFile(fileName);
    }

    /**
     * This method essentially serves as the main exception check procedure for any
     * exception that could be thrown
     * when testing the validity of improper files, that not starting with exactly
     * two integer values on
     * the first line, and having exactly one double value in the following lines.
     * If any of these conditions are not met or illegal, the method will throw an
     * appropriate exception.
     * 
     * @param stringFileName takes in a String, passed from the constructor
     * @return Returns true if a file was valid, otherwise returns false
     * @throws FileNotFoundException first Exception check, to see if a file exists
     *                               in the same directory as the program
     */
    public boolean readFile(String stringFileName) throws FileNotFoundException {
        // Primer string, will eventually be concatentated with useful information
        String outputMessage = "";

        // Assign input parameter to soon try to be opened
        this.stringFileName = stringFileName;

        // Create a new file object with the String parameter from the method
        File fileName = new File(stringFileName);
        try {
            // The first exception check, if a file cannot be found, either mistyped or not
            // in the same directory
            // throw a FileNotFoundException
            if (!fileName.exists()) {
                throw new FileNotFoundException("Error. File: " + "\"" + fileName + "\"" + " does not exist." + "\n" + "INVALID");
            }
            // Required to complete the try-catch syntax
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return false;
        }

        // Primary scanner, to open the file
        Scanner entireFileScanner = new Scanner(fileName);

        // After file was successfully opened, perform various exception checks to truly
        // see
        // if the file is the valid format
        try {
            String startingLine = entireFileScanner.nextLine();

            // Scanner meant to ONLY scan the first line, expecting only two integers
            Scanner startingLineScanner = new Scanner(startingLine);

            if (startingLineScanner.hasNextInt()) {
                // Retrieve the fist int value in the file, which is the start row ammount
                int startRows = startingLineScanner.nextInt();

                if (startingLineScanner.hasNextInt()) {
                    // Retrieve the first int value in the file, which
                    int startCols = startingLineScanner.nextInt();

                    // This checks if anything else remains after the first two integers.
                    if (startingLineScanner.hasNext()) {
                        startingLineScanner.close();
                        throw new ExceededStartValuesException(
                                "Error: This file had more than two integers for the first line.", null);
                    }

                    // Valid file will be scanned from the first int value, which serves as the row
                    for (int row = 0; row < startRows; row++) {

                        // Ensure the file has contents
                        if (entireFileScanner.hasNextLine()) {
                            String currentLine = entireFileScanner.nextLine();
                            Scanner innerLinesFileScanner = new Scanner(currentLine);

                            // Valid file will be scanned from the first int value, which serves as the
                            // column
                            for (int col = 0; col < startCols; col++) {
                                if (innerLinesFileScanner.hasNextInt()) {
                                    // Debugger use, useful to keep track of the contents within the file
                                    int intValue = innerLinesFileScanner.nextInt();
                                }

                                else if (innerLinesFileScanner.hasNextDouble()) {
                                    // Debugger use, useful to keep track of the contents within the file
                                    double doubleValue = innerLinesFileScanner.nextDouble();
                                }

                                // This checks if neither an integer or a double was found
                                else {
                                    // This checks if values in the file jas reversed row and column values
                                    // Such as 4 rows but only 3 lines, and 3 columns, but 4 values in each line
                                    if (!innerLinesFileScanner.hasNext()) {
                                        innerLinesFileScanner.close();
                                        throw new NoSuchElementException(
                                                "Error: Exceeded valid ammount of columns. Try reversing the row and column to fix.");
                                    }
                                    // This checks if a string or character type is found, instead of an int or
                                    // double
                                    else {
                                        outputMessage = innerLinesFileScanner.next();
                                        innerLinesFileScanner.close();
                                        entireFileScanner.close();
                                        throw new NumberFormatException(
                                                "Error: " + outputMessage + " is not an integer or a double.");
                                    }
                                }
                            }

                            // This checks if more columns are exceeded than retrieved from the file
                            if (innerLinesFileScanner.hasNext()) {
                                innerLinesFileScanner.close();
                                throw new IllegalStateException(
                                        "Error: More columns were exceeded than retrieved from file.");
                            }

                            innerLinesFileScanner.close();
                        }

                        // This checks if a row and column value were valid, but no other lines exist
                        else {
                            startingLineScanner.close();
                            entireFileScanner.close();
                            throw new NoSuchElementException(
                                    "Error: No values exist after the first line. Recheck row and column values on the first line.");
                        }
                    }
                }

                // This checks if the second value from the row is anything other than int
                else {
                    startingLineScanner.close();
                    entireFileScanner.close();
                    throw new NumberFormatException("Error: Second value in the first line is not an integer.");
                }
            }

            // This checks if the first value from the row is anything other than int
            else {
                startingLineScanner.close();
                entireFileScanner.close();
                throw new NumberFormatException("Error: First value in the first line is not an integer.");
            }

            // This checks if more rows were exceeded than retrieved from the file
            if (entireFileScanner.hasNext()) {
                startingLineScanner.close();
                entireFileScanner.close();
                throw new IllegalStateException("Error: More rows were exceeded than retrieved from the file.");
            }

            // If all exception checks pass, the file is considered valid.
            startingLineScanner.close();
            entireFileScanner.close();
            System.out.println("VALID" + "\n");
            return true;
        }

        catch (NoSuchElementException nsee) {
            System.out.println(nsee);
            System.out.println("INVALID" + "\n");
            entireFileScanner.close();
            return false;
        }

        catch (IllegalStateException ise) {
            System.out.println(ise);
            System.out.println("INVALID" + "\n");
            entireFileScanner.close();
            return false;
        }

        catch (ExceededStartValuesException esve) {
            System.out.println(esve);
            System.out.println("INVALID" + "\n");
            entireFileScanner.close();
            return false;
        }

        catch (NumberFormatException nfe) {
            System.out.println(nfe);
            System.out.println("INVALID" + "\n");
            entireFileScanner.close();
            return false;
        }
    }
}
