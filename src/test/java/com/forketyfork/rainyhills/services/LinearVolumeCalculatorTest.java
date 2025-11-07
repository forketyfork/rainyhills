package com.forketyfork.rainyhills.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for {@link LinearVolumeCalculator} with different valid input and output values.
 *
 * @see LinearVolumeCalculator
 * <p>
 * Created by Forketyfork on 23.03.17.
 */
public class LinearVolumeCalculatorTest {

    private final VolumeCalculator calculator = new LinearVolumeCalculator();

    @ParameterizedTest
    @MethodSource("data")
    public void whenListIsNotNull_thenOutputIsCorrect(Integer expected, List<Integer> list) {
        assertEquals(expected, calculator.calculate(list));
    }

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(0, Collections.emptyList()),
                Arguments.of(0, Collections.singletonList(1)),
                Arguments.of(0, asList(1, 2)),
                Arguments.of(2, asList(2, 0, 2)),
                Arguments.of(0, asList(0, 2, 0)),
                Arguments.of(6, asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)),
                Arguments.of(2, asList(3, 2, 4, 1, 2)),
                Arguments.of(8, asList(4, 1, 1, 0, 2, 3)),
                Arguments.of(14, asList(5, 2, 1, 2, 1, 5)),
                Arguments.of(9, asList(4, 2, 0, 3, 2, 5))
        );
    }

}
