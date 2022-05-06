package zhdanboro.maincontroller;

import org.apache.commons.math3.util.Pair;
import zhdanboro.analyzing.AnalyzeGeneration;
import zhdanboro.analyzing.AnalyzeSequenceTests;
import zhdanboro.database.DatabaseService;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.GraphicsCreator;
import zhdanboro.maincontroller.analyzecontroller.AnalyzeController;
import zhdanboro.maincontroller.generatecontroller.GenerateController;
import zhdanboro.maincontroller.generatecontroller.utils.PackInfo;
import zhdanboro.ui.ModalWindow;

public class MainController {
    GenerateController generateController;
    AnalyzeController analyzeController;
    GraphicsCreator creator;
    boolean generate;

    public MainController(boolean generateMode, String[] args) {
        generateController = null;
        analyzeController = null;
        creator = new GraphicsCreator("График функции");
        if (generateMode) {
            generateController = new GenerateController(args);
            generate = true;
        } else {
            analyzeController = new AnalyzeController(args);
            generate = false;
        }
    }

    ////////////////////////////////  Generate block  ////////////////////////////////
    public void generate(String[] args) {
        checkFunction();
    }

    private void checkFunction() {
        checkSingleGraph();
        checkSaveToBase();
    }

    private void checkSingleGraph() {
        if (generateController.getProperties().isSingleGraph()) {
            Sequence sequence = generateController.generateSequence();
            creator.updateTitle(creator.getTitle() + " №" + (generateController.getProperties().getStartPosition()+1));
            creator.createChart(sequence, generateController.getProperties().getDeviation());
            checkAnalyzeBest(generateController.generateBitSequence());
        } else {
            Sequence resultSequence = AnalyzeGeneration.createSequenceOffBoundsIndices(generateController.generateSequenceArray(), generateController.getProperties().getDeviation());
            creator.updateTitle(creator.getTitle() + " точек входа в интервал");
            creator.createChart(resultSequence);

            Sequence bestSequence = AnalyzeGeneration.findBestSequence(generateController.generateSequenceArray(), generateController.getProperties().getDeviation());
            creator = new GraphicsCreator("График функции №" + (resultSequence.getMinIndex()+1));
            creator.createChart(bestSequence, generateController.getProperties().getDeviation());
            checkAnalyzeBest(generateController.generateBitSequenceOffset(resultSequence.getMinIndex()));
        }
    }
    private void checkAnalyzeBest(Sequence sequence) {
        if (generateController.getProperties().isAnalyzeSequence()) {
            AnalyzeSequenceTests analyzer = new AnalyzeSequenceTests();
            analyzer.analyzeByTests(sequence);
            analyzer.showResults();

            String[] args = PackInfo.pack(analyzer.getResults());
            new ModalWindow().showAnalyzeStat(args);
        }
    }
    private void checkSaveToBase() {
        if (generateController.getProperties().isSaveToBase()) {
            if (!DatabaseService.saveToDatabase(false, generateController.getProperties().getPolynomial(), generateController.generateSequenceArray()))
                System.out.println("Error in writing file");
            else
                System.out.println("Write OK");

            if (!DatabaseService.saveToDatabase(true, generateController.getProperties().getPolynomial(), generateController.generateBitSequenceArray()))
                System.out.println("Error in writing binary file");
            else
                System.out.println("Write OK");

        }
    }

    //////////////////////////////// Analyze block ////////////////////////////////
    public void analyze() {
        Pair<Boolean, String>pair = analyzeController.analyze();
        String[] args = new String[2];
        args[0] = pair.getFirst() ?"Последовательность найдена в базе": "Последовательность не найдена";
        args[1] = "Степень совпадения: " + pair.getSecond();

        new ModalWindow().showFindWindow(args);
    }
}
