package com.geek;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;

public class DecipherAgent {
    /**
     * @param words Contents of lines from input file
     * @return Name of KINGDOM or NONE if emblem chars are not found in words
     */
    protected String decipherMessage(List<String> words) {
        // input of type LAND null || AIR
        if (words.get(1) == null || words.get(1).isEmpty() || words.get(1).isBlank())
            return ThronesConstants.NONE;
        if (!ThronesConstants.kingdomEmblem.containsKey(words.get(0).strip().toUpperCase()))
            return ThronesConstants.NONE;

        String kingdom = words.get(0).toUpperCase();
        List<String> decipheredChars = decipherMessage(words.get(1).strip().toLowerCase(),
                ThronesConstants.kingdomEmblem.get(words.get(0).toUpperCase()).length());

        return decipherMessage(kingdom, decipheredChars);

    }

    /**
     * Will returned the deciphered message.
     *
     * @param word  Which needs to be deciphered
     * @param shift Key to be used for process
     * @return deciphered message
     */
    private List<String> decipherMessage(String word, int shift) {

        char[] alphabetsChar =
                IntStream.rangeClosed('a', 'z')
                        .mapToObj(c -> (char) c + "")
                        .collect(Collectors.joining())
                        .toCharArray();

        List decipheredChars = word.chars()
                .mapToObj(i -> (char) i)
                .map(
                        alphabet -> {
                            int indexOfChar = (ThronesConstants.alphabets.indexOf(alphabet) - shift) % 26;
                            indexOfChar = indexOfChar < 0
                                    ? ThronesConstants.alphabets.length() + indexOfChar
                                    : indexOfChar;

                            alphabet = alphabetsChar[indexOfChar];
                            return alphabet.toString();
                        }
                ).collect(Collectors.toList());

        return decipheredChars;
    }

    /**
     * Deciphered message will be searched for Emblem characters
     *
     * @param kingdom         Kingdom Name eg: LAND, AIR
     * @param decipheredChars Deciphered message
     * @return Returns Kingdom name for valid case.
     */
    private String decipherMessage(String kingdom, List<String> decipheredChars) {
        switch (kingdom) {
            case "LAND": {
                return checkForEmblemChars(decipheredChars, ThronesConstants.kingdomEmblem.get("LAND"))
                        ? "LAND"
                        : ThronesConstants.NONE;
            }
            case "WATER": {
                return checkForEmblemChars(decipheredChars, ThronesConstants.kingdomEmblem.get("WATER"))
                        ? "WATER"
                        : ThronesConstants.NONE;
            }

            case "ICE": {
                return checkForEmblemChars(decipheredChars, ThronesConstants.kingdomEmblem.get("ICE"))
                        ? "ICE"
                        : ThronesConstants.NONE;
            }
            case "AIR": {
                return checkForEmblemChars(decipheredChars, ThronesConstants.kingdomEmblem.get("AIR"))
                        ? "AIR"
                        : ThronesConstants.NONE;
            }
            case "FIRE": {
                return checkForEmblemChars(decipheredChars, ThronesConstants.kingdomEmblem.get("FIRE"))
                        ? "FIRE"
                        : ThronesConstants.NONE;
            }
            default:
                return ThronesConstants.NONE;
        }
    }

    /**
     * Checks for emblem characters
     *
     * @param decipheredChars
     * @param emblem
     * @return
     */
    private boolean checkForEmblemChars(List<String> decipheredChars, String emblem) {
        try {
            List<Character> emblemChars =
                    emblem.toLowerCase()
                    .chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());

            //Get Map which has all the chars against their count
            Map<String, Long> emblemCharCountMap =
                    emblemChars
                            .stream()
                            .collect(groupingBy(e -> e.toString(), Collectors.counting()));


            for (Map.Entry entry : emblemCharCountMap.entrySet()) {
                if (!decipheredChars.contains(entry.getKey()))
                    return false;
                else {
                    Long count = decipheredChars
                            .stream()
                            .filter(foundChar -> entry.getKey().equals(foundChar))
                            .count();
                    // Count "this" of char in list is less than that of MAP
                    if (count < (Long) entry.getValue())
                        return false;
                }
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

}