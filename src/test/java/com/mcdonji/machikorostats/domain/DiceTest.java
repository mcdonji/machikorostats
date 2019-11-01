package com.mcdonji.machikorostats.domain;

import org.assertj.core.util.Arrays;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DiceTest {

    @Test
    public void RollOfSingleDiceIsOnceSixthForEachValue() {
        final List<Object> possibleValues = Arrays.asList(new int[]{1, 2, 3, 4, 5, 6});
        int[] counts = new int[]{0,0,0,0,0,0};
        for (int i = 0; i < 1000; i++) {
            DiceRoll roll = Dice.Roll(1);
            assertEquals(true, possibleValues.contains(roll.getValue()));
            counts[roll.getValue() -1]++;
        }
    }
}
