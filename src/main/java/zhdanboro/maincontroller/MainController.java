package zhdanboro.maincontroller;

import zhdanboro.analyzing.AnalyzeGeneration;
import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.GraphicsCreator;
import zhdanboro.maincontroller.generatecontroller.GenerateController;

public class MainController {

    public static void generate(String[] args) {
        GenerateController controller = new GenerateController(args);
        GraphicsCreator creator = new GraphicsCreator("График функции");

        checkFunction(controller, creator);
    }

    private static void checkFunction(GenerateController controller, GraphicsCreator creator) {
        if (controller.getProperties().isSingleGraph()) {
            Sequence sequence = controller.generateSequence();
            creator.updateTitle(creator.getTitle() + " №" + (controller.getProperties().getStartPosition()+1));
            creator.createChart(sequence, controller.getProperties().getDeviation());
        } else {
            Sequence resultSequence = AnalyzeGeneration.createSequenceOffBoundsIndices(controller.generateSequenceArray(), controller.getProperties().getDeviation());
            creator.updateTitle(creator.getTitle() + " точек входа в интервал");
            creator.createChart(resultSequence);

            Sequence bestSequence = AnalyzeGeneration.findBestSequence(controller.generateSequenceArray(), controller.getProperties().getDeviation());
            creator = new GraphicsCreator("График функции №" + (resultSequence.getMinIndex()+1));
            creator.createChart(bestSequence, controller.getProperties().getDeviation());
        }
    }

    public static void analyze() {

    }
}
