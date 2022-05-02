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
        sequence = new ArrayList<>();
        for (double val:array)
            sequence.add(val);
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

    @Override
    public String toString() {
        return "Sequence{" +
                "sequence=" + sequence +
                '}';
    }
}
