package ua.vkireiev.portaone.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ua.vkireiev.portaone.service.FileService;

class FileServiceTest {

    @Test
    void readFile_WhenCallWithNotExistFile_ThenException() {

        String filePath = "not_exist_file.txt";
        FileService testService = new FileService();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> testService.readFile(filePath));
        assertThat(exception.getMessage())
                .containsIgnoringCase("Can't read file: " + filePath);
    }

    @Test
    void readFile_WhenCallWithExistFileAndEmptyContent_ThenReturnEmptyList() {

        FileService testService = new FileService();
        assertTrue(testService.readFile("src/test/resources/input3.txt").isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideExistFileWithNonEmptyContentForReadFileMethod")
    void readFile_WhenCallWithExistFileAndNonEmptyContent_ThenReturnNotEmptyList(
            String parameterizedFilePath, List<String> parameterizedExpectedResult) {

        FileService testService = new FileService();

        List<String> returnedResult = testService.readFile(parameterizedFilePath);

        assertThat(returnedResult)
                .isNotEmpty()
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    private static Stream<Arguments> provideExistFileWithNonEmptyContentForReadFileMethod() {
        return Stream.of(
                Arguments.of("src/test/resources/input5.txt",
                        new LinkedList<String>(List.of("0"))),
                Arguments.of("src/test/resources/input4.txt",
                        new LinkedList<String>(List.of("19", "8", "14", "1"))),
                Arguments.of("src/test/resources/input2.txt",
                        new LinkedList<String>(List.of("-1", "0", "1"))),
                Arguments.of("src/test/resources/input1.txt",
                        new LinkedList<String>(List.of("2", "6", "4", "3", "4",
                                "4", "7", "7", "6", "8", "6", "10", "15", "6"))));
    }

}
