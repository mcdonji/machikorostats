package com.mcdonji.machikorostats.domain;

import java.util.ArrayList;

public class DiceRoll {
    public ArrayList<Integer> rolls = new ArrayList<Integer>();

    public DiceRoll() {
    }

    public DiceRoll(int i) {
        AddRoll(i);
    }

    public void AddRoll(int nextInt) {
        rolls.add(nextInt);
    }

    public int getValue() {
        return rolls.stream().reduce((integer, integer2) -> integer + integer2).get();
    }
}
