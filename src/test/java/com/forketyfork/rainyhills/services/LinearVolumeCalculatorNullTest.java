package com.forketyfork.rainyhills.services;

import org.junit.Test;

/**
 * A test case for the {@link LinearVolumeCalculator} with null-valued argument.
 *
 * Created by Forketyfork on 24.03.17.
 */
public class LinearVolumeCalculatorNullTest {

    private VolumeCalculator calculator = new LinearVolumeCalculator();

    @Test(expected = NullPointerException.class)
    public void whenListIsNull_thenException() {
        calculator.calculate(null);
    }

}
