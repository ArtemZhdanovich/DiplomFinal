package zhdanboro.maincontroller.analyzecontroller;

import org.apache.commons.math3.util.Pair;
import org.apache.commons.math3.util.Precision;
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
        boolean equal = false;
        double actualPercent = 0;
        double maxPercent = 0;
        for (Sequence seq:databaseSequences) {
            int posSeq = 0;
            int posDat = 0;
            int equalCount = 0;
            int offset = 0;
            boolean first = true;
            for (;posDat<seq.getLength(); posDat++) {
                if (!first)
                    posSeq++;
                if (seq.getElement(posDat) == sequence.getElement(posSeq)) {
                    equalCount++;
                    if (first) {
                        offset = posDat;
                        first = false;
                    }
                }
            }
            for (int i = 0; i<offset; i++) {
                if (seq.getElement(i) == sequence.getElement(posSeq))
                    equalCount++;
                posSeq++;
            }

            equal = (double)equalCount/sequence.getLength()>=equalCriteria;
            actualPercent = (double) equalCount/sequence.getLength();
            if (actualPercent>maxPercent)
                maxPercent = actualPercent;
            if (equal)
                break;
        }


        return new Pair<>(equal, Double.valueOf(Precision.round(maxPercent * 100, 2)).toString());
    }
}
