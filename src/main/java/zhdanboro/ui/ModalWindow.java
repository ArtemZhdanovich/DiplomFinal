package zhdanboro.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ModalWindow {

    public void showFindWindow(String[] args) {
        Label secondLabel = new Label(args[0] + "\n" + args[1] + "%");
        secondLabel.setWrapText(true);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 400, 200);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Результат");
        newWindow.setScene(secondScene);

        newWindow.show();
    }

    public void showAnalyzeStat(String[] args) {
        StringBuilder labelValue = new StringBuilder();
        for (String val:args) {
            labelValue.append(val);
        }
        Label secondLabel = new Label(labelValue.toString());
        secondLabel.setWrapText(true);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 400, 100*args.length);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Результаты анализа");
        newWindow.setScene(secondScene);

        newWindow.show();
    }
}
