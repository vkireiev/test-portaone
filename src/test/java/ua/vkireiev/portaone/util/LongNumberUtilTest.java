package ua.vkireiev.portaone.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ua.vkireiev.portaone.model.LongNumber;

class LongNumberUtilTest {

    @ParameterizedTest
    @MethodSource("providePositiveIntForValueOfMethod")
    void valueOf_CallWithPositiveInteger(
            int parameterizedInputInteger, LongNumber parameterizedExpectedResult) {

        assertTrue(parameterizedInputInteger >= 0);

        LongNumber returnedResult = LongNumberUtil.valueOf(parameterizedInputInteger);

        assertThat(returnedResult)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideNegativeIntForValueOfMethod")
    void valueOf_CallWithNegativeInteger(
            int parameterizedInputInteger, LongNumber parameterizedExpectedResult) {

        assertTrue(parameterizedInputInteger < 0);

        LongNumber returnedResult = LongNumberUtil.valueOf(parameterizedInputInteger);

        assertTrue(returnedResult.isNegative());
        assertThat(returnedResult)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @Test
    void valueOf_WhenCallWithNull_ThenException() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> LongNumberUtil.valueOf(null));
        assertThat(exception.getMessage())
                .containsIgnoringCase("Cannot parse null string");
    }

    @Test
    void valueOf_WhenCallWithEmptyString_ThenException() {

        NumberFormatException exception = assertThrows(
                NumberFormatException.class,
                () -> LongNumberUtil.valueOf(""));
        assertThat(exception.getMessage())
                .containsIgnoringCase("Cannot parse empty string");
    }

    @ParameterizedTest
    @MethodSource("provideStringWithInvalidFirstCharacter")
    void valueOf_WhenCallWithInvalidFirstCharacter_ThenException(
            String parameterizedInputString) {
        final String expectedExceptionMessage = "Cannot parse input string. First char must be '-'/'+' or a number";

        NumberFormatException exception = assertThrows(
                NumberFormatException.class,
                () -> LongNumberUtil.valueOf(parameterizedInputString));
        assertThat(exception.getMessage())
                .containsIgnoringCase(expectedExceptionMessage);
    }

