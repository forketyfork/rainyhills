package com.forketyfork.rainyhills.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for {@link LinearVolumeCalculator} with different valid input and output values.
 *
 * @see LinearVolumeCalculator
 * <p>
 * Created by Sergey Petunin on 23.03.17.
 */
@RunWith(Parameterized.class)
public class LinearVolumeCalculatorTest {

    private VolumeCalculator calculator = new LinearVolumeCalculator();

    private int expected;

    private List<Integer> list;

    public LinearVolumeCalculatorTest(Integer expected, List<Integer> list) {
        this.expected = expected;
        this.list = list;
    }

    @Test
    public void whenListIsNotNull_thenOutputIsCorrect() {
        assertEquals(expected, calculator.calculate(list));
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return asList(new Object[][]{
                        {0, Collections.emptyList()},
                        {0, Collections.singletonList(1)},
                        {0, asList(1, 2)},
                        {2, asList(2, 0, 2)},
                        {0, asList(0, 2, 0)},
                        {6, asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)},
                        {2, asList(3, 2, 4, 1, 2)},
                        {8, asList(4, 1, 1, 0, 2, 3)},
                        {14, asList(5, 2, 1, 2, 1, 5),},
                        {9, asList(4, 2, 0, 3, 2, 5)}
                }
        );
    }

}
