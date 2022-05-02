package zhdanboro.maincontroller;

import zhdanboro.generation.sequence.Sequence;
import zhdanboro.maincontroller.generatecontroller.GenerateController;

public class MainController {

    public static void generate(String[] args) {
        GenerateController controller = new GenerateController(args);
        Sequence sequence = controller.generate();

        System.out.println(sequence);
    }

    public static void analyze() {

    }
}
