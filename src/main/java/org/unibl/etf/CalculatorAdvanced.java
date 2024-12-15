package org.unibl.etf;

import org.unibl.etf.exception.NotSupportedOperationException;
import org.unibl.etf.exception.NumberNotInAreaException;

/**
 * Represents a calculator that extends the basic functionality of the {@code Calculator}.
 * This class includes additional operations such as calculating the factorial, powering a number,
 * and checking if a number has certain characteristics like being an Armstrong number or a perfect number.
 *
 * <p>
 * The class provides methods to perform advanced calculations and even handles exceptions.
 * </p>
 */
public class CalculatorAdvanced extends Calculator {
    /**
     * Performs advanced calculations based on the provided action.
     * <ul>
     * <li>'!' calculates the factorial of the current value.</li>
     * <li>Any digit (0-9) raises the current value to the power of that digit, if the current value is between 0 and 10.</li>
     * </ul>
     *
     * @param action the action to perform: '!' for factorial or a digit (0-9) for powering. (0!=1)
     * @throws NotSupportedOperationException if the provided action is not supported.
     * @throws NumberNotInAreaException       if the number is out of range for powering (less than 0 or greater than 10).
     */
    public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
        int e = (int) (double) getCurrentValue();
        if (action == '!') {
            if (e >= 0 && e <= 10) {
                setCurrentValue((double) calculateFactorial(e));
            } else {
                throw new NumberNotInAreaException();
            }
        } else if (Character.isDigit(action)) {
            setCurrentValue((double) power(e, Character.getNumericValue(action)));
        } else {
            throw new NotSupportedOperationException();
        }
    }

    /**
     * Checks if the current value has one of specific mathematical characteristic.
     * <ul>
     * <li>'A' checks if the number is an Armstrong number.</li>
     * <li>'P' checks if the number is a perfect number.</li>
     * </ul>
     *
     * @param value the type of characteristic to check: 'A' for Armstrong or 'P' for perfect number. Only those two are supported.
     * @return {@code true} if the number has the specified characteristic, {@code false} otherwise.
     * @throws NotSupportedOperationException if the characteristic type is not supported.
     * @throws NumberNotInAreaException       if the number is less than 1 (Armstrong and perfect number checks are only applicable for numbers greater than or equal to 1).
     */
    public Boolean hasCharacteristic(char value) throws NumberNotInAreaException, NotSupportedOperationException {
        int e = (int) (double) getCurrentValue();
        if (value == 'A') {
            if (e < 1) {
                throw new NumberNotInAreaException();
            } else {
                return isArmstrongNumber(e);
            }
        } else if (value == 'P') {
            if (e < 1) {
                throw new NumberNotInAreaException();
            } else {
                return isPerfectNumber(e);
            }
        } else {
            throw new NotSupportedOperationException();
        }
    }

    /**
     * Calculates the factorial of a number.
     *
     * @param n - the number to calculate the factorial for.
     * @return the factorial of the number.
     * @throws IllegalArgumentException if the number is negative.
     */
    private int calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * calculateFactorial(n - 1);
    }

    /**
     * Raises the base number to the power of the exponent and returns calculated value.
     *
     * @param base     the base number.
     * @param exponent the exponent to raise the base number to.
     * @return the result of raising the base to the power of the exponent.
     */
    private int power(int base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * Checks if a number is an Armstrong number.
     * An Armstrong number is a number that is equal to the sum of its own digits raised to the power of the number of digits.
     * For example 153: 1^3+5^3+3^3=153
     *
     * @param number the number to check.
     * @return {@code true} if the number is an Armstrong number, {@code false} otherwise.
     */
    private boolean isArmstrongNumber(int number) {
        int originalNumber = number;
        int sum = 0;
        int numberOfDigits = String.valueOf(number).length();

        while (number > 0) {
            int digit = number % 10;
            int digitPower = 1;

            for (int i = 0; i < numberOfDigits; i++) {
                digitPower *= digit;
            }
            sum += digitPower;
            number /= 10;
        }
        return sum == originalNumber;
    }

    /**
     * Checks if a number is a perfect number.
     * A perfect number is a number that is equal to the sum of its proper divisors (excluding the number itself).
     * For example 6: 3+2+1=6, or 28: 1+2+4+7+14=28.
     *
     * @param number the number to check.
     * @return {@code true} if the number is a perfect number, {@code false} otherwise.
     */
    private boolean isPerfectNumber(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }
}
