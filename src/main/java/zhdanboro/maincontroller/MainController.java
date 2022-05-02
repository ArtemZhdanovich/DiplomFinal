package zhdanboro.maincontroller;

import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.GraphicsCreator;
import zhdanboro.maincontroller.generatecontroller.GenerateController;

public class MainController {

    public static void generate(String[] args) {
        GenerateController controller = new GenerateController(args);
        GraphicsCreator creator = new GraphicsCreator("График функции");

        if (!controller.getProperties().isAnalyzeSequence()) {
            creator.createChart(controller.generateSequence(), controller.getProperties().getDeviation());
        }
    }

    public static void analyze() {

    }
}
