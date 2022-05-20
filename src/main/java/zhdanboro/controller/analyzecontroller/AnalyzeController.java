package zhdanboro.controller.analyzecontroller;

import org.apache.commons.math3.util.Pair;
import zhdanboro.analyzing.AnalyzeEnteredSequence;
import zhdanboro.analyzing.AnalyzeSequenceByTests;
import zhdanboro.analyzing.utils.SequenceConverter;
import zhdanboro.controller.generatecontroller.utils.PackInfo;
import zhdanboro.database.DatabaseService;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.GraphicsCreator;
import zhdanboro.ui.ModalWindow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnalyzeController {
    Sequence sequence;
    Sequence[] databaseSequences;
    double equalCriteria;
    boolean binary;

    boolean showGraph;
    boolean showEqual;
    boolean analyzeByTests;
    boolean convert;

    public AnalyzeController(String[] args) {
        sequence = new Sequence();
        if (args[3].equalsIgnoreCase("fifty"))
            equalCriteria = 0.5;
        if (args[3].equalsIgnoreCase("seventyFive"))
            equalCriteria = 0.75;
        if (args[3].equalsIgnoreCase("hundred"))
            equalCriteria = 1.0;

        binary = Boolean.parseBoolean(args[1]);

        databaseSequences = DatabaseService.loadFromDatabase(args[1].equalsIgnoreCase("binary"));
        try (FileReader reader = new FileReader(args[0])) {
            BufferedReader buffer = new BufferedReader(reader);
            String line = buffer.readLine();

            String[] arrayStr = line.split(" ");
            for (String s : arrayStr) {
                sequence.add(Double.parseDouble(s));
            }
        } catch (IOException ex) {
            System.out.println("Cannot open file");
        }

        showGraph = Boolean.parseBoolean(args[4]);
        showEqual = Boolean.parseBoolean(args[5]);
        analyzeByTests = Boolean.parseBoolean(args[6]);
        convert = Boolean.parseBoolean(args[7]);

    }

    public Pair<Boolean, String> analyze() {
        Pair<Boolean, String> result = AnalyzeEnteredSequence.analyze(databaseSequences, sequence, equalCriteria);

        if (convert) {
            Pair<Boolean, String> resTemp = AnalyzeEnteredSequence.analyze(databaseSequences, SequenceConverter.convertScalingDiv(sequence), equalCriteria);
            if (resTemp.getFirst())
                return resTemp;
            else {
                resTemp = AnalyzeEnteredSequence.analyze(databaseSequences, SequenceConverter.convertScalingMun(sequence), equalCriteria);
                if (resTemp.getFirst())
                    return resTemp;
            }
        }

        if (showGraph) {
            GraphicsCreator creator = new GraphicsCreator("График последовательности");
            creator.createChart(sequence, 0);
        }
        if (showEqual) {
            GraphicsCreator creator = new GraphicsCreator("График результата");
            creator.createChart(databaseSequences[AnalyzeEnteredSequence.findMax(databaseSequences, sequence, equalCriteria)], sequence);
        }
        if (analyzeByTests) {
            if (binary) {
                AnalyzeSequenceByTests analyzer = new AnalyzeSequenceByTests();
                analyzer.analyzeByTests(sequence);
                analyzer.showResults();

                String[] args = PackInfo.pack(analyzer.getResults());
                new ModalWindow().showAnalyzeStat(args);
            } else {
                AnalyzeSequenceByTests analyzer = new AnalyzeSequenceByTests();
                analyzer.analyzeByTests(SequenceConverter.convertToBinary(sequence));
                analyzer.showResults();

                String[] args = PackInfo.pack(analyzer.getResults());
                new ModalWindow().showAnalyzeStat(args);
            }
        }

        return result;
    }
}
