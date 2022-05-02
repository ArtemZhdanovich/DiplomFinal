package zhdanboro.maincontroller.generatecontroller;

import zhdanboro.generation.generator.Generator;
import zhdanboro.generation.generator.fabric.GeneratorCreator;
import zhdanboro.generation.generator.fabric.GeneratorType;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.maincontroller.generatecontroller.utils.Properties;

public class GenerateController {

    public Properties properties;

    public GenerateController(String[] args) {
        properties = generateProperties(args);
    }

    private Properties generateProperties(String[] args) {
        Properties propertiesInner = new Properties();
        propertiesInner.setPolynomial(args[0]);
        propertiesInner.setDeviation(Double.parseDouble(args[1]));
        propertiesInner.setSequenceLength(Integer.parseInt(args[2]));
        propertiesInner.setGenerationCount(Integer.parseInt(args[3]));
        propertiesInner.setStartPosition(Integer.parseInt(args[4]));
        propertiesInner.setSaveToBase(Boolean.getBoolean(args[5]));
        propertiesInner.setSaveToBase(Boolean.getBoolean(args[6]));

        return propertiesInner;
    }

    public Sequence generate() {
        Generator generator = GeneratorCreator.createGenerator(properties.getPolynomial(), GeneratorType.LFSR);
        return new Sequence(generator.doubleArray(properties.getSequenceLength()));
    }
}
