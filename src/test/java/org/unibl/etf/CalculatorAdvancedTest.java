package org.unibl.etf;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.unibl.etf.exception.NotSupportedOperationException;
import org.unibl.etf.exception.NumberNotInAreaException;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;

class CalculatorAdvancedTest {
    private CalculatorAdvanced calculatorAdvanced;

    @BeforeEach
    void setUp() {
        calculatorAdvanced = new CalculatorAdvanced();
    }

    @AfterEach
    void tearDown() {
        calculatorAdvanced.setCurrentValue(0.0);
        System.out.println("CalculatorAdvanced reset to initial state.");
    }

    @ParameterizedTest
    @MethodSource("validParamsCalculateAdvanced")
    void testCalculateAdvancedMethod(Double value, char action, Double result) throws NotSupportedOperationException, NumberNotInAreaException {
        calculatorAdvanced.setCurrentValue(value);
        calculatorAdvanced.calculateAdvanced(action);
        assertThat(calculatorAdvanced.getCurrentValue(), is(result));
    }

    @ParameterizedTest
    @MethodSource("invalidParamsCalculateAdvanced")
    void testCalculateAdvancedWithExceptions(Double value, char action, Class<? extends Exception> expectedException) {
        calculatorAdvanced.setCurrentValue(value);
        assertThrows(expectedException, () -> calculatorAdvanced.calculateAdvanced(action));
        //Exception exception = assertThrows(expectedException, () -> calculatorAdvanced.calculateAdvanced(action));
        //assertThat(exception.getClass(), is(expectedException));
    }

    @ParameterizedTest
    @MethodSource("validParamsHasCharacteristic")
    void testHasCharacteristic(Double value, char action, boolean expected) throws NotSupportedOperationException, NumberNotInAreaException {
        calculatorAdvanced.setCurrentValue(value);
        boolean result = calculatorAdvanced.hasCharacteristic(action);
        assertThat(result, is(expected));
    }

    @ParameterizedTest
    @MethodSource("invalidParamsHasCharacteristic")
    void testHasCharacteristicWithExceptions(Double value, char action, Class<? extends Exception> expectedException) {
        calculatorAdvanced.setCurrentValue(value);
        assertThrows(expectedException, () -> calculatorAdvanced.hasCharacteristic(action));
        //Exception exception = assertThrows(expectedException, () -> calculatorAdvanced.calculateAdvanced(action));
        //assertThat(exception.getClass(), is(expectedException));
    }

    private static Stream<Arguments> validParamsCalculateAdvanced() {
        return Stream.of(
                Arguments.of(2.0, '0', 1.0),
                Arguments.of(2.0, '1', 2.0),
                Arguments.of(2.0, '2', 4.0),
                Arguments.of(2.0, '9', 512.0),
                Arguments.of(2.0, '!', 2.0),
                Arguments.of(0.0, '!', 1.0),
                Arguments.of(10.0, '!', 3628800.0),
                Arguments.of(7.0, '!', 5040.0)
        );
    }

    private static Stream<Arguments> invalidParamsCalculateAdvanced() {
        return Stream.of(
                Arguments.of(14.0, 'e', NotSupportedOperationException.class),
                Arguments.of(22.0, '.', NotSupportedOperationException.class),
                Arguments.of(-12.0, '!', NumberNotInAreaException.class),
                Arguments.of(11.4, '!', NumberNotInAreaException.class)
        );
    }

    private static Stream<Arguments> validParamsHasCharacteristic() {
        return Stream.of(
                Arguments.of(10.0, 'A', false),
                Arguments.of(10.0, 'P', false),
                Arguments.of(6.0, 'P', true),
                Arguments.of(6.0, 'A', true),
                Arguments.of(370.0, 'A', true),
                Arguments.of(1.0, 'A', true),
                Arguments.of(1.0, 'P', false)
        );
    }

    private static Stream<Arguments> invalidParamsHasCharacteristic() {
        return Stream.of(
                Arguments.of(0.0, 'J', NotSupportedOperationException.class),
                Arguments.of(15.0, '.', NotSupportedOperationException.class),
                Arguments.of(0.17, 'A', NumberNotInAreaException.class),
                Arguments.of(0.88, 'P', NumberNotInAreaException.class),
                Arguments.of(-4.0, 'P', NumberNotInAreaException.class),
                Arguments.of(0.1, 'A', NumberNotInAreaException.class),
                Arguments.of(0.5, 'P', NumberNotInAreaException.class)
        );
    }
}