package ua.vkireiev.portaone.util;

import java.util.List;
import java.util.function.Predicate;

public class InputDataConverter {

    private InputDataConverter() {
    }

    public static int[] convertToArray(List<String> list) {

        if (list == null) {
            throw new IllegalArgumentException("Cannot convert null list");
        }

        return list.stream()
                .filter(Predicate.not(String::isBlank))
                .mapToInt(Integer::valueOf)
                .toArray();
    }

}
