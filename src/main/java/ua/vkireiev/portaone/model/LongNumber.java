package ua.vkireiev.portaone.model;

import java.util.HashMap;
import java.util.Map;

public class LongNumber {

    private boolean negative;
    private Map<Integer, Integer> segments;

    public LongNumber() {
        super();
        this.negative = false;
        this.segments = new HashMap<>();
        this.segments.put(0, 0);
    }

    public LongNumber(boolean negative, Map<Integer, Integer> segments) {
        super();
        this.negative = negative;
        this.setSegments(segments);
    }

    public LongNumber(LongNumber longNumber) {
        super();
        this.negative = longNumber.isNegative();
        this.setSegments(longNumber.getSegments());
    }

    public boolean isNegative() {

        return this.negative;
    }

    public void setNegative(boolean negative) {

        this.negative = negative;
    }

    public Map<Integer, Integer> getSegments() {

        return new HashMap<>(segments);
    }

    public void setSegments(Map<Integer, Integer> segments) {

        this.segments = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : segments.entrySet()) {
            Integer key = entry.getKey();
            Integer val = entry.getValue();
            this.segments.put(key, Math.abs(val));
        }
    }

    public void setSegment(int index, int value) {

        this.segments.put(index, Math.abs(value));
    }

    public void removeSegment(int index) {

        this.segments.remove(index);
    }

    public int getSegment(int index) {

        return this.segments.getOrDefault(index, 0);
    }

    public int getMaxSegmentIndex() {

        return this.segments.keySet().stream()
                .max(Comparable::compareTo)
                .orElse(0);
    }

    public void clear() {

        this.negative = false;
        this.segments.clear();
        this.segments.put(0, 0);
    }

    public void copy(LongNumber n) {

        this.negative = n.isNegative();
        this.setSegments(n.getSegments());
    }

}
