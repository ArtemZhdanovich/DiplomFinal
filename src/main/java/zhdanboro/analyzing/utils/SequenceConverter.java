package zhdanboro.analyzing.utils;

import zhdanboro.generation.sequence.Sequence;

public class SequenceConverter {
    public static Sequence convertToBinary(Sequence sequence) {
        Sequence binarySequence = new Sequence();
        double nowNum = 0.000;
        double nowBin = 0;
        for (double val:sequence) {
            if (val>nowNum)
                nowBin = 0;
            else if (val<nowNum)
                nowBin = 1;
            binarySequence.add(nowBin);
            nowNum = val;
        }

        return binarySequence;
    }

    public static Sequence convertScalingMun(Sequence sequence) {
        int i = 2;
        while (sequence.getMax()/i>1 & sequence.getMin()/i < 0)
            i++;

        Sequence result = new Sequence();
        for (double val:sequence) {
            result.add(val/i);
        }
        return result;
    }

    public static Sequence convertScalingDiv(Sequence sequence) {
        int i = 2;
        while (sequence.getMax()*i<1)
            i++;

        i--;
        Sequence result = new Sequence();
        for (double val:sequence)
            result.add(val*i);

        return result;
    }
}
