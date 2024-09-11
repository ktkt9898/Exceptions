public class ExceededStartValuesException extends Exception {
    public ExceededStartValuesException( String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}