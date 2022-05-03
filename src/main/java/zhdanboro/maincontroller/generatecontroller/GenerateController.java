package zhdanboro.maincontroller.generatecontroller;

import zhdanboro.generation.generator.Generator;
import zhdanboro.generation.generator.fabric.GeneratorCreator;
import zhdanboro.generation.generator.fabric.GeneratorType;
import zhdanboro.generation.generator.lfsr.LFSRGenerator;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.maincontroller.generatecontroller.utils.Properties;

public class GenerateController {

    private final Properties properties;

    public GenerateController(String[] args) {
        properties = generateProperties(args);
        createInitialPolynomialState();
    }

    private Properties generateProperties(String[] args) {
        Properties propertiesInner = new Properties();
        propertiesInner.setPolynomial(args[0]);
        propertiesInner.setDeviation(Double.parseDouble(args[1]));
        propertiesInner.setSequenceLength(Integer.parseInt(args[2]));
        propertiesInner.setGenerationCount(Integer.parseInt(args[3]));
        propertiesInner.setStartPosition(Integer.parseInt(args[4])-1);
        propertiesInner.setSaveToBase(Boolean.getBoolean(args[5]));
        propertiesInner.setSaveToBase(Boolean.getBoolean(args[6]));

        return propertiesInner;
    }

    private void createInitialPolynomialState() {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getPolynomial(), GeneratorType.LFSR);
        properties.setProcessPolynomial(initialGenerator.getState(properties.getStartPosition()));
    }

    public Sequence generateSequence() {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getPolynomial(),GeneratorType.LFSR);
        Generator generator = GeneratorCreator.createGenerator(initialGenerator.getState(properties.getStartPosition()), GeneratorType.LFSR);
        return new Sequence(generator.doubleArray(properties.getSequenceLength()));
    }
    public Sequence[] generateSequenceArray() {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getProcessPolynomial(),GeneratorType.LFSR);
        Sequence[] sequences = new Sequence[properties.getGenerationCount()];
        for (int i = 0; i< properties.getGenerationCount(); i++) {
            Generator generator = GeneratorCreator.createGenerator(initialGenerator.getState(i), GeneratorType.LFSR);
            sequences[i] = new Sequence(generator.doubleArray(properties.getSequenceLength()));
        }

        return sequences;
    }

    public Properties getProperties() {
        return properties;
    }
}
