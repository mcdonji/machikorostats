package com.mcdonji.machikorostats.domain;

import com.mcdonji.machikorostats.domain.strategies.FirstSequentialAffordableStrategy;
import com.mcdonji.machikorostats.domain.strategies.RandomStrategy;

public class StrategyEvaluator {

    private Strategy strategy;

    public StrategyEvaluator(Strategy strategy) {
        this.strategy = strategy;
    }

    public void EvaluateAgainstRandom() {
        final int gamesPlayed = 1000;
        double gamesWon = 0;
        double totalTicksSpentToWin = 0;
        for (int i = 0; i < gamesPlayed; i++) {
            Game gameTwoPlayerBaseline = new GameWithRandomOrder(new RandomStrategy(), new RandomStrategy());
            final GameResult result = gameTwoPlayerBaseline.play();
            if (result.Winner.getId() == gameTwoPlayerBaseline.getFirstPlayer().getId()) {
                gamesWon++;
                totalTicksSpentToWin = totalTicksSpentToWin + result.getTicks().size();
            }
        }

        double gamesWonProb = gamesWon / gamesPlayed;
        double averageTicksToWin = gamesWon == 0 ? 0 : totalTicksSpentToWin / gamesWon;

        gamesWon = 0;
        totalTicksSpentToWin = 0;
        for (int i = 0; i < gamesPlayed; i++) {
            Game gameTwoPlayerConvenienceStore = new GameWithRandomOrder(new FirstSequentialAffordableStrategy(), new RandomStrategy());
            final GameResult result = gameTwoPlayerConvenienceStore.play();
            if (result.Winner.getId() == gameTwoPlayerConvenienceStore.getFirstPlayer().getId()) {
                gamesWon++;
                totalTicksSpentToWin = totalTicksSpentToWin + result.getTicks().size();

            }
//            ArrayList<String> ticks = result.getTicks();
//            for (int j = 0; j < ticks.size(); j++) {
//                System.out.println(j + " " + ticks.get(j));
//            }
        }
    }

}
