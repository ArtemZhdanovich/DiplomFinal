package zhdanboro.generation.generator.lfsr;

import zhdanboro.generation.generator.Generator;

public class LFSRGenerator implements Generator {
    private final String initialState;
    private StringBuilder polynomial;
    private double sum;
    private int count;
    private final int length;
    private final boolean firstBit;

    private LFSRGenerator(String polynomial, boolean firstBitMode) {
        initialState = polynomial;
        this.polynomial = new StringBuilder(polynomial);
        sum = 0.0000;
        count = 0;
        length = polynomial.length();
        firstBit = firstBitMode;
    }

    private void tick(StringBuilder polynomial) {
        int newByte = Character.getNumericValue(polynomial.charAt(length - 1));
        int[] arr = TabsTable.getArray(polynomial.length());
        for (int a : arr) {
            newByte ^= Character.getNumericValue(polynomial.charAt(a - 1));
        }

        polynomial.deleteCharAt(length - 1);
        polynomial.insert(0, newByte);
    }

    private int getBit() {
        int index = firstBit ? 0 : length - 1;
        return Character.getNumericValue(polynomial.charAt(index));
    }

    private double getDouble() {
        sum+= getBit();
        count++;

        return sum/count;
    }

    private void reset() {
        sum = 0;
        count = 0;
        polynomial = new StringBuilder(initialState);
    }

    public static LFSRGenerator createLFSRGenerator(String polynomial) {
        return createLFSRGenerator(polynomial, true);
    }
    public static LFSRGenerator createLFSRGenerator(String polynomial, boolean firstBitType) {
        return new LFSRGenerator(polynomial, firstBitType);
    }

    @Override
    public double nextDouble() {
        double value = getDouble();
        tick(polynomial);

        return value;
    }

    @Override
    public int nextBit() {
        int value = getBit();
        tick(polynomial);

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
            tick(polynomial);
        String state = new String(polynomial);

        reset();
        return state;
    }
}
