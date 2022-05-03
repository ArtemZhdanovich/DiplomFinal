package zhdanboro.maincontroller;

import zhdanboro.analyzing.AnalyzeGeneration;
import zhdanboro.analyzing.AnalyzeSequenceTests;
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
//        AnalyzeSequenceTests analyzeSequenceTests = new AnalyzeSequenceTests();
//        analyzeSequenceTests.analyzeFrequency("1011010101");
        checkFunction();
    }

    private void checkFunction() {
        checkSingleGraph();
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

    public static void analyze() {

    }
}
