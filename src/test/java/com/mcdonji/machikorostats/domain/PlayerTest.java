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
//        EstablishmentDeck updatedDeck = player.Move(deck);
    }
}
