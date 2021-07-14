package com.geek;

import java.util.HashMap;
import java.util.Map;

public class ThronesConstants {
    public static String alphabets = null;
    public static Map<String, String> kingdomEmblem = null;
    public static String NONE = "NONE";
    public static int MINIMUM_REQUIRED_KINGDOMS = 3;

    static {
        alphabets = "abcdefghijklmnopqrstuvwxyz";
        kingdomEmblem = new HashMap();
        kingdomEmblem.put("SPACE", "GORILLA");
        kingdomEmblem.put("LAND", "PANDA");
        kingdomEmblem.put("WATER", "OCTOPUS");
        kingdomEmblem.put("ICE", "MAMMOTH");
        kingdomEmblem.put("AIR", "OWL");
        kingdomEmblem.put("FIRE", "DRAGON");
    }
}
