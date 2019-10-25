package com.mcdonji.machikorostats.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Strategy {
    String getName();
    Establishment GetEstablishmentPreference(int money, EstablishmentDeck deck, Player player, ArrayList<Player> otherPlayers);
    int NumberOfDiceToRoll(Player player);
    boolean shouldReroll(DiceRoll roll);
    int NumberOfDiceToRollOnRerole(Player player);
    Landmark landmarkToActivate(int money, ArrayList<Landmark> landmarks);
    Player ChoosePlayerToTakeFrom(Player player, ArrayList<Player> otherPlayers);
    EstablishmentTrade ChoosePlayerAndEstablishmentToTakeAndGive(Player player, ArrayList<Player> otherPlayers);
}


