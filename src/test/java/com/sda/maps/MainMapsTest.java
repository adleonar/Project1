package com.sda.maps;

import com.sda.maps.MainMaps.DecentKey;
import com.sda.maps.MainMaps.StateKey;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class MainMapsTest {

    private final static ZonedDateTime NOW_UTC = ZonedDateTime.now(ZoneOffset.UTC);

    /**
     * Zadanie M.1 Uzupełnij asercję. Następnie (po uzupełnieniu asercji)
     * zastanów się: Czy wartość jest taka jak się spodziewałeś? Czy wyobrażasz
     * sobie sytuację w której coś takiego może się wydarzyć?
     */
    @Test
    public void crappyKey() {
        Map<MainMaps.CrappyKey, String> map = MainMaps.crappyMap();
        final String keyName = "taka sama nazwa";
        MainMaps.CrappyKey keyOne = new MainMaps.CrappyKey(keyName);
        map.put(keyOne, "Sample value!");
        MainMaps.CrappyKey sameKey = new MainMaps.CrappyKey(keyName);
        Assert.assertEquals("" /*
                                * Zmien pusty "" na wartość jaką zwróci
                                * #map.get(sameKey).
                                */, map.get(sameKey));
    }

    /**
     * Zadanie M.2 Uzupełnij klasę {@code MainMaps.DecentKey} w taki sposób,
     * żeby poprawić zachowanie z zadania M.1, tj: Dwie instancje klasy
     * {@code MainMaps.DecentKey} o tym samym parametrze name powinny zwracać tę
     * samą wartość. Test powinien "przechodzić".
     */
    @Test
    public void decentKey() {
        Map<MainMaps.DecentKey, String> map = MainMaps.decentMap();
        final String keyName = "nazwa klucza";
        MainMaps.DecentKey dkOne = new DecentKey(keyName);
        final String expectedValue = "oczekiwana wartosc";
        map.put(dkOne, expectedValue);
        MainMaps.DecentKey dkTwo = new MainMaps.DecentKey(keyName);
        Assert.assertEquals(map.get(dkOne), map.get(dkTwo));
    }

    /**
     * Zadanie M.3 Bardzo dobrą praktyką jest używanie jako kluczy obiektów
     * immutable, niezmiennych. Oznacza to, że wartości pól danego obiektu mogą
     * być nadane tylko raz, następnie nie mogę być zmieniane, tylko
     * odczytywane. Poniższy przykład pokazuje potencjalny problem jaki może się
     * wydarzyć, kiedy klucz jest obiektem ze zmiennym stanem. Oczywiście
     * {@link #assingRandomName(StateKey)} jest metodą trywialną, natomiast jest
     * to tylko przykład mający ukazać potencjalne niebezpieczeństwa.
     */
    @Test
    public void stateKey() {
        Map<MainMaps.StateKey, String> map = MainMaps.stateKeyMap();
        final String keyName = "keyName";
        MainMaps.StateKey skOne = new MainMaps.StateKey(keyName);
        map.put(skOne, "some value");
        skOne.setName("stanDrugi");
        this.assingRandomName(skOne);
        MainMaps.StateKey sameSk = new MainMaps.StateKey(keyName);
        Assert.assertEquals("" /*
                                * Zmien pusty "" na wartość jaką zwróci
                                * #map.get(sameSk).
                                */, map.get(sameSk));
    }

    /**
     * Zadanie M.4 Uzyskaj z mapy dostęp do kolekcji wartości (używając metody
     * należące do interfce'u Map!). Następnie dopisz asercję używając metody
     * {@link Assert#assertThat(Object, org.hamcrest.Matcher)}, jako drugi
     * argument możesz wykorzystać {@link CoreMatchers#hasItems(Object...)} z
     * odpowiednimi argumentami.
     */
    @Test
    public void accessingValuesOne() {
        Map<Long, String> map = new HashMap<Long, String>();
        map.put(1L, "Asia");
        map.put(2L, "Kasia");
        map.put(3L, "Basia");
    }

    /**
     * Zadanie M.5 Uzyskaj z mapy dostęp do kolekcji wartości (używając metody
     * należące do interfce'u Map! innej niż w M4.). Następnie dopisz asercję
     * używając metody {@link Assert#assertThat(Object, org.hamcrest.Matcher)},
     * jako drugi argument możesz wykorzystać
     * {@link CoreMatchers#hasItems(Object...)} z odpowiednimi argumentami.
     */
    @Test
    public void accessingValuesTwo() {
        final Map<Long, String> map = new HashMap<Long, String>();
        map.put(1L, "Asia");
        map.put(2L, "Kasia");
        map.put(3L, "Basia");
    }

    /**
     * Zadanie M.6 Uzyskaj z mapy dostęp do setu kluczy (używając metody
     * należące do interfce'u Map!). Następnie dopisz asercję używając metody
     * {@link Assert#assertThat(Object, org.hamcrest.Matcher)}, jako drugi
     * argument możesz wykorzystać {@link CoreMatchers#hasItems(Object...)} z
     * odpowiednimi argumentami.
     */
    @Test
    public void accessingKeySet() {
        final Map<Long, String> map = new HashMap<Long, String>();
        map.put(1L, "Asia");
        map.put(2L, "Kasia");
        map.put(3L, "Basia");
    }

    /**
     * Zadanie M.7 Przekształcanie mapy. Zmienna powerMap zawiera mapę liczb od
     * 0 do 50 (włącznie) jako klucze i odpowiadające kwadraty tych liczb. Twoim
     * zadaniem jest przekształcenie tej mapy tak by wartościami były sześciany
     * tych liczb.
     */
    @Test
    public void changingMapValues() {
        final Map<Integer, Integer> powerMap = this.createSquareMap();
        /**
         * Miejsce na twój kod.
         */
        Assert.assertThat(powerMap.size(), CoreMatchers.is(51));
        Assert.assertThat(powerMap, CoreMatchers.equalTo(this.createQubeMap()));
    }

    /**
     * Zadanie M.8 Zamiana kluczy na wartości. Zmienna map zawiera czas w
     * formacie epoch jako klucz, oraz odpowiadającą datę w formacie
     * {@code ZonedDateTime}. Przekształć tę mapę tak, by obecne wartości były
     * kluczami, a obecne klucze - wartosciami. Zastanów się co trzeba wziąć pod
     * uwagę przeprowadzając taką transformację?
     */
    @Test
    public void invertMap() {
        final Map<Long, ZonedDateTime> map = this.epochDateMap();
        Map<ZonedDateTime, Long> inversedMap = null;
        /**
         * Miejsce na twój kod.
         */
        Assert.assertThat(inversedMap.size(), CoreMatchers.is(map.size()));
        Assert.assertThat(inversedMap, CoreMatchers.equalTo(this.dateEpochMap()));
    }

    private void assingRandomName(StateKey sk) {
        sk.setName(String.valueOf(new Random().nextLong()));
    }

    private Map<Integer, Integer> createSquareMap() {
        return IntStream.rangeClosed(0, 50).boxed()
                        .collect(Collectors.toMap(e -> e, e -> e * e));
    }

    private Map<Integer, Integer> createQubeMap() {
        return Collections.unmodifiableMap(IntStream.rangeClosed(0, 50).boxed()
                        .collect(Collectors.toMap((Integer e) -> e, (Integer e) -> e * e * e)));
    }

    private Map<Long, ZonedDateTime> epochDateMap() {
        return Collections.unmodifiableMap(
                    IntStream.range(0, 30).boxed()
                        .collect(
                            Collectors.toMap(
                                (Integer e) -> NOW_UTC.plusDays(e).toEpochSecond(),
                                (Integer e) -> NOW_UTC.plusDays(e)
                            )
                        ));
    }

    private Map<ZonedDateTime, Long> dateEpochMap() {
        return Collections.unmodifiableMap(
                   IntStream.range(0, 30).boxed()
                        .collect(
                            Collectors.toMap(
                                (Integer e) -> NOW_UTC.plusDays(e),
                                (Integer e) -> NOW_UTC.plusDays(e).toEpochSecond()
                            )
                        ));
    }

}
