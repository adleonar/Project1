package com.sda.maps.fl;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class FantasyLandMap {

    public Map<Region, Map<String, FantasyCity>> parseLines(List<String> lines) {
		String city;
		Region region;
		Long population;
		String[] currentCityInfo;
    	Map<Region, Map<String, FantasyCity>> fantasyLandMap = new EnumMap<Region, Map<String, FantasyCity>>(Region.class);     	   	
    	
    	lines.remove(0);    
    	
    	for (String currentCityInFile : lines)	{
    		
    		currentCityInfo = currentCityInFile.trim().split(",");
    		city = currentCityInfo[0];
    		region = Region.fromName(currentCityInfo[1]);
    		population = Long.parseLong(currentCityInfo[2]);
    		Race race = Race.fromName(currentCityInfo[3]);
    		    		
    		FantasyCity fantasyCity = new FantasyCity(city, race, population);   		 		
    		
    		Map<String, FantasyCity> currentRegionMap = fantasyLandMap.get(region);
    		
    		if (currentRegionMap==null){
    			currentRegionMap = new HashMap<String, FantasyCity>();
    		}
    		currentRegionMap.put(city, fantasyCity);		    		
    		fantasyLandMap.put(region, currentRegionMap);
    	} 	
        return fantasyLandMap;
    }

    
    

    public static enum Region {

        IAGROYDAL("Iagroydal"),

        SOSCUYVANIA("Soscuyvania"),

        JOCRARIA("Jocraria"),

        KETHIJAN("Kethijan");

        private final String name;

        Region(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
        
        public static Region fromName(String name) {
            Optional<Region> regionOption =
                Arrays.asList(Region.values()).stream()
                    .filter(r -> r.name.equals(name))
                    .findFirst();
            return regionOption
                            .orElseThrow(
                                () -> new IllegalArgumentException(
                                    String.format("No region with %s found!", name)
                                )
                            );
        }

    }

    public static enum Race {

        ELF("Barbaric Waterway Elf"),

        HALFLING("Cursed Fire Halfling"),

        DARK_OGRE("Dark Ogre"),

        MERFLOK("Gray Merfolk"),
        
        HUMAN_ORGE("Human-Ogre");

        private final String name;

        Race(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
        
        public static Race fromName(String name) {
            Optional<Race> raceOption =
                Arrays.asList(Race.values()).stream()
                    .filter(r -> r.name.equals(name))
                    .findFirst();
            return raceOption
                            .orElseThrow(
                                () -> new IllegalArgumentException(
                                    String.format("No race: %s found!", name)
                                )
                            );
        }

    }

}
