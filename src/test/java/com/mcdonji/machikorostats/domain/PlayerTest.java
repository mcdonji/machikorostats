package com.mcdonji.machikorostats.domain;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void testRepositoryReturnsNullOnInvalidFoodGroupCode() {
        EstablishmentDeck deck = EstablishmentDeck.CreateDeck();
        Player player = new Player(new Random(), 3,  deck.Esablishments());
//        assertThat("", player.Move(deck)).isNull();
    }
}
