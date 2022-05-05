package zhdanboro.maincontroller;

import zhdanboro.analyzing.AnalyzeGeneration;
import zhdanboro.analyzing.AnalyzeSequenceTests;
import zhdanboro.database.DatabaseService;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.GraphicsCreator;
import zhdanboro.maincontroller.generatecontroller.GenerateController;

public class MainController {
    GenerateController controller;
    GraphicsCreator creator;

    public MainController(String[] args) {
        controller = new GenerateController(args);
        creator = new GraphicsCreator("График функции");
    }
    public void generate(String[] args) {
        checkFunction();
    }

    private void checkFunction() {
        checkSingleGraph();
        checkSaveToBase();
    }

    private void checkSingleGraph() {
        if (controller.getProperties().isSingleGraph()) {
            Sequence sequence = controller.generateSequence();
            creator.updateTitle(creator.getTitle() + " №" + (controller.getProperties().getStartPosition()+1));
            creator.createChart(sequence, controller.getProperties().getDeviation());
            checkAnalyzeBest(controller.generateBitSequence());
        } else {
            Sequence resultSequence = AnalyzeGeneration.createSequenceOffBoundsIndices(controller.generateSequenceArray(), controller.getProperties().getDeviation());
            creator.updateTitle(creator.getTitle() + " точек входа в интервал");
            creator.createChart(resultSequence);

            Sequence bestSequence = AnalyzeGeneration.findBestSequence(controller.generateSequenceArray(), controller.getProperties().getDeviation());
            creator = new GraphicsCreator("График функции №" + (resultSequence.getMinIndex()+1));
            creator.createChart(bestSequence, controller.getProperties().getDeviation());
            checkAnalyzeBest(controller.generateBitSequenceOffset(resultSequence.getMinIndex()));
        }
    }
    private void checkAnalyzeBest(Sequence sequence) {
        if (controller.getProperties().isAnalyzeSequence()) {
            AnalyzeSequenceTests analyzer = new AnalyzeSequenceTests();
            analyzer.analyzeByTests(sequence);
            analyzer.showResults();
        }
    }
    private void checkSaveToBase() {
        if (controller.getProperties().isSaveToBase()) {
            if (!DatabaseService.saveToDatabase(false, controller.getProperties().getPolynomial(), controller.generateSequenceArray()))
                System.out.println("Error in writing file");
            else
                System.out.println("Write OK");

            if (!DatabaseService.saveToDatabase(true, controller.getProperties().getPolynomial(), controller.generateBitSequenceArray()))
                System.out.println("Error in writing binary file");
            else
                System.out.println("Write OK");

        }
    }

    public static void analyze() {

    }
}
