package zhdanboro.analyzing;

import org.apache.commons.math3.util.Pair;
import zhdanboro.analyzing.utils.Tests;
import zhdanboro.generation.sequence.Sequence;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class AnalyzeSequenceTests {
    HashMap<String, Pair<Double, Boolean>> results;

    public AnalyzeSequenceTests() {
        results = new HashMap<>();
    }

    public void analyzeByTests(Sequence sequence) {
        results.put("Frequency", Tests.frequencyTest(sequence));
        results.put("FrequencyBlock", Tests.frequencyBlockTest(sequence));
    }

    public void analyzeFrequency(String sequence) {
        int[] array = new int[sequence.length()];
        char[] chars = sequence.toCharArray();
        for (int i = 0; i<chars.length; i++) {
            array[i] = Character.getNumericValue(chars[i]);
        }

        Sequence sequence1 = new Sequence(array);
        System.out.println(sequence1);
        Pair<Double, Boolean> pair = Tests.frequencyTest(sequence1);
        Pair<Double, Boolean> pair1 = Tests.frequencyBlockTest(sequence1);

        System.out.println("Frequency: " + pair.getFirst() + " " + pair.getSecond());
        System.out.println("FrequencyBlock: " + pair1.getFirst() + " " + pair1.getSecond());

    }

    public HashMap<String, Pair<Double, Boolean>> getResults() {
        return results;
    }

    public void showResults() {
        DecimalFormat format = new DecimalFormat("#.##########");
        for (Map.Entry<String, Pair<Double, Boolean>> val:results.entrySet()) {
            System.out.println(val.getKey() + ": " + format.format(val.getValue().getFirst()) + " " + val.getValue().getSecond());
        }
    }
}
