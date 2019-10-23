package com.mcdonji.machikorostats.model;

import com.mcdonji.machikorostats.domain.EstablishmentInDeck;

import java.util.ArrayList;

public class EstablishmentsModel {
    ArrayList<EstablishmentModel> establishmentModels = new ArrayList<EstablishmentModel>();
    public EstablishmentsModel(ArrayList<EstablishmentInDeck> deck) {
        for (EstablishmentInDeck eid: deck) {
            establishmentModels.add(new EstablishmentModel(eid));
        }
    }

    public ArrayList<EstablishmentModel> getEstablishmentModels() {
        return establishmentModels;
    }

    public void setEstablishmentModels(ArrayList<EstablishmentModel> establishmentModels) {
        this.establishmentModels = establishmentModels;
    }
}
