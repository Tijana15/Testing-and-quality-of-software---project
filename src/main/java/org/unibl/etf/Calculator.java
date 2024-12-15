package org.unibl.etf;

import org.unibl.etf.exception.DivisionByZeroException;
import org.unibl.etf.exception.NotSupportedOperationException;

/**
 * Represents a simple calculator that can perform basic arithmetic operations such as
 * addition, subtraction, multiplication, and division on a current value.
 * The calculator has an attribute {@code currentValue},
 * which is updated with each operation. The initial value of the calculator is set to 0.0.
 */

public class Calculator {
    private Double currentValue;

    /**
     * Creates a new {@code Calculator} instance with an initial value of 0.0.
     */
    public Calculator() {
        this.currentValue = 0.0;
    }

    /**
     * Performs a calculation using the specified value and operator on the current value.
     * Supported operators are:
     * <ul>
     * <li>'+' for addition</li>
     * <li>'-' for subtraction</li>
     * <li>'*' for multiplication</li>
     * <li>'/' for division</li>
     * </ul>
     *
     * @param value    the value to be used in the calculation.
     * @param operator the operator specifying the type of calculation to perform.
     * @throws DivisionByZeroException        if division by zero is attempted.
     * @throws NotSupportedOperationException if an unsupported operator is provided.
     */
    public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
        switch (operator) {
            case '+':
                currentValue += value;
                break;
            case '-':
                currentValue -= value;
                break;
            case '*':
                currentValue *= value;
                break;
            case '/':
                if (value.equals(0.0)) {
                    throw new DivisionByZeroException();
                } else {
                    currentValue /= value;
                }
                break;
            default:
                throw new NotSupportedOperationException();
        }
    }

    /**
     * Returns the current value of the calculator.
     *
     * @return the current value.
     */
    public Double getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets the current value of the calculator to the specified value.
     *
     * @param currentValue the value to set as the current value.
     */
    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }
}
