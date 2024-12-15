package org.unibl.etf.exception;
/**
 * Exception that is thrown when a number is outside the valid range.
 * Specifically, this exception is triggered when the number is not within the range [0, 10].
 */
public class NumberNotInAreaException extends Exception{
    /**
     * Constructs a new {@code NumberNotInAreaException} with a default error message.
     * The default message is: "Number is not in range [0,10]."
     */
    public NumberNotInAreaException(){
        super("Number is not in range [0,10].");
    }
}