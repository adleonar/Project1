package com.sda.maps.fl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Zadanie projektowe P.M.1
 * W skrócie:
 *  Celem zadania jest sprawienie by test {@link #checkMap()} przechodził,
 *  nie używając metody {@link FantasyLandMapTestSolution#retrieveData()}.
 * Bardziej szczegółowo:
 *  W resources istnieje plik typu csv (comma seperated values) zawierający 
 *  dane ze spisu ludności fikcyjnego świata. Plik ten nazywa się forgottenland.csv
 *  DLA ODWAŻNIEJSZYCH: Przepisać metodę all lines parsującą dane z tego pliku.
 *  Mając linie pliku (może warto odrzucić nagłówek?) w zmiennej allines przekazać ją do metody
 *  {@link FantasyLandMap#parseLines(List)}. Aktualnia ta metoda jest pusta.
 *  Należy ją zaimplementować w taki sposób by zwracała "Mapę map", tj:
 *  Mapę w której kluczem będą regiony {@code FantasyLandMap.Region} a wartościami 
 *  Mapy mające za klucz nazwę danego Miasta (pierwsza kolumna w tekście) a za wartość
 *  obiektową reprezentację miasta - {@code FantasyCity}.
 */
public class FantasyLandMapTest {

    @Test
    public void checkMap() throws Exception {
        FantasyLandMap flm = new FantasyLandMap();
        List<String> allLines = allLines();
        final Map<FantasyLandMap.Region, Map<String, FantasyCity>> map = flm.parseLines(allLines);
        
        System.out.println(map);
        System.out.println(FantasyLandMapTestSolution.retrieveData());
        
        Assert.assertThat(map, CoreMatchers.is(FantasyLandMapTestSolution.retrieveData()));
        
    }

    private List<String> allLines() {
        BufferedReader bis = new BufferedReader(
                        new InputStreamReader(
                            FantasyLandMapTest.class.getResourceAsStream("/forgottenland.csv")
                        )
                    );
         return bis.lines().collect(Collectors.toList());
    }
}
