package com.mcdonji.machikorostats.domain;

import java.util.Collection;
import java.util.List;

public interface Strategy {
    String getName();
    Establishment GetEstablishmentPreference(int money, EstablishmentDeck deck, Player player, Collection<Player> otherPlayers);
    int NumberOfDiceToRoll(Player player);
    boolean shouldReroll(DiceRoll roll);
    int NumberOfDiceToRollOnRerole(Player player);
    boolean shouldActivateLandmark(int money, List<Landmark> landmarks);
    Landmark landmarkToActivate(int money, List<Landmark> landmarks);
}
