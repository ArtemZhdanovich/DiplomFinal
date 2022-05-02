package zhdanboro.maincontroller;

import zhdanboro.analyzing.AnalyzeGeneration;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.GraphicsCreator;
import zhdanboro.maincontroller.generatecontroller.GenerateController;

public class MainController {

    public static void generate(String[] args) {
        GenerateController controller = new GenerateController(args);
        GraphicsCreator creator = new GraphicsCreator("График функции");

        Sequence resultSequence = AnalyzeGeneration.createSequenceOffBoundsIndices(controller.generateSequenceArray(), controller.getProperties().getDeviation());
        creator.createChart(resultSequence);

        Sequence bestSequence = AnalyzeGeneration.findBestSequence(controller.generateSequenceArray(), controller.getProperties().getDeviation());
        creator.createChart(bestSequence, controller.getProperties().getDeviation());
    }

    public static void analyze() {

    }
}
