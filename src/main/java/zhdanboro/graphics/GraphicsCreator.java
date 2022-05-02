package zhdanboro.graphics;

import zhdanboro.generation.sequence.Sequence;
import zhdanboro.graphics.charts.XYChart;

import javax.swing.*;

public record GraphicsCreator(String title) {
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
}
