package zhdanboro.graphics;

import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.charts.XYChart;

import javax.swing.*;
import java.util.StringTokenizer;

public class GraphicsCreator {
    String baseTitle;
    String title;

    public GraphicsCreator(String title) {
        baseTitle = title;
        this.title = title;
    }
    void initChart(XYChart chart) {
        chart.setAlwaysOnTop(false);
        chart.pack();
        chart.setSize(1600, 800);
        chart.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        chart.setVisible(true);
    }
    public void createChart(Sequence sequence) {
        SwingUtilities.invokeLater(() -> {
            XYChart chart = new XYChart("Function", sequence, title);
            this.initChart(chart);
        });
    }
    public void createChart(Sequence sequence, double deviation) {
        SwingUtilities.invokeLater(() -> {
            XYChart chart = new XYChart("Function", sequence, deviation, title);
            initChart(chart);
        });
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
    public String getTitle() {
        return baseTitle;
    }
}
