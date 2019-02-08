package com.mcdonji.machikorostats.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EstablishmentDeck {
    public static EstablishmentDeck CreateDeck() {
        EstablishmentDeck establishmentDeck = new EstablishmentDeck();
        establishmentDeck.Add(4, Establishments.WheatField, 0);
        establishmentDeck.Add(6, Establishments.WheatField, 1);
        establishmentDeck.Add(6, Establishments.Ranch, 1);
        establishmentDeck.Add(4, Establishments.Bakery, 0);
        establishmentDeck.Add(6, Establishments.Bakery, 1);
        establishmentDeck.Add(6, Establishments.Cafe, 2);
        establishmentDeck.Add(6, Establishments.ConvenienceStore, 2);
        establishmentDeck.Add(6, Establishments.Forest, 3);
        establishmentDeck.Add(4, Establishments.Stadium, 6);
        establishmentDeck.Add(4, Establishments.TvStation, 7);
        establishmentDeck.Add(4, Establishments.BusinessCenter, 8);
        establishmentDeck.Add(6, Establishments.CheeseFactory, 5);
        establishmentDeck.Add(6, Establishments.FurnitureFactory, 3);
        establishmentDeck.Add(6, Establishments.Mine, 6);
        establishmentDeck.Add(6, Establishments.FamilyRestaurant, 3);
        establishmentDeck.Add(6, Establishments.AppleOrchard, 3);
        establishmentDeck.Add(6, Establishments.FruitVegMarket, 2);
        return establishmentDeck;
    }

    private Collection<EstablishmentInDeck> deck = new ArrayList<EstablishmentInDeck>();
    private void Add(int number, Establishment establishment, int cost) {
        deck.add(new EstablishmentInDeck(number, establishment, cost));
    }

    public Collection<Establishment> Take(ArrayList<Establishment> initEstablishments) {
        initEstablishments.forEach(this::Take);
        return initEstablishments;
    }

    public Establishment Take(Establishment establishment) {
        for (EstablishmentInDeck establishmentInDec: deck) {
            if (establishmentInDec.getEstablishment().equals(establishment)) {
                if (establishmentInDec.reduceEstablishmentsLeft()) {
                    return establishment;
                } else {
                    return null;
                }
            }
        };
        return null;
    }

    public boolean IsAvailable(Establishment desiredEstablishment) {
        for (EstablishmentInDeck establishmentInDeck: deck) {
            if (establishmentInDeck.getEstablishment().equals(desiredEstablishment)){
                return establishmentInDeck.getEstablishmentsLeft() > 0;
            }
        }
        return false;
    }

    public int CostOf(Establishment desiredEstablishment) {
        for (EstablishmentInDeck establishmentInDeck: deck) {
            if (establishmentInDeck.getEstablishment().equals(desiredEstablishment)){
                return establishmentInDeck.getCostOfEstablishment();
            }
        }
        return 0;
    }

    public int AvaliableEstablishmentsCount() {
        return deck.stream().mapToInt(i->i.getEstablishmentsLeft()).sum();
    }

    public List<Establishment> AvaliableEstablishments() {
        return deck
                .stream().filter(x -> x.getEstablishmentsLeft() > 0).collect(Collectors.toList())
                .stream().map(x-> x.getEstablishment()).collect(Collectors.toList());
    }

    public List<Establishment> Esablishments() {
        return deck
                .stream().map(x-> x.getEstablishment()).collect(Collectors.toList());
    }

}
