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

        Scanner entireFileScanner = new Scanner( fileName );

        // Try to create and open a file
        try {
            if (entireFileScanner.hasNextInt()) {
                int startRows = entireFileScanner.nextInt();

                if (entireFileScanner.hasNextInt()) {
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
    
                                else {
                                    outputMessage = currentLineScanner.next();
                                    currentLineScanner.close();
                                    entireFileScanner.close();
                                    throw new NumberFormatException();
                                }
                            }
                            // Reset the count for double types at the beginning of each row search

                            if ( doubleCount != 1) {
                                doubleExists = false;
                                outputMessage = "Not exactly one double";
                                throw new NumberFormatException();
                            }

                            else {
                                doubleCount = 0;
                                currentLineScanner.close();
                            }
                        }
                        else {
                            entireFileScanner.close();
                            throw new NoSuchElementException();
                        }
                    }
                    // if ( doubleCount != 1 ) {
                        
                    //     entireFileScanner.close();
                    //     throw new NumberFormatException();
                    // }
                }
            }
            else {
                entireFileScanner.close();
                throw new NoSuchElementException();
            }

            entireFileScanner.close();
            return true;

        } catch (NoSuchElementException nsee) {
            System.out.println("No such element exists");
            entireFileScanner.close();
            return false;

        } catch (NumberFormatException nfe) {
            System.out.println("java.lang.NumberFormatException: For input string: " + "\"" + outputMessage + "\"");
            entireFileScanner.close();
            return false;

        } catch (Exception e) {
            System.out.println("No e Bad");
            entireFileScanner.close();
            return false;
        }
    }
}
