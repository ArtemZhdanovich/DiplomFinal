package zhdanboro.analyzing;

import org.apache.commons.math3.util.Pair;
import org.apache.commons.math3.util.Precision;
import zhdanboro.generation.sequence.Sequence;

public class AnalyzeEnteredSequence {
    public static Pair<Boolean, String> analyze(Sequence[] databaseSequences, Sequence sequence, double equalCriteria) {
        boolean equal = false;
        double actualPercent;
        double maxPercent = 0;
        for (Sequence seq:databaseSequences) {
            int posSeq = 0;
            int posDat = 0;
            int equalCount = 0;
            int offset = 0;
            boolean first = true;
            for (;posDat<seq.getLength(); posDat++) {
                if (!first)
                    posSeq++;
                if (posSeq>= sequence.getLength()) {
                    posSeq = 0;
                    equalCount = 0;
                }
                if (seq.getElement(posDat) == sequence.getElement(posSeq)) {
                    equalCount++;
                    if (first) {
                        offset = posDat;
                        first = false;
                    }
                }
            }
            for (int i = 0; i<offset; i++) {
                if (seq.getElement(i) == sequence.getElement(posSeq))
                    equalCount++;
                posSeq++;
                if (posSeq>=sequence.getLength()) {
                    posSeq = 0;
                    equalCount = 0;
                }
            }

            equal = (double)equalCount/sequence.getLength()>=equalCriteria;
            actualPercent = (double) equalCount/sequence.getLength();
            if (actualPercent>maxPercent)
                maxPercent = actualPercent;
            if (equal)
                break;
        }

        return new Pair<>(equal, Double.valueOf(Precision.round(maxPercent * 100, 2)).toString());
    }
}
