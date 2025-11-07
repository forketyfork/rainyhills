package com.forketyfork.rainyhills.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A test case for the {@link LinearVolumeCalculator} with null-valued argument.
 *
 * Created by Forketyfork on 24.03.17.
 */
public class LinearVolumeCalculatorNullTest {

    private final VolumeCalculator calculator = new LinearVolumeCalculator();

    @Test
    public void whenListIsNull_thenException() {
        assertThrows(NullPointerException.class, () -> calculator.calculate(null));
    }

}
