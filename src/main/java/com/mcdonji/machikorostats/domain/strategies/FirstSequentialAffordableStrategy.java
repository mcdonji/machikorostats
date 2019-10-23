package com.mcdonji.machikorostats.domain.strategies;

import com.mcdonji.machikorostats.domain.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FirstSequentialAffordableStrategy implements Strategy {
    @Override
    public String getName() {
        return this.getClass().getName();
    }


    @Override
    public Establishment GetEstablishmentPreference(int money, EstablishmentDeck deck, Player player, Collection<Player> otherPlayers) {
        for (Establishment possibleEstablishment: deck.AvaliableEstablishments()) {
            if (deck.CostOf(possibleEstablishment) <= money) {
                return possibleEstablishment;
            }
        }
        return null;
    }

    @Override
    public int NumberOfDiceToRoll(Player player) {
        int canRoll = player.canRoll();
        if (canRoll == 2) {
            return 2;
        }
        return 1;
    }

    @Override
    public boolean shouldReroll(DiceRoll roll) {
        return false;
    }

    @Override
    public int NumberOfDiceToRollOnRerole(Player player) {
        return NumberOfDiceToRoll(player);
    }

    @Override
    public Landmark landmarkToActivate(int money, List<Landmark> landmarks) {
        for (Landmark landmark : landmarks) {
            if (landmark.isActive() == false && (money > landmark.getCost())) {
                return landmark;
            }
        }
        return null;
    }

    @Override
    public Player ChoosePlayerToTakeFrom(Player player, Collection<Player> otherPlayers) {
        return otherPlayers.iterator().next();
    }

    @Override
    public EstablishmentTrade ChoosePlayerAndEstablishmentToTakeAndGive(Player player, Collection<Player> otherPlayers) {
        Player otherPlayer = otherPlayers.iterator().next();
        return new EstablishmentTrade(otherPlayer, otherPlayer.getGreenAndBlueEstablishments().iterator().next(), player.getGreenAndBlueEstablishments().iterator().next());
    }
}
