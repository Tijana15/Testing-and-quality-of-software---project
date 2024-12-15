package org.unibl.etf.exception;
/**
 * Exception that is thrown when a division by zero is attempted.
 * This exception is intended to provide a meaningful error message
 * and prevent undefined behavior in calculations.
 */
public class DivisionByZeroException extends Exception{
    /**
     * Constructs a new {@code DivisionByZeroException} with a default error message.
     * The default message is: "Division by zero is not defined."
     */
    public DivisionByZeroException(){
        super("Division by zero is not defined.");
    }
}
