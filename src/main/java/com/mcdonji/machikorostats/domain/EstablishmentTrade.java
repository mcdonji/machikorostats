package com.mcdonji.machikorostats.domain;

public class EstablishmentTrade {
    private Player otherPlayer;
    private Establishment toTake;
    private Establishment toGive;

    public EstablishmentTrade(Player otherPlayer, Establishment toTake, Establishment toGive) {
        this.otherPlayer = otherPlayer;
        this.toTake = toTake;
        this.toGive = toGive;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public Establishment getToTake() {
        return toTake;
    }

    public Establishment getToGive() {
        return toGive;
    }
}