    @Test
    void valueOf_WhenCallWithLoneCharacterPlusOrMinus_ThenException() {
        final String expectedExceptionMessage = "Cannot parse input string. Cannot have lone '+' or '-'";

        NumberFormatException exception = assertThrows(
                NumberFormatException.class,
                () -> LongNumberUtil.valueOf("+"));
        assertThat(exception.getMessage())
                .containsIgnoringCase(expectedExceptionMessage);

        exception = assertThrows(
                NumberFormatException.class,
                () -> LongNumberUtil.valueOf("-"));
        assertThat(exception.getMessage())
                .containsIgnoringCase(expectedExceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("provideStringConsistingNotNumbersOrPlusOrMinus")
    void valueOf_WhenCallWithStringConsistingNotNumbersOrPlusOrMinus_ThenException(
            String parameterizedInputString) {
        final String expectedExceptionMessage = "Cannot parse input string. Error at index";

        NumberFormatException exception = assertThrows(
                NumberFormatException.class,
                () -> LongNumberUtil.valueOf(parameterizedInputString));
        assertThat(exception.getMessage())
                .containsIgnoringCase(expectedExceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("provideStringBeginsWithZeros")
    void valueOf_WhenCallWithStringBeginsWithZeros_ThenReturnLongNumberWithTrimmedZeros(
            String parameterizedInputString, LongNumber parameterizedExpectedResult) {

        LongNumber returnedResult = LongNumberUtil.valueOf(parameterizedInputString);

        assertThat(returnedResult)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideStringEndsWithZeros")
    void valueOf_WhenCallWithStringEndsWithZeros_ThenReturnLongNumber(
            String parameterizedInputString, LongNumber parameterizedExpectedResult) {

        LongNumber returnedResult = LongNumberUtil.valueOf(parameterizedInputString);

        assertThat(returnedResult)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideValidString")
    void valueOf_WhenCallWithValidString_ThenReturnLongNumber(
            String parameterizedInputString, LongNumber parameterizedExpectedResult) {

        LongNumber returnedResult = LongNumberUtil.valueOf(parameterizedInputString);

        assertThat(returnedResult)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @Test
    void toString_WhenCallWithNullAsLongNumber_ThenException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> LongNumberUtil.toString(null));
        assertThat(exception.getMessage())
                .containsIgnoringCase("Cannot convert null to string");
    }

    @Test
    void toString_WhenCallWithEmptySegments_ThenReturnZero() {
        LongNumber longNumber = new LongNumber();
        Set<Integer> keySet = longNumber.getSegments().keySet();
        for (Integer index : keySet) {
            longNumber.removeSegment(index);
        }
        assertThat(longNumber.getSegments())
                .isEmpty();

        assertEquals("0", LongNumberUtil.toString(longNumber));
    }

    @Test
    void toString_WhenCallWithNotNormalizedSegments_ThenReturnTrimedResultString() {
        LongNumber longNumber = new LongNumber();
        longNumber.setSegment(0, 1);
        longNumber.setSegment(1, 2);
        longNumber.setSegment(2, 0);
        longNumber.setSegment(3, 0);
        longNumber.setSegment(4, 0);
        longNumber.setNegative(false);

        assertEquals("20001", LongNumberUtil.toString(longNumber));
    }

    @ParameterizedTest
    @MethodSource("provideNotNormalizedSegments")
    void toString_WhenCallWithNotNormalizedSegments_ThenReturnResultString(
            LongNumber parameterizedLongNumber, String parameterizedExpectedResult) {

        assertEquals(parameterizedExpectedResult, LongNumberUtil.toString(parameterizedLongNumber));
    }

    @ParameterizedTest
    @MethodSource("provideNotValidSegments")
    void toString_WhenCallWithNotValidSegments_ThenReturnResultString(
            LongNumber parameterizedLongNumber, String parameterizedExpectedResult) {

        assertEquals(parameterizedExpectedResult, LongNumberUtil.toString(parameterizedLongNumber));
    }

    @ParameterizedTest
    @MethodSource("provideValidSegments")
    void toString_WhenCallWithValidSegments_ThenReturnResultString(
            LongNumber parameterizedLongNumber, String parameterizedExpectedResult) {

        assertEquals(parameterizedExpectedResult, LongNumberUtil.toString(parameterizedLongNumber));
    }

    @ParameterizedTest
    @MethodSource("provideNullArguments")
    void absCompareTo_WhenCallWithNull_ThenException(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {
        final String expectedExceptionMessage = "Cannot compare null value";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> LongNumberUtil.absCompareTo(parameterizedLongNumber1, parameterizedLongNumber2));
        assertThat(exception.getMessage())
                .containsIgnoringCase(expectedExceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("provideFirstSmallerForAbsCompareToMethod")
    void absCompareTo_WhenCallWithFirstSmaller_ThenReturnMinusOne(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(-1, LongNumberUtil.absCompareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideFirstBiggerForAbsCompareToMethod")
    void absCompareTo_WhenCallWithFirstBigger_ThenReturnOne(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(1, LongNumberUtil.absCompareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideEqualForAbsCompareToMethod")
    void absCompareTo_WhenCallWithEqual_ThenReturnZero(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(0, LongNumberUtil.absCompareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideNullArguments")
    void compareTo_WhenCallWithNull_ThenException(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {
        final String expectedExceptionMessage = "Cannot compare null value";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));
        assertThat(exception.getMessage())
                .containsIgnoringCase(expectedExceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("provideBothPositiveAndFirstSmallerForCompareToMethod")
    void compareTo_WhenCallWithBothPositiveAndFirstSmaller_ThenReturnMinusOne(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(-1, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideBothPositiveAndFirstBiggerForCompareToMethod")
    void compareTo_WhenCallWithBothPositiveAndFirstBigger_ThenReturnOne(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(1, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideFirstNegativeAndSecondPositiveForCompareToMethod")
    void compareTo_WhenCallWithFirstNegativeAndSecondPositive_ThenReturnMinusOne(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(-1, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideFirstPositiveAndSecondNegativeForCompareToMethod")
    void compareTo_WhenCallWithFirstPositiveAndSecondNegative_ThenReturnOne(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(1, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideBothNegativeAndFirstSmallerForCompareToMethod")
    void compareTo_WhenCallWithBothNegativeAndFirstSmaller_ThenReturnMinusOne(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(-1, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideBothNegativeAndFirstBiggerForCompareToMethod")
    void compareTo_WhenCallWithBothNegativeAndFirstBigger_ThenReturnOne(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(1, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideEqualForCompareToMethod")
    void compareTo_WhenCallWithEqual_ThenReturnZero(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {

        assertEquals(0, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));
    }

    @ParameterizedTest
    @MethodSource("provideNullArguments")
    void add_WhenCallWithNull_ThenException(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {
        final String expectedExceptionMessage = "Cannot make addition with null value";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> LongNumberUtil.add(parameterizedLongNumber1, parameterizedLongNumber2));
        assertThat(exception.getMessage())
                .containsIgnoringCase(expectedExceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("provideBothPositiveForAddMethod")
    void add_WhenCallWithBothPositive_ThenReturnPositive(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertFalse(parameterizedLongNumber1.isNegative());
        assertFalse(parameterizedLongNumber2.isNegative());

        LongNumberUtil.add(parameterizedLongNumber1, parameterizedLongNumber2);

        assertFalse(parameterizedLongNumber1.isNegative());
        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideBothNegativeForAddMethod")
    void add_WhenCallWithBothNegative_ThenReturnNegative(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertTrue(parameterizedLongNumber1.isNegative());
        assertTrue(parameterizedLongNumber2.isNegative());

        LongNumberUtil.add(parameterizedLongNumber1, parameterizedLongNumber2);

        assertTrue(parameterizedLongNumber1.isNegative());
        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideFirstPositiveAndSecondNegativeForAddMethod")
    void add_CallWithFirstPositiveAndSecondNegative(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertFalse(parameterizedLongNumber1.isNegative());
        assertTrue(parameterizedLongNumber2.isNegative());

        LongNumberUtil.add(parameterizedLongNumber1, parameterizedLongNumber2);

        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideFirstNegativeAndSecondPositiveForAddMethod")
    void add_CallWithFirstNegativeAndSecondPositive(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertTrue(parameterizedLongNumber1.isNegative());
        assertFalse(parameterizedLongNumber2.isNegative());

        LongNumberUtil.add(parameterizedLongNumber1, parameterizedLongNumber2);

        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideNullArguments")
    void subtract_WhenCallWithNull_ThenException(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2) {
        final String expectedExceptionMessage = "Cannot make subtraction with null value";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> LongNumberUtil.subtract(parameterizedLongNumber1, parameterizedLongNumber2));
        assertThat(exception.getMessage())
                .containsIgnoringCase(expectedExceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("provideBothPositiveAndEqualForSubtractMethod")
    void subtract_CallWithBothPositiveAndEqual(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertFalse(parameterizedLongNumber1.isNegative());
        assertFalse(parameterizedLongNumber2.isNegative());

        LongNumberUtil.subtract(parameterizedLongNumber1, parameterizedLongNumber2);

        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideBothPositiveAndFirstBiggerForSubtractMethod")
    void subtract_CallWithBothPositiveAndFirstBigger(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertFalse(parameterizedLongNumber1.isNegative());
        assertFalse(parameterizedLongNumber2.isNegative());
        assertEquals(1, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));

        LongNumberUtil.subtract(parameterizedLongNumber1, parameterizedLongNumber2);

        assertFalse(parameterizedLongNumber1.isNegative());
        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideBothPositiveAndFirstSmallerForSubtractMethod")
    void subtract_CallWithBothPositiveAndFirstSmaller(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertFalse(parameterizedLongNumber1.isNegative());
        assertFalse(parameterizedLongNumber2.isNegative());
        assertEquals(-1, LongNumberUtil.compareTo(parameterizedLongNumber1, parameterizedLongNumber2));

        LongNumberUtil.subtract(parameterizedLongNumber1, parameterizedLongNumber2);

        assertTrue(parameterizedLongNumber1.isNegative());
        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideFirstPositiveAndSecondNegativeForSubtractMethod")
    void subtract_CallWithFirstPositiveAndSecondNegative(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertFalse(parameterizedLongNumber1.isNegative());
        assertTrue(parameterizedLongNumber2.isNegative());

        LongNumberUtil.subtract(parameterizedLongNumber1, parameterizedLongNumber2);

        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideFirstNegativeAndSecondPositiveForSubtractMethod")
    void subtract_CallWithFirstNegativeAndSecondPositive(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertTrue(parameterizedLongNumber1.isNegative());
        assertFalse(parameterizedLongNumber2.isNegative());

        LongNumberUtil.subtract(parameterizedLongNumber1, parameterizedLongNumber2);

        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideBothNegativeForSubtractMethod")
    void subtract_CallWithBothNegative(
            LongNumber parameterizedLongNumber1, LongNumber parameterizedLongNumber2,
            LongNumber parameterizedExpectedResult) {

        assertTrue(parameterizedLongNumber1.isNegative());
        assertTrue(parameterizedLongNumber2.isNegative());

        LongNumberUtil.subtract(parameterizedLongNumber1, parameterizedLongNumber2);

        assertThat(parameterizedLongNumber1)
                .usingRecursiveComparison()
                .isEqualTo(parameterizedExpectedResult);
    }

    private static Stream<Arguments> providePositiveIntForValueOfMethod() {
        return Stream.of(
                Arguments.of(1, ConstantsTest.getTestLongNumber("1")),
                Arguments.of(9999, ConstantsTest.getTestLongNumber("9999")),
                Arguments.of(10000, ConstantsTest.getTestLongNumber("10000")),
                Arguments.of(Integer.MAX_VALUE, ConstantsTest.getTestLongNumber(String.valueOf(Integer.MAX_VALUE))),
                Arguments.of(0, ConstantsTest.getTestLongNumber("0")));
    }

    private static Stream<Arguments> provideNegativeIntForValueOfMethod() {
        return Stream.of(
                Arguments.of(-1, ConstantsTest.getTestLongNumber("-1")),
                Arguments.of(-9999, ConstantsTest.getTestLongNumber("-9999")),
                Arguments.of(-10000, ConstantsTest.getTestLongNumber("-10000")),
                Arguments.of(Integer.MIN_VALUE, ConstantsTest.getTestLongNumber(String.valueOf(Integer.MIN_VALUE))));
    }

    private static Stream<Arguments> provideStringWithInvalidFirstCharacter() {
        return Stream.of(
                Arguments.of("$1234"),
                Arguments.of(".1234"),
                Arguments.of(" 1234"));
    }

    private static Stream<Arguments> provideStringConsistingNotNumbersOrPlusOrMinus() {
        return Stream.of(
                Arguments.of("12.345"),
                Arguments.of("-.12345"),
                Arguments.of("+.12345"),
                Arguments.of("12345 "),
                Arguments.of("1 2345"),
                Arguments.of("1-2345"),
                Arguments.of("1+2345"),
                Arguments.of("12345-"),
                Arguments.of("12345+"));
    }

    private static Stream<Arguments> provideStringBeginsWithZeros() {

        return Stream.of(
                Arguments.of("0", ConstantsTest.getTestLongNumber("0")),
                Arguments.of("+0", ConstantsTest.getTestLongNumber("0")),
                Arguments.of("-0", ConstantsTest.getTestLongNumber("0")),
                Arguments.of("001", ConstantsTest.getTestLongNumber("1")),
                Arguments.of("+001", ConstantsTest.getTestLongNumber("1")),
                Arguments.of("-001", ConstantsTest.getTestLongNumber("-1")),
                Arguments.of("000000123", ConstantsTest.getTestLongNumber("123")),
                Arguments.of("+000000123", ConstantsTest.getTestLongNumber("123")),
                Arguments.of("-000000123", ConstantsTest.getTestLongNumber("-123")));
    }

    private static Stream<Arguments> provideStringEndsWithZeros() {

        return Stream.of(
                Arguments.of("1000", ConstantsTest.getTestLongNumber("1000")),
                Arguments.of("+1000", ConstantsTest.getTestLongNumber("1000")),
                Arguments.of("-1000", ConstantsTest.getTestLongNumber("-1000")),
                Arguments.of("10000", ConstantsTest.getTestLongNumber("10000")),
                Arguments.of("+10000", ConstantsTest.getTestLongNumber("10000")),
                Arguments.of("-10000", ConstantsTest.getTestLongNumber("-10000")),
                Arguments.of("120034005600070000", ConstantsTest.getTestLongNumber("120034005600070000")),
                Arguments.of("+120034005600070000", ConstantsTest.getTestLongNumber("120034005600070000")),
                Arguments.of("-120034005600070000", ConstantsTest.getTestLongNumber("-120034005600070000")));
    }

    private static Stream<Arguments> provideValidString() {

        return Stream.of(
                Arguments.of("1", ConstantsTest.getTestLongNumber("1")),
                Arguments.of("-1", ConstantsTest.getTestLongNumber("-1")),
                Arguments.of("12345", ConstantsTest.getTestLongNumber("12345")),
                Arguments.of("-12345", ConstantsTest.getTestLongNumber("-12345")),
                Arguments.of("120034005600070000", ConstantsTest.getTestLongNumber("120034005600070000")),
                Arguments.of("-120034005600070000", ConstantsTest.getTestLongNumber("-120034005600070000")));
    }

    private static Stream<Arguments> provideNotNormalizedSegments() {

        return Stream.of(
                Arguments.of(new LongNumber(
                        false,
                        new HashMap<Integer, Integer>() {
                            {
                                put(4, 0);
                                put(0, 0);
                                put(1, 101);
                                put(2, 0);
                                put(3, 0);
                            }
                        }),
                        "1010000"),
                Arguments.of(new LongNumber(
                        true,
                        new HashMap<Integer, Integer>() {
                            {
                                put(4, 0);
                                put(0, 0);
                                put(1, 101);
                                put(2, 0);
                                put(3, 0);
                            }
                        }),
                        "-1010000"),
                Arguments.of(new LongNumber(
                        false,
                        new HashMap<Integer, Integer>() {
                            {
                                put(4, 0);
                                put(0, 0);
                                put(1, 0);
                                put(2, 0);
                                put(3, 0);
                            }
                        }),
                        "0"),
                Arguments.of(new LongNumber(
                        true,
                        new HashMap<Integer, Integer>() {
                            {
                                put(4, 0);
                                put(0, 0);
                                put(1, 0);
                                put(2, 0);
                                put(3, 0);
                            }
                        }),
                        "0"));
    }

    private static Stream<Arguments> provideNotValidSegments() {

        return Stream.of(
                Arguments.of(new LongNumber(
                        false,
                        new HashMap<Integer, Integer>() {
                            {
                                put(5, 5);
                                put(0, 0);
                                put(1, 101);
                                put(2, 2);
                                put(4, 4);
                            }
                        }),
                        "500040000000201010000"),
                Arguments.of(new LongNumber(
                        true,
                        new HashMap<Integer, Integer>() {
                            {
                                put(5, 5);
                                put(0, 0);
                                put(1, 101);
                                put(2, 2);
                                put(4, 4);
                            }
                        }),
                        "-500040000000201010000"));
    }

    private static Stream<Arguments> provideValidSegments() {

        return Stream.of(
                Arguments.of(ConstantsTest.getTestLongNumber("1"), "1"),
                Arguments.of(ConstantsTest.getTestLongNumber("-1"), "-1"),
                Arguments.of(ConstantsTest.getTestLongNumber("54444033300220001"), "54444033300220001"),
                Arguments.of(ConstantsTest.getTestLongNumber("-1110002003304445555"), "-1110002003304445555"));
    }

    private static Stream<Arguments> provideNullArguments() {

        LongNumber longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 9999);

        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(longNumber, null),
                Arguments.of(null, longNumber));
    }

    private static Stream<Arguments> provideFirstSmallerForAbsCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-5555"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-5555"),
                        ConstantsTest.getTestLongNumber("10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("5554"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("5554"),
                        ConstantsTest.getTestLongNumber("10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")));
    }

    private static Stream<Arguments> provideFirstBiggerForAbsCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("-5555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("-5555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("5554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("5554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("0")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-5556"),
                        ConstantsTest.getTestLongNumber("5555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("0")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("0")));
    }

    private static Stream<Arguments> provideEqualForAbsCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("5555"),
                        ConstantsTest.getTestLongNumber("-5555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1000"),
                        ConstantsTest.getTestLongNumber("1000")));
    }

    private static Stream<Arguments> provideBothPositiveAndFirstSmallerForCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("5555"),
                        ConstantsTest.getTestLongNumber("10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445554"),
                        ConstantsTest.getTestLongNumber("10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("5554"),
                        ConstantsTest.getTestLongNumber("5555")));
    }

    private static Stream<Arguments> provideBothPositiveAndFirstBiggerForCompareToMethod() {

        return Stream.of(

                Arguments.of(
                        ConstantsTest.getTestLongNumber("5556"),
                        ConstantsTest.getTestLongNumber("1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("10002003304445554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("0")));
    }

    private static Stream<Arguments> provideFirstNegativeAndSecondPositiveForCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445554"),
                        ConstantsTest.getTestLongNumber("10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("0")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10000"),
                        ConstantsTest.getTestLongNumber("1")));
    }

    private static Stream<Arguments> provideFirstPositiveAndSecondNegativeForCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("-1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")));
    }

    private static Stream<Arguments> provideBothNegativeAndFirstSmallerForCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10002003304445554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10000"),
                        ConstantsTest.getTestLongNumber("-1000")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10000")));
    }

    private static Stream<Arguments> provideBothNegativeAndFirstBiggerForCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445554"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1000"),
                        ConstantsTest.getTestLongNumber("-10000")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1000"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")));
    }

    private static Stream<Arguments> provideEqualForCompareToMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1000"),
                        ConstantsTest.getTestLongNumber("1000")));
    }

    private static Stream<Arguments> provideBothPositiveForAddMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("2")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("2"),
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("2")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("9999"),
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("10000")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445554"),
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("10002003304445555")));
    }

    private static Stream<Arguments> provideBothNegativeForAddMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("-2")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-9999"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("-10000")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445554"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")));
    }

    private static Stream<Arguments> provideFirstPositiveAndSecondNegativeForAddMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("-1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("-5555"),
                        ConstantsTest.getTestLongNumber("-5554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("5555"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("5554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10000"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("9999")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("-9009909999999"),
                        ConstantsTest.getTestLongNumber("9992993394445556")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")));
    }

    private static Stream<Arguments> provideFirstNegativeAndSecondPositiveForAddMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("5555"),
                        ConstantsTest.getTestLongNumber("5554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-5555"),
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("-5554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10000"),
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("-9999")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("10000"),
                        ConstantsTest.getTestLongNumber("9999")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("9009909999999"),
                        ConstantsTest.getTestLongNumber("-9992993394445556")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("-10002003304445554")));
    }

    private static Stream<Arguments> provideBothPositiveAndEqualForSubtractMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("0")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1000"),
                        ConstantsTest.getTestLongNumber("1000"),
                        ConstantsTest.getTestLongNumber("0")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("0")));
    }

    private static Stream<Arguments> provideBothPositiveAndFirstBiggerForSubtractMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("10002003304445554"),
                        ConstantsTest.getTestLongNumber("1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("10002003304445554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("9009909999999"),
                        ConstantsTest.getTestLongNumber("9992993394445556")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("9009909999999"),
                        ConstantsTest.getTestLongNumber("123000000"),
                        ConstantsTest.getTestLongNumber("9009786999999")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("10002003304445555")));
    }

    private static Stream<Arguments> provideBothPositiveAndFirstSmallerForSubtractMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("5555"),
                        ConstantsTest.getTestLongNumber("10000"),
                        ConstantsTest.getTestLongNumber("-4445")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445554"),
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("-1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10002003304445554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("123000000"),
                        ConstantsTest.getTestLongNumber("9009909999999"),
                        ConstantsTest.getTestLongNumber("-9009786999999")));
    }

    private static Stream<Arguments> provideFirstPositiveAndSecondNegativeForSubtractMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("5555"),
                        ConstantsTest.getTestLongNumber("-4445"),
                        ConstantsTest.getTestLongNumber("10000")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("10002003304445554"),
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("20004006608891109")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("-10002003304445554"),
                        ConstantsTest.getTestLongNumber("10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("10002003304445555")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("1")));
    }

    private static Stream<Arguments> provideFirstNegativeAndSecondPositiveForSubtractMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("9999"),
                        ConstantsTest.getTestLongNumber("-10000")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("1"),
                        ConstantsTest.getTestLongNumber("-10002003304445554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445554"),
                        ConstantsTest.getTestLongNumber("10002003304445555"),
                        ConstantsTest.getTestLongNumber("-20004006608891109")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445554"),
                        ConstantsTest.getTestLongNumber("0"),
                        ConstantsTest.getTestLongNumber("-10002003304445554")));
    }

    private static Stream<Arguments> provideBothNegativeForSubtractMethod() {

        return Stream.of(
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("-10000"),
                        ConstantsTest.getTestLongNumber("9999")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("-10002003304445554")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445554"),
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-10002003304445555"),
                        ConstantsTest.getTestLongNumber("-10002003304445554"),
                        ConstantsTest.getTestLongNumber("-1")),
                Arguments.of(
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("-1"),
                        ConstantsTest.getTestLongNumber("0")));
    }

}
