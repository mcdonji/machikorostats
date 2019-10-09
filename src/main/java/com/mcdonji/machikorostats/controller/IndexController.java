package com.mcdonji.machikorostats.controller;

import com.mcdonji.machikorostats.model.IndexModel;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class IndexController {
    @RequestMapping("/")
    public IndexModel index() {
        return new IndexModel("Welcome to Machi Koro Stats!");
    }
}