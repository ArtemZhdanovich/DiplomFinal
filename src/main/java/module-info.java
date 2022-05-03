module zhdanboro.ui.diplomfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jfree.jfreechart;
    requires commons.math3;
    requires mahout.math;


    opens zhdanboro.ui to javafx.fxml;
    exports zhdanboro.ui;
}