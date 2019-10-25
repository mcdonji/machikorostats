package com.mcdonji.machikorostats.domain.strategies;

import com.mcdonji.machikorostats.domain.*;

import java.util.ArrayList;

public class ConvenienceStoreStrategy implements Strategy {

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public Establishment GetEstablishmentPreference(int money, EstablishmentDeck deck, Player player, ArrayList<Player> otherPlayers) {
        if (deck.AvaliableEstablishmentsCount(Establishments.ConvenienceStore) > 0) {
            if (deck.CostOf(Establishments.ConvenienceStore) <= money) {
                return Establishments.ConvenienceStore;
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
        return 1;
    }

    @Override
    public boolean shouldReroll(DiceRoll roll) {
        if (roll.getValue() != 4) {
            return true;
        }
        return false;
    }

    @Override
    public int NumberOfDiceToRollOnRerole(Player player) {
        return 1;
    }

    @Override
    public Landmark landmarkToActivate(int money, ArrayList<Landmark> landmarks) {
        Landmark radioTower = Landmark.get(Landmark.RadioTowerName, landmarks);
        if (!radioTower.isActive() && money >= radioTower.getCost()) {
            return radioTower;
        }
        Landmark shoppingMall = Landmark.get(Landmark.ShoppingMallName, landmarks);
        if (!shoppingMall.isActive() && money >= shoppingMall.getCost()) {
            return shoppingMall;
        }
        Landmark amusementPark = Landmark.get(Landmark.AmusementParkName, landmarks);
        if (!amusementPark.isActive() && money >= amusementPark.getCost()) {
            return amusementPark;
        }
        Landmark trainStation = Landmark.get(Landmark.TrainStationName, landmarks);
        if (!trainStation.isActive() && money >= trainStation.getCost()) {
            return trainStation;
        }
        return null;
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
