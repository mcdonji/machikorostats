package com.mcdonji.machikorostats.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameWithRandomOrder extends Game {
    public GameWithRandomOrder(Strategy... strategies) {
        final List<Strategy> list = Arrays.asList(strategies);
        Collections.shuffle(list);
        super.Game(Arrays.asList(strategies));
    }

}
