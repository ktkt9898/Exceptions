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

    public boolean readFile( String stringFileName ) throws FileNotFoundException {
        String error = "";
        this.stringFileName = stringFileName;

        File fileName = new File( stringFileName );

        Scanner entireFileScanner = new Scanner( fileName );

        // Try to create and open a file
        try {
            if (entireFileScanner.hasNextInt()) {
                int startRows = entireFileScanner.nextInt();
                if (entireFileScanner.hasNextInt()) {
                    int startCols = entireFileScanner.nextInt();
                    // System.out.println(originalLineScanner.nextLine());

                    // Skip trick
                    entireFileScanner.nextLine();

                    for (int row = 0; row < startRows; row++) {
                        int doubleCount = 0;
                        int testIntCount = 0;

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
                                    error = currentLineScanner.next();
                                    throw new NumberFormatException();
                                }
                            }

                            currentLineScanner.close();
    
                            if ( doubleCount != 1 ) {
                                error = "Not exactly one double";
                                throw new NumberFormatException();
                            }
    
                            // if (originalLineScanner.hasNextDouble()) {
                            //     originalLineScanner.nextDouble();
                            // }
        
                            // else {
                            //     error = originalLineScanner.next();
                            //     throw new NumberFormatException();
                            // }
                        }
                        else {
                            throw new NoSuchElementException();
                        }
                    }
                }
            }
            else {
                throw new NoSuchElementException();
            }

            entireFileScanner.close();
            return true;

        } catch (NoSuchElementException nsee) {
            System.out.println("No nsee Bad");
            entireFileScanner.close();
            return false;

        } catch (NumberFormatException nfe) {
            System.out.println("java.lang.NumberFormatException: For input string: " + "\"" + error + "\"");
            entireFileScanner.close();
            return false;

        } catch (Exception e) {
            System.out.println("No e Bad");
            entireFileScanner.close();
            return false;
        }
    }
}
