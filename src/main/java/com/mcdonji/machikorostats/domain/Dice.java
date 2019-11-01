package com.mcdonji.machikorostats.domain;

import java.util.Random;

public class Dice {
    private static Random random = new Random();

    public static DiceRoll Roll(int numberOfDiceToRoll) {
        DiceRoll diceRoll = new DiceRoll();
        for (int i =0; i < numberOfDiceToRoll; i++)
        {
            diceRoll.AddRoll(random.nextInt(6)+1);
        }

        return  diceRoll;
    }
}
