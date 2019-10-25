package com.mcdonji.machikorostats.domain;

import com.mcdonji.machikorostats.domain.strategies.FirstSequentialAffordableStrategy;
import org.junit.Test;

import java.util.Random;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerTest {


    @Test
    public void testCanCreatePlayers() {
        EstablishmentDeck deck = EstablishmentDeck.CreateDeck();
        Player player1 = new Player(1, "Jim", new Random(), 3,  deck.Establishments());
        Player player2 = new Player(2, "Pamela", new Random(), 3,  deck.Establishments());
        Player player3 = new Player(3, "Sarabi", new Random(), 3,  deck.Establishments());

        ArrayList<Player> players = new ArrayList<Player>(3);
        players.add(player1);
        players.add(player2);
        players.add(player3);

	
        for (Player player : players) 
    	{
            player.addOtherPlayers(players);
        }

        assertEquals(players.size(),3);
        assertEquals(2, player1.otherPlayerCount());
        assertEquals(2, player2.otherPlayerCount());
        assertEquals(2, player3.otherPlayerCount());
    }

    @Test
    public void testRevenueFromPlayerWithNoEstablishments() {
        Player jim = new Player(1, "Jim", new Random(), 3, new ArrayList<Establishment>() {});
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(1)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(2)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(3)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(4)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(5)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(6)).get(jim.getId()).intValue());
    }

    @Test
    public void testRevenueFromPlayerWithWheatField() {
        ArrayList<Establishment> initialEstablishments = new ArrayList<>();
        initialEstablishments.add(Establishments.WheatField);
        Player jim = new Player(1, "Jim", new Random(), 3, initialEstablishments);
        assertEquals(1, jim.CalculateRevenueForRoll(new DiceRoll(1)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(2)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(3)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(4)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(5)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(6)).get(jim.getId()).intValue());
    }

    @Test
    public void testRevenueFromPlayerWithRanch() {
        ArrayList<Establishment> initialEstablishments = new ArrayList<>();
        initialEstablishments.add(Establishments.Ranch);
        Player jim = new Player(1, "Jim", new Random(), 3, initialEstablishments);
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(1)).get(jim.getId()).intValue());
        assertEquals(1, jim.CalculateRevenueForRoll(new DiceRoll(2)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(3)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(4)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(5)).get(jim.getId()).intValue());
        assertEquals(0, jim.CalculateRevenueForRoll(new DiceRoll(6)).get(jim.getId()).intValue());
    }

    @Test
    public void testRevenueFromPlayerWithCafe() {
        ArrayList<Establishment> initialEstablishments = new ArrayList<>();
        initialEstablishments.add(Establishments.Cafe);
        Player jim = new Player(1, "Jim", new Random(), 3, initialEstablishments);
        Player pamela = new Player(2, "Pamela", new Random(), 3, new ArrayList<Establishment>());

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(jim);
        players.add(pamela);

        jim.addOtherPlayers(players);
        pamela.addOtherPlayers(players);

        assertEquals(0, pamela.CalculateRevenueForRoll(new DiceRoll(1)).get(jim.getId()).intValue());
        assertEquals(0, pamela.CalculateRevenueForRoll(new DiceRoll(2)).get(jim.getId()).intValue());
        assertEquals(1, pamela.CalculateRevenueForRoll(new DiceRoll(3)).get(jim.getId()).intValue());
        assertEquals(0, pamela.CalculateRevenueForRoll(new DiceRoll(4)).get(jim.getId()).intValue());
        assertEquals(0, pamela.CalculateRevenueForRoll(new DiceRoll(5)).get(jim.getId()).intValue());
        assertEquals(0, pamela.CalculateRevenueForRoll(new DiceRoll(6)).get(jim.getId()).intValue());

        assertEquals(0,  pamela.CalculateRevenueForRoll(new DiceRoll(1)).get(pamela.getId()).intValue());
        assertEquals(0,  pamela.CalculateRevenueForRoll(new DiceRoll(2)).get(pamela.getId()).intValue());
        assertEquals(-1, pamela.CalculateRevenueForRoll(new DiceRoll(3)).get(pamela.getId()).intValue());
        assertEquals(0,  pamela.CalculateRevenueForRoll(new DiceRoll(4)).get(pamela.getId()).intValue());
        assertEquals(0,  pamela.CalculateRevenueForRoll(new DiceRoll(5)).get(pamela.getId()).intValue());
        assertEquals(0,  pamela.CalculateRevenueForRoll(new DiceRoll(6)).get(pamela.getId()).intValue());
    }




    @Test
    public void testWheatFieldMove() {
        EstablishmentDeck deck = EstablishmentDeck.CreateDeck();
        assertEquals(15, deck.AvaliableEstablishments().size());
        ArrayList<Establishment> initEstablishments = new ArrayList<>();
        initEstablishments.add(Establishments.WheatField);
        initEstablishments.add(Establishments.Bakery);

        Strategy defaultStrategy = new FirstSequentialAffordableStrategy();
        Player jim = new Player(1, "Jim", new Random(), 3,  initEstablishments, defaultStrategy);
        Player pamela = new Player(2, "Pamela", new Random(), 3,  initEstablishments, defaultStrategy);

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(jim);
        players.add(pamela);

        jim.addOtherPlayers(players);
        pamela.addOtherPlayers(players);

        deck = jim.move(deck, new DiceRoll(1));

        assertEquals(3, jim.Establishments().size());
        assertEquals(3, jim.Money());
        assertEquals(5, deck.AvaliableEstablishmentsCount(Establishments.WheatField));
    }
}
