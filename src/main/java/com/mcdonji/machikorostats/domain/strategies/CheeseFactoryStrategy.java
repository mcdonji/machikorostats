package com.mcdonji.machikorostats.domain.strategies;

import com.mcdonji.machikorostats.domain.*;

import java.util.ArrayList;

public class CheeseFactoryStrategy implements Strategy {

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public Establishment GetEstablishmentPreference(int money, EstablishmentDeck deck, Player player, ArrayList<Player> otherPlayers) {
        if (deck.AvaliableEstablishmentsCount(Establishments.Ranch) > 0) {
            if (deck.CostOf(Establishments.Ranch) <= money) {
                return Establishments.Ranch;
            } else {
                return null;
            }
        }

        if (deck.AvaliableEstablishmentsCount(Establishments.CheeseFactory) > 0) {
            if (deck.CostOf(Establishments.CheeseFactory) <= money) {
                return Establishments.CheeseFactory;
            } else {
                return null;
            }
        }
        if (deck.AvaliableEstablishmentsCount(Establishments.Bakery) > 0) {
            if (deck.CostOf(Establishments.Bakery) <= money) {
                return Establishments.Bakery;
            } else {
                return null;
            }
        }

        return null;
    }

    @Override
    public int NumberOfDiceToRoll(Player player) {
        return player.canRoll();
    }

    @Override
    public boolean shouldReroll(DiceRoll roll) {
        if (roll.getValue() != 7) {
            return true;
        }
        return false;
    }

    @Override
    public int NumberOfDiceToRollOnRerole(Player player) {
        return player.canRoll();
    }


    @Override
    public Landmark landmarkToActivate(int money, ArrayList<Landmark> landmarks) {
        ArrayList<String> landMarkActivationOrder = new ArrayList<>();
        landMarkActivationOrder.add(Landmark.RadioTowerName);
        landMarkActivationOrder.add(Landmark.ShoppingMallName);
        landMarkActivationOrder.add(Landmark.AmusementParkName);
        landMarkActivationOrder.add(Landmark.TrainStationName);
        return Landmark.getLandmarkInActivationOrder(money, landmarks, landMarkActivationOrder);
    }


    @Override
    public Player ChoosePlayerToTakeFrom(Player player, ArrayList<Player> otherPlayers) {
        return otherPlayers.iterator().next();
    }

    @Override
    public EstablishmentTrade ChoosePlayerAndEstablishmentToTakeAndGive(Player player, ArrayList<Player> otherPlayers) {
        Player otherPlayer = otherPlayers.iterator().next();
        return new EstablishmentTrade(otherPlayer, otherPlayer.getGreenAndBlueEstablishments().iterator().next(), player.getGreenAndBlueEstablishments().iterator().next());
    }
}
