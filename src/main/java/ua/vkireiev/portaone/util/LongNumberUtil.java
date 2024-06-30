package ua.vkireiev.portaone.util;

import ua.vkireiev.portaone.model.LongNumber;

public class LongNumberUtil {

    public static final int DIGIT_BASE = 10000;
    public static final int DIGIT_BASE_LENGTH = 4;
    public static final int DIGIT_BASE_WEIGHT = 10;

    private LongNumberUtil() {
    }

    public static LongNumber valueOf(int i) {

        LongNumber result = new LongNumber();
        if (i < 0) {
            result.setNegative(true);
        }

        int value = i;
        int index = 0;
        while (Math.abs(value / DIGIT_BASE) > 0) {
            result.setSegment(index++, Math.abs(value % DIGIT_BASE));
            value = value / DIGIT_BASE;
        }
        result.setSegment(index, value);

        return result;
    }

    public static LongNumber valueOf(String s) throws IllegalArgumentException, NumberFormatException {

        if (s == null) {
            throw new IllegalArgumentException("Cannot parse null string");
        }

        int length = s.length();
        if (length == 0) {
            throw new NumberFormatException("Cannot parse empty string");
        }

        LongNumber result = new LongNumber();
        int firstPosition = 0;
        char firstChar = s.charAt(0);
        if (firstChar < '0' || firstChar > '9') {
            if (firstChar == '-') {
                result.setNegative(true);
            } else if (firstChar != '+') {
                throw new NumberFormatException("Cannot parse input string. First char must be '-'/'+' or a number");
            }

            if (length == 1) {
                throw new NumberFormatException("Cannot parse input string. Cannot have lone '+' or '-'");
            }
            firstPosition++;
        }

        int i = s.length() - 1;
        StringBuilder currentSegment = new StringBuilder();
        int segmentsCount = 0;
        while (firstPosition <= i) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                throw new NumberFormatException(
                        "Cannot parse input string. Error at index " + i + " with symbol '" + c + "'");
            }
            currentSegment.insert(0, c);
            if (currentSegment.length() >= DIGIT_BASE_LENGTH) {
                result.setSegment(segmentsCount++, convertToInteger(currentSegment));
                currentSegment = new StringBuilder();
            }
            i--;
        }
        if (currentSegment.length() > 0) {
            result.setSegment(segmentsCount, convertToInteger(currentSegment));
        }

        return normalize(result);
    }

    public static String toString(LongNumber n) throws IllegalArgumentException {

        if (n == null || n.getSegments() == null) {
            throw new IllegalArgumentException("Cannot convert null to string");
        }

        if (n.getSegments().isEmpty()) {
            return "0";
        }

        LongNumber longNumber = normalize(n);
        StringBuilder result = new StringBuilder();
        int maxSegmentIndex = longNumber.getSegments().keySet().stream()
                .max(Comparable::compareTo)
                .orElse(0);
        for (int i = 0; i < maxSegmentIndex; i++) {
            result.insert(0, String.format("%0" + DIGIT_BASE_LENGTH + "d", longNumber.getSegment(i)));
        }
        result.insert(0, String.valueOf(longNumber.getSegment(maxSegmentIndex)));

        if (longNumber.isNegative()) {
            result.insert(0, '-');
        }

        return result.toString();
    }

    public static int absCompareTo(LongNumber o1, LongNumber o2) throws IllegalArgumentException {

        if (o1 == null
                || o2 == null
                || o1.getSegments() == null
                || o2.getSegments() == null) {
            throw new IllegalArgumentException("Cannot compare null value");
        }

        int i = Integer.max(o1.getMaxSegmentIndex(), o2.getMaxSegmentIndex());
        while (i >= 0) {
            int x = o1.getSegment(i);
            int y = o2.getSegment(i);
            if (x < y) {
                return -1;
            }
            if (x > y) {
                return 1;
            }
            i--;
        }

        return 0;
    }

    public static int compareTo(LongNumber o1, LongNumber o2) throws IllegalArgumentException {

        if (o1 == null
                || o2 == null
                || o1.getSegments() == null
                || o2.getSegments() == null) {
            throw new IllegalArgumentException("Cannot compare null value");
        }

        int maxSegmentIndex = Integer.max(o1.getMaxSegmentIndex(), o2.getMaxSegmentIndex());
        if (maxSegmentIndex == 0 && o1.getSegment(0) == 0 && o2.getSegment(0) == 0) {
            return 0;
        }

        if (o1.isNegative() != o2.isNegative()) {
            return o1.isNegative() ? -1 : 1;
        }

        return o1.isNegative() ? (-1 * absCompareTo(o1, o2)) : absCompareTo(o1, o2);
    }

    public static void add(LongNumber term1, LongNumber term2) throws IllegalArgumentException {

        if (term1 == null
                || term2 == null
                || term1.getSegments() == null
                || term2.getSegments() == null) {
            throw new IllegalArgumentException("Cannot make addition with null value");
        }

        if (term1.isNegative() == term2.isNegative()) {
            // "+a" + "+b" or "-a" + "-b"
            LongNumber a = new LongNumber(term1);
            a.setNegative(false);
            LongNumber b = new LongNumber(term2);
            a.setNegative(false);
            makeSimpleAddition(a, b);
            a.setNegative(term1.isNegative());
            term1.copy(a);
        } else {
            boolean isResultNegative;
            LongNumber a;
            LongNumber b;
            if (absCompareTo(term1, term2) > 0) {
                isResultNegative = term1.isNegative();
                a = new LongNumber(term1);
                a.setNegative(false);
                b = new LongNumber(term2);
                a.setNegative(false);
            } else {
                isResultNegative = term2.isNegative();
                a = new LongNumber(term2);
                a.setNegative(false);
                b = new LongNumber(term1);
                a.setNegative(false);
            }
            makeSimpleSubtraction(a, b);
            a.setNegative(isResultNegative);
            term1.copy(a);
        }

        normalize(term1);
    }

    public static void subtract(LongNumber reduced, LongNumber subtractor) {

        if (reduced == null
                || subtractor == null
                || reduced.getSegments() == null
                || subtractor.getSegments() == null) {
            throw new IllegalArgumentException("Cannot make subtraction with null value");
        }

        if (subtractor.isNegative()) {
            // "?a" - "-b" => "?a" + "+b"
            LongNumber a = new LongNumber(reduced);
            LongNumber b = new LongNumber(subtractor);
            b.setNegative(false);
            add(a, b);
            reduced.copy(a);
        } else {
            boolean isResultNegative;
            LongNumber a;
            LongNumber b;
            if (absCompareTo(reduced, subtractor) > 0) {
                isResultNegative = reduced.isNegative();
                a = new LongNumber(reduced);
                a.setNegative(false);
                b = new LongNumber(subtractor);
                b.setNegative(false);
                makeSimpleSubtraction(a, b);
                a.setNegative(isResultNegative);
            } else {
                if (reduced.isNegative()) {
                    a = new LongNumber(reduced);
                    a.setNegative(false);
                    b = new LongNumber(subtractor);
                    a.setNegative(false);
                    add(a, b);
                    a.setNegative(true);
                } else {
                    a = new LongNumber(subtractor);
                    a.setNegative(false);
                    b = new LongNumber(reduced);
                    b.setNegative(false);
                    makeSimpleSubtraction(a, b);
                    a.setNegative(true);
                }
            }

            reduced.copy(a);
        }

        normalize(reduced);
    }

    private static LongNumber normalize(LongNumber n) {

        int maxSegmentIndex = n.getMaxSegmentIndex();
        int i = maxSegmentIndex;
        while (i > 0 && n.getSegment(i) == 0) {
            n.removeSegment(i);
            i--;
        }

        maxSegmentIndex = n.getMaxSegmentIndex();
        if (maxSegmentIndex == 0 && n.getSegment(0) == 0) {
            n.setNegative(false);
        }

        return n;
    }

    private static Integer convertToInteger(StringBuilder s) throws NumberFormatException {

        int result = 0;
        int i = s.length();
        int currentDigitBase = 1;
        while ((i > 0) && ((s.length() - i) < DIGIT_BASE_LENGTH)) {
            result += (s.charAt(i - 1) - 48) * currentDigitBase;
            currentDigitBase *= DIGIT_BASE_WEIGHT;
            i--;
        }

        return result;
    }

    private static void makeSimpleSubtraction(LongNumber reduced, LongNumber subtractor) {

        int maxSegmentIndex = reduced.getMaxSegmentIndex() > subtractor.getMaxSegmentIndex()
                ? reduced.getMaxSegmentIndex()
                : subtractor.getMaxSegmentIndex();

        for (int i = 0; i <= maxSegmentIndex; i++) {
            calculateSegmentInSubtraction(reduced, i, reduced.getSegment(i), subtractor.getSegment(i));
        }
    }

    private static void makeSimpleAddition(LongNumber a, LongNumber b) {

        int maxSegmentIndex = a.getMaxSegmentIndex() > b.getMaxSegmentIndex()
                ? a.getMaxSegmentIndex()
                : b.getMaxSegmentIndex();

        for (int i = 0; i <= maxSegmentIndex; i++) {
            calculateSegmentInAddition(a, i, a.getSegment(i), b.getSegment(i));
        }
    }

    private static void calculateSegmentInSubtraction(LongNumber n, int index, int a, int b) {

        if (a < b) {
            calculateSegmentInSubtraction(n, (index + 1), n.getSegment(index + 1), 1);
            n.setSegment(index, ((a + DIGIT_BASE) - b));
        } else {
            n.setSegment(index, (a - b));
        }
    }

    private static void calculateSegmentInAddition(LongNumber n, int index, int a, int b) {

        int result = a + b;
        if (result >= DIGIT_BASE) {
            calculateSegmentInAddition(n, (index + 1), n.getSegment(index + 1), (result / DIGIT_BASE));
            n.setSegment(index, (result % DIGIT_BASE));
        } else {
            n.setSegment(index, result);
        }
    }

}
