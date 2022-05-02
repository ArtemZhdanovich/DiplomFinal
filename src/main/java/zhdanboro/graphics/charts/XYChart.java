package zhdanboro.graphics.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import zhdanboro.generation.sequence.Sequence;

import java.awt.*;
import java.io.Serial;

public class XYChart extends ApplicationFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    public XYChart(final String title, Sequence sequence, String graphTitle)
    {
        super(title);

        XYDataset dataset    = createDataset(sequence);
        JFreeChart chart      = createChart(dataset, true, graphTitle);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(560, 480));
        setContentPane(chartPanel);
    }

    public XYChart(final String title, Sequence sequence, double deviation, String graphTitle)
    {
        super(title);

        XYDataset dataset     = createDataset(sequence, deviation);
        JFreeChart chart      = createChart(dataset, false, graphTitle);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(560, 480));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(XYDataset dataset, boolean result, String title) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                null,                        // x axis label
                null,                        // y axis label
                dataset,                     // data
                PlotOrientation.VERTICAL,
                true,                        // include legend
                false,                       // tooltips
                false                        // urls
        );

        if (result)
            customizeResult(chart);
        else
            customizeGraph(chart);

        return chart;
    }
    private void customizeResult(JFreeChart chart) {
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.CYAN);
        plot.setDomainGridlinePaint(Color.blue);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke       (0, new BasicStroke(2.5f));
        renderer.setSeriesStroke       (1, new BasicStroke(1.5f));

        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);
    }
    private void customizeGraph(JFreeChart chart) {
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.CYAN);
        plot.setDomainGridlinePaint(Color.blue);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        for (int i = 0; i<4; i++) {
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke(i, new BasicStroke(1.5f));
        }
        renderer.setSeriesStroke(0, new BasicStroke(2.5f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.BLUE);
        renderer.setSeriesPaint(3, Color.GREEN);

        plot.setRenderer(renderer);
    }

    private XYDataset createDataset(Sequence sequence) {
        final XYSeries series = new XYSeries("График точек входа в интервал");
        final XYSeries minPoint = new XYSeries("Min point");
        minPoint.add(sequence.getMinIndex()+1, sequence.getElement(sequence.getMinIndex()));

        for (int i = 1; i<sequence.getLength()+1; i++) {
            series.add(i, sequence.getElement(i-1));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(minPoint);

        return dataset;
    }

    private XYDataset createDataset(Sequence sequence, double deviation) {
        final XYSeries result = new XYSeries("Result");
        final XYSeries upInterval = new XYSeries("Max interval border");
        final XYSeries downInterval = new XYSeries("Min interval border");
        final XYSeries middle = new XYSeries("Middle");
        for (int i = 1; i<sequence.getLength()+1; i++) {
            upInterval.add(i, 0.5000+deviation);
            downInterval.add(i, 0.5000-deviation);
            middle.add(i, 0.5000);
            result.add(i, sequence.getElement(i-1));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(result);
        dataset.addSeries(upInterval);
        dataset.addSeries(downInterval);
        dataset.addSeries(middle);

        return dataset;
    }
}
