package zhdanboro.analyzing;

import zhdanboro.generation.sequence.Sequence;

public class AnalyzeGeneration {
    public static Sequence findBestSequence(Sequence[] sequences, double deviation) {
        int minSequenceIndex = 0;
        int nowMaxOffBoundsIndex = sequences[0].getLength();
        for (int k = 0; k<sequences.length; k++) {
            int maxOffBoundsIndex = 0;
            for (int i = 0; i<sequences[k].getLength(); i++) {
                if (sequences[k].getElement(i)>0.5000+deviation | sequences[k].getElement(i)<0.5000-deviation)
                    maxOffBoundsIndex = i;
            }
            if (nowMaxOffBoundsIndex>maxOffBoundsIndex) {
                nowMaxOffBoundsIndex = maxOffBoundsIndex;
                minSequenceIndex = k;
            }
        }
        return sequences[minSequenceIndex];
    }

    public static Sequence createSequenceOffBoundsIndices(Sequence[] sequences, double deviation) {
        Sequence result = new Sequence();
        for (Sequence sequence:sequences) {
            int minPos = 0;
            for (int i = 0; i<sequence.getLength(); i++) {
                if (sequence.getElement(i)>0.5000+deviation | sequence.getElement(i)<0.5000-deviation)
                    minPos = i;
            }
            result.add(minPos+1);
        }

        return result;
    }
}
