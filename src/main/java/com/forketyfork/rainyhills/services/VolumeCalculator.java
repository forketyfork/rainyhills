package com.forketyfork.rainyhills.services;

import java.util.List;

/**
 * Created by Forketyfork on 24.03.17.
 */
public interface VolumeCalculator {

    /**
     * Calculates the volume of the water that stays on the walls with specified heights after the rain.
     *
     * @param heights the list of wall heights
     * @return the volume of water gathered
     */
    int calculate(List<Integer> heights);

}
