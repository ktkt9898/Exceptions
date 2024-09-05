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

    public Checker( String fileName) throws FileNotFoundException {
        readFile( fileName );
    }

    public boolean readFile( String stringFileName ) throws FileNotFoundException {
        String error = "";
        this.stringFileName = stringFileName;

        File fileName = new File(stringFileName);

        Scanner fileScanner = new Scanner(fileName);

        String line = fileScanner.nextLine();

        // Try to create and open a file
        try {
            Scanner lineScanner = new Scanner(line);

            if (lineScanner.hasNextInt()) {
                int startRows = lineScanner.nextInt();
                if (lineScanner.hasNextInt()) {
                    int startCols = lineScanner.nextInt();
                    System.out.println(lineScanner.nextLine());

                    for (int row = 1; row < startRows; row++) {
                        String skip = lineScanner.nextLine();
                        String[] skipArray = skip.split(" ");

                        for (int col = 1; col < startCols; col++) {
                            if (lineScanner.hasNextDouble( )){
                                lineScanner.nextDouble( );
                            }
    
                            else {
                                error = lineScanner.next();
                                throw new NumberFormatException();
                            }
                        }
                        if (lineScanner.hasNextDouble( )) {
                            lineScanner.nextDouble( );
                        }
    
                        else {
                            error = lineScanner.next();
                            throw new NumberFormatException();
                        }
                    }
                }
            }
            else {
                return false;
            }

            lineScanner.close();
            fileScanner.close();
            return true;

        } catch (NoSuchElementException nsee) {
            System.out.println("No nsee Bad");
            fileScanner.close();
            return false;

        } catch (NumberFormatException nfe) {
            System.out.println("java.lang.NumberFormatException: For input string: " + "\"" + error + "\"");
            fileScanner.close();
            return false;

        } catch (Exception e) {
            System.out.println("No e Bad");
            fileScanner.close();
            return false;
        }
    }
}
