package zhdanboro.analyzing;

import org.apache.commons.math3.util.Pair;
import zhdanboro.analyzing.utils.Tests;
import zhdanboro.generation.sequence.Sequence;

import java.util.ArrayList;
import java.util.HashMap;

public class AnalyzeSequenceTests {
    HashMap<String, Pair<Double, Boolean>> results;

    AnalyzeSequenceTests() {
        results = new HashMap<>();
    }

    public void analyzeByTests(Sequence sequence) {
        results.put("Frequency", Tests.frequencyTest(sequence));
        results.put("FrequencyBlock", Tests.frequencyBlockTest(sequence));

        System.out.println("Frequency: " + results.get("Frequency").getFirst());
        System.out.println("FrequencyBlock" + results.get("FrequencyBlock").getFirst());
    }
}
