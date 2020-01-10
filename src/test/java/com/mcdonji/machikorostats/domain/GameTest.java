package com.mcdonji.machikorostats.domain;

import com.mcdonji.machikorostats.domain.strategies.CheeseFactoryStrategy;
import com.mcdonji.machikorostats.domain.strategies.ConvenienceStoreStrategy;
import com.mcdonji.machikorostats.domain.strategies.FirstSequentialAffordableStrategy;
import com.mcdonji.machikorostats.domain.strategies.RandomStrategy;
import org.junit.Test;

import java.util.ArrayList;

public class GameTest {

    @Test
    public void testConvenienceStoreStrategyEvaluation() {
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

        System.out.println("Baseline: " + gamesWonProb + " averageTicksToWin: " + averageTicksToWin);

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

        gamesWonProb = gamesWon / gamesPlayed;
        averageTicksToWin = gamesWon == 0 ? 0 : totalTicksSpentToWin / gamesWon;

        System.out.println("ConvenienceStoreStrategy: " + gamesWonProb + " averageTicksToWin: " + averageTicksToWin);

    }


    @Test
    public void testExecuteTwoPlayerRandomStrategyGame() {
        Game game = new Game(new RandomStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println(i + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteFourPlayerRandomStrategyGame() {
        Game game = new Game(new RandomStrategy(), new RandomStrategy(), new RandomStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println(i + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteTwoPlayerRandomVsConvenienceStoreStrategyGame() {
        Game game = new Game(new ConvenienceStoreStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println(i + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteTwoPlayerTwoConvenienceStoreStrategyGame() {
        Game game = new Game(new ConvenienceStoreStrategy(), new ConvenienceStoreStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println(i + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteFourPlayerRandomVsConvenienceStoreStrategyGame() {
        Game game = new Game(new ConvenienceStoreStrategy(), new RandomStrategy(), new RandomStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println(i + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteTwoPlayerRandomVsCheeseFactoryStrategyGame() {
        Game game = new Game(new CheeseFactoryStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println(i + " " + ticks.get(i));
        }
    }


}
