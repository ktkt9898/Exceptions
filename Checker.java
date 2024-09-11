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

    public Checker( String fileName ) throws FileNotFoundException {
        readFile( fileName );
    }

    /**
     * This method essentially is a catch-all for any exception that could be thrown
     * when testing the validity of improper files, that not starting with exactly two integer values on
     * the first line, and having exactly one double value in the following lines.
     * If any of these conditions are not met or illegal, the method will throw an appropriate exception.
     * 
     * @param stringFileName
     * @return
     * @throws FileNotFoundException
     */
    public boolean readFile( String stringFileName ) throws FileNotFoundException {
        // Primer string, will eventually be concatentated with useful information
        String outputMessage = "";

        this.stringFileName = stringFileName;

        File fileName = new File( stringFileName );
        try {
            if (!fileName.exists()) {
                throw new FileNotFoundException(fileName + " does not exist.");
            } 
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return false;
        }

        Scanner entireFileScanner = new Scanner( fileName );

        // Try to create and open a file
        try {
            if (entireFileScanner.hasNextInt()) {
                // Retrieve the fist int value in the file, which is the start row ammount
                int startRows = entireFileScanner.nextInt();

                if (entireFileScanner.hasNextInt()) {
                    // Retrieve the first int value in the file, which
                    int startCols = entireFileScanner.nextInt();

                    // doubleCount will be compared to at the end of the loop
                    int doubleCount = 0;
                    int testIntCount = 0;
                    boolean doubleExists = true;

                    // Skip trick
                    entireFileScanner.nextLine();

                    for (int row = 0; row < startRows; row++) {

                        if ( entireFileScanner.hasNextLine()) {
                            String currentLine = entireFileScanner.nextLine();
                            Scanner currentLineScanner = new Scanner(currentLine);

                            for (int col = 0; col < startCols; col++) {
                                if (currentLineScanner.hasNextInt()) {
                                    // Debugger use
                                    int intValue = currentLineScanner.nextInt();
                                    testIntCount++;
                                }
                                
                                else if (currentLineScanner.hasNextDouble()){
                                    // Debugger use
                                    double doubleValue = currentLineScanner.nextDouble();
                                    doubleCount++;
                                }
    
                                // This checks if no
                                else {
                                    outputMessage = currentLineScanner.next();
                                    currentLineScanner.close();
                                    entireFileScanner.close();
                                    throw new NumberFormatException();
                                }
                            }
                        }

                        // This checks if a row and column value were valid, but no other lines exist
                        else {
                            entireFileScanner.close();
                            throw new NoSuchElementException();
                        }
                    }
                }

                // This checks if the second value from the row is anything other than int
                else {
                    entireFileScanner.close();
                    throw new NoSuchElementException();
                }
            }
            
            // This checks if the first value from the row is anything other than int
            else {
                entireFileScanner.close();
                throw new NoSuchElementException();
            }

            // This checks the exception where more than the retrieved row and columns exist
            if (entireFileScanner.hasNextDouble()) {
                throw new IllegalStateException("More rows and columns exist than retrieved.");
            }



            entireFileScanner.close();
            System.out.println(toString());
            return true;

        } catch (NoSuchElementException nsee) {
            System.out.println(nsee);
            System.out.println("INVALID");
            entireFileScanner.close();
            return false;

        } catch (IllegalStateException ise) {
            System.out.println(ise);
            System.out.println("INVALID");
            entireFileScanner.close();
            return false;

        } catch (NumberFormatException nfe) {
            System.out.println(nfe);
            System.out.println("INVALID");
            entireFileScanner.close();
            return false;
        }
    }

    @Override
    public String toString() {
        String append = "";
        append += "VALID";
        return append;
    }
}
