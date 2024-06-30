package ua.vkireiev.portaone.util;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.util.Arrays;

import ua.vkireiev.portaone.model.LongNumber;

public class ConstantsTest {

    private static Map<String, LongNumber> LongNumberTestMap = new HashMap<>();
    private static Map<String, Map<String, Object>> statisticalMetricsTestMap = new HashMap<>();

    static {
        LongNumber longNumber;

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 0);
        LongNumberTestMap.put("0", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 1);
        LongNumberTestMap.put("1", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 1);
        LongNumberTestMap.put("-1", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 2);
        LongNumberTestMap.put("2", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 2);
        LongNumberTestMap.put("-2", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 123);
        LongNumberTestMap.put("123", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 123);
        LongNumberTestMap.put("-123", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 1000);
        LongNumberTestMap.put("1000", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 1000);
        LongNumberTestMap.put("-1000", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 4445);
        LongNumberTestMap.put("-4445", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 9999);
        LongNumberTestMap.put("9999", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 9999);
        LongNumberTestMap.put("-9999", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 0);
        longNumber.setSegment(1, 1);
        LongNumberTestMap.put("10000", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 0);
        longNumber.setSegment(1, 1);
        LongNumberTestMap.put("-10000", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 0);
        longNumber.setSegment(1, 2300);
        longNumber.setSegment(2, 1);
        LongNumberTestMap.put("123000000", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 0);
        longNumber.setSegment(1, 2300);
        longNumber.setSegment(2, 1);
        LongNumberTestMap.put("-123000000", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 2345);
        longNumber.setSegment(1, 1);
        LongNumberTestMap.put("12345", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 2345);
        longNumber.setSegment(1, 1);
        LongNumberTestMap.put("-12345", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 0);
        longNumber.setSegment(1, 7);
        longNumber.setSegment(2, 56);
        longNumber.setSegment(3, 34);
        longNumber.setSegment(4, 12);
        LongNumberTestMap.put("120034005600070000", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 0);
        longNumber.setSegment(1, 7);
        longNumber.setSegment(2, 56);
        longNumber.setSegment(3, 34);
        longNumber.setSegment(4, 12);
        LongNumberTestMap.put("-120034005600070000", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 1);
        longNumber.setSegment(1, 22);
        longNumber.setSegment(2, 333);
        longNumber.setSegment(3, 4444);
        longNumber.setSegment(4, 5);
        LongNumberTestMap.put("54444033300220001", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 5555);
        longNumber.setSegment(1, 444);
        longNumber.setSegment(2, 33);
        longNumber.setSegment(3, 2);
        longNumber.setSegment(4, 111);
        LongNumberTestMap.put("-1110002003304445555", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 5554);
        LongNumberTestMap.put("5554", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 5554);
        LongNumberTestMap.put("-5554", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 5555);
        LongNumberTestMap.put("5555", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 5555);
        LongNumberTestMap.put("-5555", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 9999);
        longNumber.setSegment(1, 999);
        longNumber.setSegment(2, 99);
        longNumber.setSegment(3, 9);
        LongNumberTestMap.put("9009909999999", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 9999);
        longNumber.setSegment(1, 999);
        longNumber.setSegment(2, 99);
        longNumber.setSegment(3, 9);
        LongNumberTestMap.put("-9009909999999", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 5556);
        LongNumberTestMap.put("5556", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 5556);
        LongNumberTestMap.put("-5556", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 5554);
        longNumber.setSegment(1, 444);
        longNumber.setSegment(2, 33);
        longNumber.setSegment(3, 2);
        longNumber.setSegment(4, 1);
        LongNumberTestMap.put("10002003304445554", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 5554);
        longNumber.setSegment(1, 444);
        longNumber.setSegment(2, 33);
        longNumber.setSegment(3, 2);
        longNumber.setSegment(4, 1);
        LongNumberTestMap.put("-10002003304445554", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 5555);
        longNumber.setSegment(1, 444);
        longNumber.setSegment(2, 33);
        longNumber.setSegment(3, 2);
        longNumber.setSegment(4, 1);
        LongNumberTestMap.put("10002003304445555", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 5555);
        longNumber.setSegment(1, 444);
        longNumber.setSegment(2, 33);
        longNumber.setSegment(3, 2);
        longNumber.setSegment(4, 1);
        LongNumberTestMap.put("-10002003304445555", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 5556);
        longNumber.setSegment(1, 9444);
        longNumber.setSegment(2, 9933);
        longNumber.setSegment(3, 9992);
        LongNumberTestMap.put("9992993394445556", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 5556);
        longNumber.setSegment(1, 9444);
        longNumber.setSegment(2, 9933);
        longNumber.setSegment(3, 9992);
        LongNumberTestMap.put("-9992993394445556", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 9999);
        longNumber.setSegment(1, 8699);
        longNumber.setSegment(2, 97);
        longNumber.setSegment(3, 9);
        LongNumberTestMap.put("9009786999999", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 9999);
        longNumber.setSegment(1, 8699);
        longNumber.setSegment(2, 97);
        longNumber.setSegment(3, 9);
        LongNumberTestMap.put("-9009786999999", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 1109);
        longNumber.setSegment(1, 889);
        longNumber.setSegment(2, 66);
        longNumber.setSegment(3, 4);
        longNumber.setSegment(4, 2);
        LongNumberTestMap.put("20004006608891109", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 1109);
        longNumber.setSegment(1, 889);
        longNumber.setSegment(2, 66);
        longNumber.setSegment(3, 4);
        longNumber.setSegment(4, 2);
        LongNumberTestMap.put("-20004006608891109", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(false);
        longNumber.setSegment(0, 3647);
        longNumber.setSegment(1, 4748);
        longNumber.setSegment(2, 21);
        LongNumberTestMap.put("2147483647", longNumber);

        //
        longNumber = new LongNumber();
        longNumber.setNegative(true);
        longNumber.setSegment(0, 3648);
        longNumber.setSegment(1, 4748);
        longNumber.setSegment(2, 21);
        LongNumberTestMap.put("-2147483648", longNumber);

        //
        String key = "19, 8, 14, 1";
        statisticalMetricsTestMap.put(key,
                Map.of(
                        "elements", Arrays.array(19, 8, 14, 1),
                        "min", "1",
                        "max", "19",
                        "median", "11.0000",
                        "avg", "10.5000"));

        //
        key = "2, 6, 4, 3, 4, 4, 7, 7, 6, 8, 6, 10, 15, 6";
        statisticalMetricsTestMap.put(key,
                Map.of(
                        "elements", Arrays.array(2, 6, 4, 3, 4, 4, 7, 7, 6, 8, 6, 10, 15, 6),
                        "min", "2",
                        "max", "15",
                        "median", "6.0000",
                        "avg", "6.2857"));

        key = "5, 6, 1, 2, 3, 0, 1, 8";
        statisticalMetricsTestMap.put(key,
                Map.of(
                        "elements", Arrays.array(5, 6, 1, 2, 3, 0, 1, 8),
                        "min", "0",
                        "max", "8",
                        "median", "2.5000",
                        "avg", "3.2500"));

        key = "5, 9, 1, 4, 5, −2, 0";
        statisticalMetricsTestMap.put(key,
                Map.of(
                        "elements", Arrays.array(5, 9, 1, 4, 5, -2, 0),
                        "min", "-2",
                        "max", "9",
                        "median", "4.0000",
                        "avg", "3.1429"));

        key = "7, 4, 2, 3, 6, 1";
        statisticalMetricsTestMap.put(key,
                Map.of(
                        "elements", Arrays.array(7, 4, 2, 3, 6, 1),
                        "min", "1",
                        "max", "7",
                        "median", "3.5000",
                        "avg", "3.8333"));

        key = "-1, 0, 1";
        statisticalMetricsTestMap.put(key,
                Map.of(
                        "elements", Arrays.array(-1, 0, 1),
                        "min", "-1",
                        "max", "1",
                        "median", "0.0000",
                        "avg", "0.0000"));

        key = "-1, 1";
        statisticalMetricsTestMap.put(key,
                Map.of(
                        "elements", Arrays.array(-1, 1),
                        "min", "-1",
                        "max", "1",
                        "median", "0.0000",
                        "avg", "0.0000"));

        key = "0";
        statisticalMetricsTestMap.put(key,
                Map.of(
                        "elements", Arrays.array(0),
                        "min", "0",
                        "max", "0",
                        "median", "0.0000",
                        "avg", "0.0000"));
    }

    private ConstantsTest() {
    }

    public static LongNumber getTestLongNumber(String key) {

        LongNumber longNumber = LongNumberTestMap.get(key);
        if (longNumber != null) {
            LongNumber result = new LongNumber();
            result.setNegative(longNumber.isNegative());
            result.setSegments(longNumber.getSegments());

            return result;
        }

        throw new NumberFormatException("TestLongNumber values with key = '" + key + "' not found");
    }

    public static Map<String, Object> getTestStatisticalMetrics(String key) {

        Map<String, Object> result = statisticalMetricsTestMap.get(key);
        if (result != null) {
            return new HashMap<>(result);
        }

        throw new NumberFormatException("TestLongNumber values with key = '" + key + "' not found");
    }

}
