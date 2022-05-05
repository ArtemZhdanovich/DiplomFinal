package zhdanboro.maincontroller;

import zhdanboro.analyzing.AnalyzeGeneration;
import zhdanboro.analyzing.AnalyzeSequenceTests;
import zhdanboro.database.DatabaseService;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.GraphicsCreator;
import zhdanboro.maincontroller.analyzecontroller.AnalyzeController;
import zhdanboro.maincontroller.generatecontroller.GenerateController;

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
        System.out.println(analyzeController.analyze());
    }
}
