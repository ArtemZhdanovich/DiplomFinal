package zhdanboro.generation.generator;

public interface Generator {
    double nextDouble();
    int nextBit();
    double[] doubleArray(int count);
    int[] bitArray(int count);
    String getState(int offset);
    default String getState() {
        return getState(0);
    }
}

