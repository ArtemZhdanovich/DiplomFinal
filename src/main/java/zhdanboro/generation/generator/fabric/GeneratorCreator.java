package zhdanboro.generation.generator.fabric;

import zhdanboro.generation.generator.Generator;
import zhdanboro.generation.generator.lfsr.LFSRGenerator;

public class GeneratorCreator {
    public static Generator createGenerator(String polynomial, GeneratorType type, int[] shifts) {
        Generator generator = null;

        switch (type) {
            case LFSR -> generator = LFSRGenerator.createLFSRGenerator(polynomial, shifts);
            default -> {
                System.out.println("Cannot create this type of generator");
            }
        }
        return generator;
    }
}

