/**
 * This class is responsible for throwing custom exceptions at runtime.
 * @author Hamzeh Kaddoura
 */
public class BTCException extends RuntimeException {

    /**
     * Returns a specified error message.
     * Message is being pass to the RuntimeException constructor.
     * @param message String containing the error message.
     */
    public BTCException(String message) {
        super(message);
    }

    /**
     * Default constructor.
     * Default message is passed to the base constructor and later passed on to the RuntimeException.
     * Prints out stack trace.
     */
    public BTCException() {
        this("Something went wrong");
        super.printStackTrace();
    }
}
