/**
 * This class throws a specific exception when checking the first two values
 * of an input file
 * 
 * @author Kyle Truschel
 */

public class ExceededStartValuesException extends Exception {

    /**
     * This constructor calls the super keyword to inform the user when more than
     * two
     * values exist on the first line of a file
     * 
     * @param errorMessage takes an empty string, but will be formatted into a
     *                     useful message when this exception is thrown
     * @param error        takes in the Throwable type, inherited from Exception
     */
    public ExceededStartValuesException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}