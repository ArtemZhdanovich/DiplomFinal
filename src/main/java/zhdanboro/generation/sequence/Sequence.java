package zhdanboro.generation.sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Sequence implements Iterable<Double>{
    ArrayList<Double> sequence;

    public Sequence() {
        sequence = new ArrayList<>();
    }
    public Sequence(double[] array) {
        this();
        for (double val:array)
            sequence.add(val);
    }
    public Sequence(int[] array) {
        this();
        for (double val:array) {
            sequence.add(val);
        }
    }

    @Override
    public Iterator<Double> iterator() {
        return sequence.iterator();
    }

    public double getElement(int index) {
        return sequence.get(index);
    }

    public void add(double element) {
        sequence.add(element);
    }

    public int getLength() {
        return sequence.size();
    }

    public int getMinIndex() {
        int minValue = Collections.min(sequence).intValue();
        return sequence.indexOf((double) minValue);
    }

    public double getMin() {
        return Collections.min(sequence);
    }
    public double getMax() {
        return Collections.max(sequence);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (double val:sequence) {
            res.append(val);
            res.append(" ");
        }

        return res.toString();
    }
}
