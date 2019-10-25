package com.mcdonji.machikorostats.domain;

import java.util.ArrayList;

public class GameResult {
    private ArrayList<String> ticks = new ArrayList<String>();
    private long seconds;

    public void AddTick(String tickRepresentation) {
        ticks.add(tickRepresentation);
    }
    public ArrayList<String> getTicks(){
        return ticks;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public long getSeconds() {
        return seconds;
    }
}
