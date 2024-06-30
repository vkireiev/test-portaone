package ua.vkireiev.portaone.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InputDataConverterTest {

    @Test
    void convertToArray_WhenCallWithNullAsList_ThenException() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> InputDataConverter.convertToArray(null));
        assertThat(exception.getMessage())
                .containsIgnoringCase("Cannot convert null list");
    }

    @Test
    void convertToArray_WhenCallWithEmptyList_ThenReturnEmptyArray() {

        assertThat(InputDataConverter.convertToArray(List.of()))
                .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("provideNotEmptyListForConvertToArrayMethod")
    void convertToArray_WhenCallWithNotEmptyList_ThenReturnNotEmptyArray(
            List<String> parameterizedList, Integer[] parameterizedExpectedResult) {

        assertThat(InputDataConverter.convertToArray(parameterizedList))
                .isNotEmpty()
                .isEqualTo(Stream.of(parameterizedExpectedResult)
                        .mapToInt(Integer::intValue)
                        .toArray());
    }

    private static Stream<Arguments> provideNotEmptyListForConvertToArrayMethod() {
        return Stream.of(
                Arguments.of(
                        new LinkedList<String>(List.of("0")),
                        Arrays.array(0)),
                Arguments.of(
                        new LinkedList<String>(List.of("19", "8", "14", "1")),
                        Arrays.array(19, 8, 14, 1)),
                Arguments.of(
                        new LinkedList<String>(List.of("-1", "0", "1")),
                        Arrays.array(-1, 0, 1)),
                Arguments.of(
                        new LinkedList<String>(List.of("2", "6", "4", "3", "4",
                                "4", "7", "7", "6", "8", "6", "10", "15", "6")),
                        Arrays.array(2, 6, 4, 3, 4, 4, 7, 7, 6, 8, 6, 10, 15, 6)));
    }

}
