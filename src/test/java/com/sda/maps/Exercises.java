package com.sda.maps;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class Exercises {

    /**
     * Na rozgrzewke!
     */
    @Test
    public void warmUp() {
		Map<String, Integer> sampleMap = new HashMap<String, Integer>();
		
		sampleMap.put("Piotrek", 23);
		sampleMap.put("Krzysiek", 25);
		
		Assert.assertEquals(2,  sampleMap.size());
		
		sampleMap.remove("Krzysiek");
		
		Assert.assertEquals(1, sampleMap.size());
		final Integer oldValue = sampleMap.values().iterator().next();
		
		sampleMap.put("Krzysiek", 35);
		
		final Integer newValue = sampleMap.values().iterator().next();
		Assert.assertNotEquals(oldValue, newValue);	
    }

    /**
     * Spojrz na String rawCars. Zawiera on lise marek samochodow
     * wymienionych po przecinku. Twoim zadaniem jest stworzyc taka
     * mape typu Map<String, Integer> w ktorej kluczem
     * bedzie nazwa marki samochodu (np. mini) a wartoscia liczba
     * wystapien danej marki w tekscie.
     * 
     * Przydatne metody:
     *  - {@link String#split(String)}:
     *      Metoda zmiennych typu String, dzielaca String na tablice typu
     *      String[]. Jako parametr przyjmuje String na bazie ktorego ma
     *      sie odbyc podzial.
     *      Np. "mama,tata".split(",") zwrÃ³ci nami ["mama", "tata"]
     *  - {@link Map#getOrDefault(Object, Object)}:
     *      Metoda ta sluzy do zapobiegniecia zwracania nulla, kiedy
     *      dany klucz nie istnieje w mapie. Np. posiadajac mape
     *      przykladMapy = {"Piotr"=24}, gdy uzyjemy metody
     *      przykladyMapy.getOrDefault("Lukasz", 0) zamiast nulla
     *      (gdyz "Lukasz" nie znajduje sie w mapie) otrzymamy wartosc 0.
     *      tip: Jesli w mapie nie jeszcze samochodu danej marki, to znaczy,
     *      ze ile razy wystÄ…piÅ‚ do tej pory?
     *     
     */
    @Test
    public void letterOccurences() {
        String rawCars = "mercedes,lexus,alfa romeo,mercedes,"
            + "lexus,alfa romeo,jeep,mercedes,volvo,volvo,volvo,volvo"  /* "Surowy" tekst, ktory nalezy przetworzyc */
            + ",mini,bmw,bmw";
        
		String[] modifiedRawCars = rawCars.split(",");

		Map<String, Integer> carOccurrences = new HashMap<String, Integer>();
		int tempValue;
		
		for (String car : modifiedRawCars){
			tempValue = carOccurrences.getOrDefault(car, 0);
			carOccurrences.put(car, tempValue+1);
		}
		
		int val=0;
		for (int v : carOccurrences.values()){
			val += v;
		}
		
		System.out.println(carOccurrences + " £¹cznie samochodów jest: " + val);
		
        
		
        																/* 
                                                                         Nowa, pusta mapa, w ktorej powinny znalezc
                                                                         sie marki aut jako klucze, a liczba ich 
                                                                         wystapien jako wartosc
                                                                         */

        
        Assert.assertThat(carOccurrences,CoreMatchers.is(this.letterOccurencesAnswer()));                                                              /*
                                                                        Asercja majÄ…ca sprawdziÄ‡, czy Wasza mapa jest taka sama
                                                                        jak ta policzona w tej metodzie (policzna przeze mnie)
                                                                        */
    }

    @Test
    public void enumMap() {
        String rawCars = "mercedes,lexus,mercedes,"
            + "lexus,jeep,mercedes,volvo,volvo,volvo,volvo"  /* "Surowy" tekst, ktory nalezy przetworzyc */
            + ",mini,bmw,bmw";
        
        Map<Brands, Integer> enumMap = new EnumMap<Brands, Integer>(Brands.class);

        String[] modifiedRawCars = rawCars.toUpperCase().split(",");
        
        int tempValue;
        
        for (String car : modifiedRawCars){
        	Brands brand = Brands.valueOf(car.toUpperCase());
        	tempValue = enumMap.getOrDefault(brand, 0);
        	enumMap.put(brand, tempValue+1);
        }
        
        System.out.println(enumMap);
        
        
    }

    @Test
    public void weakHashMap() {
        
    }

    @Test
    public void concurrentHashMap() {
    }

    /**
     * Metoda zawierajaca odpowiedz.
     */
    private Map<String, Integer> letterOccurencesAnswer() {
        Map<String, Integer> occurrences = new HashMap<String, Integer>();
        occurrences.put("jeep", 1);
        occurrences.put("mini", 1);
        occurrences.put("mercedes", 3);
        occurrences.put("alfa romeo", 2);
        occurrences.put("lexus", 2);
        occurrences.put("bmw", 2);
        occurrences.put("volvo", 4);
        return occurrences;
    }

    private static enum Brands	{
    	MERCEDES,
    	LEXUS,
    	JEEP,
    	VOLVO,
    	MINI,
    	BMW,
    }
    

}
