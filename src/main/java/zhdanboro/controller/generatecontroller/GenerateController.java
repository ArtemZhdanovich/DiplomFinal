package zhdanboro.controller.generatecontroller;

import zhdanboro.generation.generator.Generator;
import zhdanboro.generation.generator.fabric.GeneratorCreator;
import zhdanboro.generation.generator.fabric.GeneratorType;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.controller.generatecontroller.utils.Properties;
import zhdanboro.controller.generatecontroller.utils.TabsTable;

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

        propertiesInner.setSaveToBase(Boolean.parseBoolean(args[5]));
        propertiesInner.setAnalyzeSequence(Boolean.parseBoolean(args[6]));
        propertiesInner.setSingleGraph(Boolean.parseBoolean(args[7]));

        if (args[8].equalsIgnoreCase("0")) {
            propertiesInner.setShifts(TabsTable.getShifts(args[0].length()));
        } else {
            int count = 0;
            for (int i = 0; i<args[0].length()-1; i++) {
                if (args[8].charAt(i) == '1')
                    count++;
            }

            int[] arr = new int[count];
            int j = 0;
            for (int i = 0; i<args[0].length()-1; i++) {
                if (args[8].charAt(i) == '1') {
                    arr[j] = i + 1;
                    j++;
                }
            }

            propertiesInner.setShifts(arr);
        }

        return propertiesInner;
    }

    private void createInitialPolynomialState() {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getPolynomial(), GeneratorType.LFSR, properties.getShifts());
        properties.setProcessPolynomial(initialGenerator.getState(properties.getStartPosition()));
    }

    public Sequence generateSequence() {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getPolynomial(),GeneratorType.LFSR, properties.getShifts());
        Generator generator = GeneratorCreator.createGenerator(initialGenerator.getState(properties.getStartPosition()), GeneratorType.LFSR, properties.getShifts());
        return new Sequence(generator.doubleArray(properties.getSequenceLength()));
    }
    public Sequence generateBitSequence() {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getPolynomial(),GeneratorType.LFSR, properties.getShifts());
        Generator generator = GeneratorCreator.createGenerator(initialGenerator.getState(properties.getStartPosition()), GeneratorType.LFSR, properties.getShifts());
        return new Sequence(generator.bitArray(properties.getSequenceLength()));
    }
    public Sequence generateBitSequenceOffset(int offset) {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getPolynomial(),GeneratorType.LFSR, properties.getShifts());
        Generator generator = GeneratorCreator.createGenerator(initialGenerator.getState(offset), GeneratorType.LFSR, properties.getShifts());
        return new Sequence(generator.bitArray(properties.getSequenceLength()));
    }
    public Sequence[] generateSequenceArray() {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getProcessPolynomial(),GeneratorType.LFSR, properties.getShifts());
        Sequence[] sequences = new Sequence[properties.getGenerationCount()];
        for (int i = 0; i< properties.getGenerationCount(); i++) {
            Generator generator = GeneratorCreator.createGenerator(initialGenerator.getState(i), GeneratorType.LFSR, properties.getShifts());
            sequences[i] = new Sequence(generator.doubleArray(properties.getSequenceLength()));
        }

        return sequences;
    }
    public Sequence[] generateBitSequenceArray() {
        Generator initialGenerator = GeneratorCreator.createGenerator(properties.getProcessPolynomial(),GeneratorType.LFSR, properties.getShifts());
        Sequence[] sequences = new Sequence[properties.getGenerationCount()];
        for (int i = 0; i< properties.getGenerationCount(); i++) {
            Generator generator = GeneratorCreator.createGenerator(initialGenerator.getState(i), GeneratorType.LFSR, properties.getShifts());
            sequences[i] = new Sequence(generator.bitArray(properties.getSequenceLength()));
        }

        return sequences;
    }

    public Properties getProperties() {
        return properties;
    }
}
