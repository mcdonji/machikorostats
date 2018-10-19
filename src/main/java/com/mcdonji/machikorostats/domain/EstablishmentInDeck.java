package com.mcdonji.machikorostats.domain;

public class EstablishmentInDeck {
    private Establishment establishment;
    private int establishmentsNumber;
    private int establishmentsLeft;
    private int costOfEstablishment;

    public EstablishmentInDeck(int establishmentsNumber, Establishment establishment, int cost) {
        this.establishmentsNumber = establishmentsNumber;
        this.establishmentsLeft = establishmentsNumber;
        this.establishment = establishment;
        this.costOfEstablishment = cost;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public int getEstablishmentsNumber() {
        return establishmentsNumber;
    }

    public int getEstablishmentsLeft() {
        return establishmentsLeft;
    }

    public int getCostOfEstablishment() {
        return costOfEstablishment;
    }

    public boolean reduceEstablishmentsNumber() {
        if (establishmentsNumber - establishmentsLeft > 0) {
            establishmentsNumber--;
            return true;
        }
        return false;
    }
}
