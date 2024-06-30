package ua.vkireiev.portaone.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ua.vkireiev.portaone.model.LongNumber;
import ua.vkireiev.portaone.util.LongNumberUtil;

public class StatisticalService {

    private static final String EMPTY_INPUT_ARRAY_MESSAGE = "The input array cannot be empty";

    private int maxValue;
    private int minValue;
    private double median;
    private double avgValue;
    List<Integer> maxIncreasingSequence = new LinkedList<>();
    List<Integer> maxDescendingSequence = new LinkedList<>();

    public void calculateStatisticalMetrics(int[] array) throws IllegalArgumentException {

        if (array.length == 0) {
            throw new IllegalArgumentException(EMPTY_INPUT_ARRAY_MESSAGE);
        }

        findMaxIncreasingAndMaxDescendingSubsequences(array);

        List<Integer> sortedList = Arrays.stream(array).boxed().collect(Collectors.toList());
        sortedList.sort(Integer::compareTo);

        this.maxValue = sortedList.get(sortedList.size() - 1);
        this.minValue = sortedList.get(0);

        if ((array.length % 2) == 0) {
            LongNumber medianSumm = LongNumberUtil.valueOf(sortedList.get((array.length / 2)));
            LongNumberUtil.add(medianSumm, LongNumberUtil.valueOf(sortedList.get((array.length / 2) - 1)));

            this.median = Double.valueOf(LongNumberUtil.toString(medianSumm)) / 2;
        } else {
            this.median = sortedList.get((array.length / 2));
        }

        LongNumber summValue = new LongNumber();
        for (int i = 0; i < array.length; i++) {
            LongNumberUtil.add(summValue, LongNumberUtil.valueOf(array[i]));
        }

        this.avgValue = Double.valueOf(LongNumberUtil.toString(summValue)) / array.length;

        findMaxIncreasingAndMaxDescendingSubsequences(array);
    }

    public void printStatisticalMetrics() {

        System.out.println(this.maxValue);
        System.out.println(this.minValue);
        System.out.println(String.format("%.4f", this.median));
        System.out.println(String.format("%.4f", this.avgValue));
        System.out.println(this.maxIncreasingSequence.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
        System.out.println(this.maxDescendingSequence.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public double getMedian() {
        return median;
    }

    public double getAvgValue() {
        return avgValue;
    }

    public List<Integer> getMaxIncreasingSequence() {
        return new LinkedList<>(maxIncreasingSequence);
    }

    public List<Integer> getMaxDescendingSequence() {
        return new LinkedList<>(maxDescendingSequence);
    }

    private static List<Integer> initiateLinkedList(int element) {

        List<Integer> result = new LinkedList<>();
        result.add(element);

        return result;
    }

    private void findMaxIncreasingAndMaxDescendingSubsequences(int[] array) throws IllegalArgumentException {

        if (array.length == 0) {
            throw new IllegalArgumentException(EMPTY_INPUT_ARRAY_MESSAGE);
        }

        int previousElement = array[0];
        this.maxIncreasingSequence = initiateLinkedList(previousElement);
        this.maxDescendingSequence = initiateLinkedList(previousElement);
        List<Integer> incSeq = initiateLinkedList(previousElement);
        List<Integer> descSeq = initiateLinkedList(previousElement);

        for (int i = 1; i < array.length; i++) {
            int currentElement = array[i];
            if (previousElement < currentElement) {
                incSeq.add(currentElement);
                if (this.maxIncreasingSequence.size() < incSeq.size()) {
                    this.maxIncreasingSequence = new LinkedList<>(incSeq);
                }
            } else {
                incSeq = initiateLinkedList(currentElement);
            }

            if (previousElement > currentElement) {
                descSeq.add(currentElement);
                if (this.maxDescendingSequence.size() < descSeq.size()) {
                    this.maxDescendingSequence = new LinkedList<>(descSeq);
                }
            } else {
                descSeq = initiateLinkedList(currentElement);
            }

            previousElement = currentElement;
        }
    }

}
