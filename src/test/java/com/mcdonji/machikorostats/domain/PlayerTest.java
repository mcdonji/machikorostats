package com.mcdonji.machikorostats.domain;

import org.junit.Test;

import java.util.Random;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testCanCreatePlayers() {
        EstablishmentDeck deck = EstablishmentDeck.CreateDeck();
        Player player1 = new Player(1, "Jim", new Random(), 3,  deck.Esablishments());
        Player player2 = new Player(2, "Pamela", new Random(), 3,  deck.Esablishments());
        Player player3 = new Player(3, "Sarabi", new Random(), 3,  deck.Esablishments());

        ArrayList<Player> players = new ArrayList<Player>(3);
        players.add(player1);
        players.add(player2);
        players.add(player3);

	
        for (Player player : players) 
    	{
            player.addOtherPlayers(players);
        }

        assertEquals(players.size(),3);
        assertEquals(player1.otherPlayerCount(), 2);
        assertEquals(player2.otherPlayerCount(), 2);
        assertEquals(player3.otherPlayerCount(), 2);
    }

    @Test
    public void testRevenueFromPlayerWithNoEstablishments() {
        Player jim = new Player(1, "Jim", new Random(), 3, new ArrayList<Establishment>() {});
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(1)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(2)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(3)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(4)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(5)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(6)), 0);
    }

    @Test
    public void testRevenueFromPlayerWithWheatField() {
        ArrayList<Establishment> initialEstablishments = new ArrayList<>();
        initialEstablishments.add(Establishments.WheatField);
        Player jim = new Player(1, "Jim", new Random(), 3, initialEstablishments);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(1)), 1);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(2)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(3)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(4)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(5)), 0);
        assertEquals(jim.revenueFromMyRoll(new DiceRoll(6)), 0);
    }

    @Test
    public void testPlayerMove() {
        EstablishmentDeck deck = EstablishmentDeck.CreateDeck();
        assertEquals(17, deck.AvaliableEstablishments().size());

        ArrayList<Establishment> initEstablishments = new ArrayList<>();
        initEstablishments.add(Establishments.WheatField);
        initEstablishments.add(Establishments.Bakery);

        Strategy defaultStrategy = new Strategy();
        Player jim = new Player(1, "Jim", new Random(), 3,  deck.Take(initEstablishments),defaultStrategy);
        Player pamela = new Player(2, "Pamela", new Random(), 3,  deck.Take(initEstablishments),defaultStrategy);

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(jim);
        players.add(pamela);

        jim.addOtherPlayers(players);
        pamela.addOtherPlayers(players);

        assertEquals(88, deck.AvaliableEstablishmentsCount());
        assertEquals(3, jim.getMoney());

        assertEquals(1, jim.HandleRoll(new DiceRoll(2)));
        deck = jim.Move(deck, new DiceRoll(2));
        assertEquals(87, deck.AvaliableEstablishmentsCount());
    }

}
