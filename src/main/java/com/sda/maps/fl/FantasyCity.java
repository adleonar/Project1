package com.sda.maps.fl;

import com.sda.maps.fl.FantasyLandMap.Race;

public class FantasyCity {

    private final String name;

    private final FantasyLandMap.Race dominatingRace;

    private final long population;

    public FantasyCity(String name, Race dominatingRace, long population) {
        this.name = name;
        this.dominatingRace = dominatingRace;
        this.population = population;
    }

    public String getName() {
        return this.name;
    }

    public FantasyLandMap.Race getDominatingRace() {
        return this.dominatingRace;
    }

    public long getPopulation() {
        return this.population;
    }

    @Override
    public String toString() {
        return "FantasyLand [name=" + this.name + ", dominatingRace="
                        + this.dominatingRace + ", population="
                        + this.population + "]";
    }

}
