package org.unibl.etf.exception;
/**
 * Exception that is thrown when an unsupported operation is attempted.
 * This exception is designed to indicate that the requested operation
 * is not available or not implemented.
 */
public class NotSupportedOperationException extends Exception {
    /**
     * Constructs a new {@code NotSupportedOperationException} with a default error message.
     * The default message is: "This type of operation is not supported."
     */
    public NotSupportedOperationException(){
        super("This type of operation is not supported.");
    }
}
