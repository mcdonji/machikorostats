package com.mcdonji.machikorostats.controller;

import com.mcdonji.machikorostats.domain.EstablishmentDeck;
import com.mcdonji.machikorostats.model.EstablishmentsModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstablishmentsController {
    @RequestMapping("/establishments")
    public EstablishmentsModel index() {
        return new EstablishmentsModel(EstablishmentDeck.CreateDeck().getDeck());
    }
}
