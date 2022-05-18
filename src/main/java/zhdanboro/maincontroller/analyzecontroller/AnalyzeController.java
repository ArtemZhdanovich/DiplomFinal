package zhdanboro.maincontroller.analyzecontroller;

import org.apache.commons.math3.util.Pair;
import org.apache.commons.math3.util.Precision;
import zhdanboro.analyzing.AnalyzeEnteredSequence;
import zhdanboro.database.DatabaseService;
import zhdanboro.generation.sequence.Sequence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnalyzeController {
    Sequence sequence;
    Sequence[] databaseSequences;
    double equalCriteria;

    public AnalyzeController(String[] args) {
        sequence = new Sequence();
        if (args[3].equalsIgnoreCase("fifty"))
            equalCriteria = 0.5;
        if (args[3].equalsIgnoreCase("seventyFive"))
            equalCriteria = 0.75;
        if (args[3].equalsIgnoreCase("hundred"))
            equalCriteria = 1.0;

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

    }

    public Pair<Boolean, String> analyze() {
        return AnalyzeEnteredSequence.analyze(databaseSequences, sequence, equalCriteria);
    }
}
