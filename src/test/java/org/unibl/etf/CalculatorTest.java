package org.unibl.etf;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.unibl.etf.exception.DivisionByZeroException;
import org.unibl.etf.exception.NotSupportedOperationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.Matchers.is;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @MethodSource("validParamsCalculate")
    void testCalculateMethod(Double value, char operation, Double result) throws DivisionByZeroException, NotSupportedOperationException {
        calculator.setCurrentValue(2.0);
        calculator.calculate(value, operation);
        assertThat(calculator.getCurrentValue(), is(result));
    }

    @ParameterizedTest
    @MethodSource("exceptionParamsCalculate")
    void testCalculateWithExceptions(Double value, char operation, Class<? extends Exception> expectedException) {
        assertThrows(expectedException, () -> calculator.calculate(value, operation));
    }

    @AfterEach
    void tearDown() {
        calculator.setCurrentValue(0.0);
        System.out.println("Calculator reset to initial state.");
    }

    @Test
    void getCurrentValue() {
        calculator.setCurrentValue(42.0);
        assertThat(calculator.getCurrentValue(), is(42.0));
    }

    @Test
    void setCurrentValue() {
        calculator.setCurrentValue(99.0);
        assertThat(calculator.getCurrentValue(), is(99.0));
    }

    private static Stream<Arguments> validParamsCalculate() {
        return Stream.of(
                Arguments.of(5.0, '+', 7.0),
                Arguments.of(0.0, '+', 2.0),
                Arguments.of(-5.0, '+', -3.0),
                Arguments.of(3.0, '-', -1.0),
                Arguments.of(0.0, '-', 2.0),
                Arguments.of(-10.0, '-', 12.0),
                Arguments.of(12.1, '*', 24.2),
                Arguments.of(-12.56, '*', -25.12),
                Arguments.of(10.0, '/', 0.2),
                Arguments.of(-2.0, '/', -1.0),
                Arguments.of(1.0, '/', 2.0),
                Arguments.of(4.0, '*', 8.0)
        );
    }

    private static Stream<Arguments> exceptionParamsCalculate() {
        return Stream.of(
                Arguments.of(0.0, '/', DivisionByZeroException.class),
                Arguments.of(5.0, '?', NotSupportedOperationException.class),
                Arguments.of(11.24, 'a', NotSupportedOperationException.class),
                Arguments.of(5.0, '#', NotSupportedOperationException.class),
                Arguments.of(2.5, '!', NotSupportedOperationException.class),
                Arguments.of(3.0, 'z', NotSupportedOperationException.class)
        );
    }

}