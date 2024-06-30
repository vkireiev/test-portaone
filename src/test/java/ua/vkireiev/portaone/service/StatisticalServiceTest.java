package ua.vkireiev.portaone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ua.vkireiev.portaone.util.ConstantsTest;

class StatisticalServiceTest {

    @Test
    void calculateStatisticalMetrics_WhenCallWithEmptyInputData_ThenException() {

        StatisticalService testService = new StatisticalService();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> testService.calculateStatisticalMetrics(new int[0]));
        assertThat(exception.getMessage())
                .containsIgnoringCase("The input array cannot be empty");
    }

    @ParameterizedTest
    @MethodSource("provideValuesAndResultsForStatisticalServiceMethods")
    void calculateStatisticalMetrics_CallWithValidInputData(
            Map<String, Object> parameterizedTestData) {

        StatisticalService testService = new StatisticalService();
        testService.calculateStatisticalMetrics(Arrays.stream((Integer[]) parameterizedTestData.get("elements"))
                .mapToInt(Integer::intValue)
                .toArray());

        assertEquals(Integer.valueOf((String) parameterizedTestData.get("min")), testService.getMinValue());
        assertEquals(Integer.valueOf((String) parameterizedTestData.get("max")), testService.getMaxValue());
        assertEquals(((String) parameterizedTestData.get("median")), String.format("%.4f", testService.getMedian()));
        assertEquals(((String) parameterizedTestData.get("avg")), String.format("%.4f", testService.getAvgValue()));
    }

    private static Stream<Arguments> provideValuesAndResultsForStatisticalServiceMethods() {
        return Stream.of(
                Arguments.of(ConstantsTest.getTestStatisticalMetrics("0")),
                Arguments.of(ConstantsTest.getTestStatisticalMetrics("-1, 1")),
                Arguments.of(ConstantsTest.getTestStatisticalMetrics("-1, 0, 1")),
                Arguments.of(ConstantsTest.getTestStatisticalMetrics("5, 9, 1, 4, 5, −2, 0")),
                Arguments.of(ConstantsTest.getTestStatisticalMetrics("7, 4, 2, 3, 6, 1")),
                Arguments.of(ConstantsTest.getTestStatisticalMetrics("5, 6, 1, 2, 3, 0, 1, 8")),
                Arguments.of(ConstantsTest.getTestStatisticalMetrics("2, 6, 4, 3, 4, 4, 7, 7, 6, 8, 6, 10, 15, 6")),
                Arguments.of(ConstantsTest.getTestStatisticalMetrics("19, 8, 14, 1")));
    }

}
