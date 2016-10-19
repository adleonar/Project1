package com.sda.maps.fl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class FantasyLandMapTestSolution {

    /**
     * DO NOT LOOK HERE! THIS IS SOLUTION MADE ON PURPOSE TO BE NON-READABLE AND
     * USE STREAMS AND JAVA8
     */
    public static Map<FantasyLandMap.Region, Map<String, FantasyCity>> retrieveData() {
        BufferedReader bis = new BufferedReader(
                                new InputStreamReader(
                                    FantasyLandMapTest.class.getResourceAsStream("/forgottenland.csv")
                                )
                            );
        return bis.lines().skip(1)
            .map(String::trim)
            .map(line -> line.split(","))
            .collect(Collectors.groupingBy(arr -> FantasyLandMap.Region.fromName(arr[1])))
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    val -> val.getKey(),
                    val -> val.getValue().stream().collect(
                                    Collectors.toMap(
                                        (String[] arr) -> arr[0],
                                        arr -> new FantasyCity(arr[0], FantasyLandMap.Race.fromName(arr[3]), Long.valueOf(arr[2]))
                                    )
                           )
                 )
             );
    }

}
