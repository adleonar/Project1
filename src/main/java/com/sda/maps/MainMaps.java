package com.sda.maps;

import java.util.HashMap;
import java.util.Map;

public class MainMaps {

    public static Map<MainMaps.CrappyKey, String> crappyMap() {
        return stringMapOfKey(CrappyKey.class);
    }

    public static Map<MainMaps.DecentKey, String> decentMap() {
        return stringMapOfKey(DecentKey.class);
    }

    public static Map<MainMaps.StateKey, String> stateKeyMap() {
        return stringMapOfKey(StateKey.class);
    }

    private static <T extends AbstractKey> Map<T, String> stringMapOfKey(
                    Class<T> klazz) {
        return new HashMap<T, String>();
    }

    private static abstract class AbstractKey {
    }

    public static class CrappyKey extends AbstractKey {

        private final String name;

        public CrappyKey(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

    }

    public static class DecentKey extends AbstractKey {

        private final String name;

        public DecentKey(String name) {
            this.name = name;
        }

        public String getname() {
            return this.name;
        }
    }

    public static class StateKey extends AbstractKey {

        private String name;

        public StateKey(String name) {
            this.name = name;
        }

        public String getname() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
