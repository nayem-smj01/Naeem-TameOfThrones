package com.geek;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeekTrust {
    public static void main(String[] args) {
        String inputFile = null;
        try {
            inputFile = args[0];
            List<String> messages;
            messages = Files.readAllLines(Paths.get(inputFile),
                    StandardCharsets.UTF_8);

            messages = messages.stream()
                    .filter(line -> (line != null && !line.isBlank() && !line.isEmpty()))
                    .filter(line -> line.contains(" "))
                    .map(line -> {
                        DecipherAgent agent = new DecipherAgent();
                        String[] words = line.split(" ");
                        return agent.decipherMessage(Arrays.asList(words));
                    }).collect(Collectors.toList());

            kingShansFate(messages);
        } catch (IOException e) {
            System.out.println(" Specified file " + inputFile + "  not found. Supply valid file");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ThronesConstants.NONE);
        }
    }

    /**
     * API filters all the Kingdoms
     * @param messages
     */
    private static void kingShansFate(Collection<String> messages) {
        Set kingdoms = messages
                .stream()
                .filter(value -> value != ThronesConstants.NONE)
                .collect(Collectors.toSet());

        if(kingdoms.size() >= ThronesConstants.MINIMUM_REQUIRED_KINGDOMS) {
            System.out.print("SPACE ");
            kingdoms.forEach(
                    kingdom-> System.out.print(kingdom+" ")
            );
        }
        else
            System.out.println(ThronesConstants.NONE);
    }
}
