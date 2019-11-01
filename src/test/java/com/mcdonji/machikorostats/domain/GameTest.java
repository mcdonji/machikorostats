package com.mcdonji.machikorostats.domain;

import com.mcdonji.machikorostats.domain.strategies.CheeseFactoryStrategy;
import com.mcdonji.machikorostats.domain.strategies.ConvenienceStoreStrategy;
import com.mcdonji.machikorostats.domain.strategies.RandomStrategy;
import org.junit.Test;

import java.util.ArrayList;

public class GameTest {

    @Test
    public void testExecuteTwoPlayerRandomStrategyGame() {
        Game game = new Game(new RandomStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println( i  + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteFourPlayerRandomStrategyGame() {
        Game game = new Game(new RandomStrategy(), new RandomStrategy(), new RandomStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println( i  + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteTwoPlayerRandomVsConvenienceStoreStrategyGame() {
        Game game = new Game(new ConvenienceStoreStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println( i  + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteTwoPlayerTwoConvenienceStoreStrategyGame() {
        Game game = new Game(new ConvenienceStoreStrategy(), new ConvenienceStoreStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println( i  + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteFourPlayerRandomVsConvenienceStoreStrategyGame() {
        Game game = new Game(new ConvenienceStoreStrategy(), new RandomStrategy(), new RandomStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println( i  + " " + ticks.get(i));
        }
    }

    @Test
    public void testExecuteTwoPlayerRandomVsCheeseFactoryStrategyGame() {
        Game game = new Game(new CheeseFactoryStrategy(), new RandomStrategy());
        GameResult gameResult = game.play();
        ArrayList<String> ticks = gameResult.getTicks();
        for (int i = 0; i < ticks.size(); i++) {
            System.out.println( i  + " " + ticks.get(i));
        }
    }


}
