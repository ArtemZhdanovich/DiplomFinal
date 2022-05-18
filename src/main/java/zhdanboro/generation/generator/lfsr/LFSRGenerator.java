package zhdanboro.generation.generator.lfsr;

import zhdanboro.generation.generator.Generator;

public class LFSRGenerator implements Generator {
    private final String initialState;
    private StringBuilder currentState;
    private double sum;
    private int count;
    private final int length;
    private final boolean firstBit;
    private final int[] shifts;

    private LFSRGenerator(String polynomial, boolean firstBitMode, int[] shifts) {
        initialState = polynomial;
        this.currentState = new StringBuilder(polynomial);
        sum = 0.0000;
        count = 0;
        length = polynomial.length();
        firstBit = firstBitMode;
        this.shifts = shifts;
    }

    private void tick(StringBuilder polynomial) {
        int newByte = Character.getNumericValue(polynomial.charAt(length - 1));
        for (int a : shifts) {
            newByte ^= Character.getNumericValue(polynomial.charAt(a - 1));
        }

        polynomial.deleteCharAt(length - 1);
        polynomial.insert(0, newByte);
    }

    private int getBit() {
        int index = firstBit ? 0 : length - 1;
        return Character.getNumericValue(currentState.charAt(index));
    }

    private double getDouble() {
        sum+= getBit();
        count++;

        return sum/count;
    }

    private void reset() {
        sum = 0;
        count = 0;
        currentState = new StringBuilder(initialState);
    }

    public static LFSRGenerator createLFSRGenerator(String polynomial, int[] shifts) {
        return createLFSRGenerator(polynomial, true, shifts);
    }
    public static LFSRGenerator createLFSRGenerator(String polynomial, boolean firstBitType, int[] shifts) {
        return new LFSRGenerator(polynomial, firstBitType, shifts);
    }

    @Override
    public double nextDouble() {
        double value = getDouble();
        tick(currentState);

        return value;
    }

    @Override
    public int nextBit() {
        int value = getBit();
        tick(currentState);

        return value;
    }

    @Override
    public double[] doubleArray(int count) {
        double[] array = new double[count];
        for (int i = 0; i<count; i++)
            array[i] = nextDouble();

        reset();
        return array;
    }

    @Override
    public int[] bitArray(int count) {
        int[] array = new int[count];
        for (int i = 0; i<count; i++)
            array[i] = nextBit();

        reset();
        return array;
    }

    @Override
    public String getState(int offset) {
        for (int i = 0; i<offset; i++)
            tick(currentState);
        String state = new String(currentState);

        reset();
        return state;
    }
}
