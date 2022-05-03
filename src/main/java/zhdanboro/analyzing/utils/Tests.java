package zhdanboro.analyzing.utils;

import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.Pair;
import org.apache.mahout.math.jet.stat.Gamma;
import zhdanboro.generation.sequence.Sequence;

public class Tests {
    public static Pair<Double, Boolean> frequencyTest(Sequence sequence) {
        int nextByte;
        int sum = 0;

        for (double val : sequence) {
            nextByte = val == 1 ? 1 : -1;
            sum += nextByte;
        }

        double stat = Math.abs(sum) / Math.sqrt(sequence.getLength());
        double pValue = Erf.erfc(stat / Math.sqrt(2));
        boolean testOk = pValue > 0.01;

        return new Pair<>(pValue, testOk);
    }

    public static Pair<Double, Boolean> frequencyBlockTest(Sequence sequence) {
        int blockLen = (int) Math.floor(Math.sqrt(sequence.getLength()));
        int blockCount = (int) Math.floor((double) sequence.getLength() / blockLen);
        double sum = 0.0000;
        double chiSquared;

        int blockSum;
        double pi;
        for (int i = 0; i < blockCount; i++) {
            blockSum = 0;
            for (int k = 0; k < blockLen; k++)
                blockSum += sequence.getElement(k + i * blockLen);
            pi = (double) blockSum / (double) blockLen;
            double v = pi - 0.5000;
            sum += v * v;
        }
        chiSquared = 4.0 * blockLen * sum;

        double pValue = Gamma.incompleteGammaComplement(blockCount/2.0, chiSquared/2.0);
        boolean testOk = pValue>0.01;

        return new Pair<>(pValue, testOk);
    }
}
