package com.forketyfork.rainyhills.services;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The implementation of the volume calculation algorithm that uses O(n) time and O(n) space.
 * <p>
 * Each element shall contain water on it only if there exist some greater elements
 * to the left and to the right of it, we'll call them "walls". To find the amount of water
 * over each element, we take the highest wall to the left, the highest wall to the right,
 * find the lowest wall of the two, and find the difference between the current element
 * and the lowest of the two walls.
 * <p>
 * To do this, we need only two iterations through the list. During the first iteration,
 * in direct order, we find the "left wall" values. During the second iteration, in reversed order,
 * we both find the "right wall" value and the amount of water that holds on the current element.
 * <p>
 * Thus the algorithm is executed in linear O(n) time and linear O(n) space.
 * <p>
 * Created by Forketyfork on 23.03.17.
 */
@ApplicationScoped
public class LinearVolumeCalculator implements VolumeCalculator, Serializable {

    /**
     * Calculates the volume of the water that stays on the walls with specified heights after the rain.
     *
     * @param heights the list of wall heights
     * @return the volume of water gathered
     */
    @Override
    public int calculate(List<Integer> heights) {
        Objects.requireNonNull(heights, "The heights list cannot be null");

        // can't gather any water in an list with less than 3 walls
        if (heights.size() < 3) {
            return 0;
        }

        // the list of the highest elements to the left of the current
        List<Integer> leftWalls = new ArrayList<>(heights.size());

        // no amount of water can hold on a leftmost element, obviously
        leftWalls.add(heights.get(0));

        // the first iteration through the list — we find the highest walls
        // to the left of the current
        for (int i = 1; i < heights.size(); i++) {
            leftWalls.add(Math.max(leftWalls.get(i - 1), heights.get(i)));
        }

        // this shall be the highest element to the right of the current
        int rightWall = heights.get(heights.size() - 1);

        int sum = 0;

        // we skip the leftmost and the rightmost elements, as no amount of water
        // can hold on them, obviously
        for (int i = heights.size() - 2; i > 0; i--) {
            sum += Math.max(0, Math.min(rightWall, leftWalls.get(i)) - heights.get(i));
            rightWall = Math.max(rightWall, heights.get(i));
        }

        return sum;

    }

}