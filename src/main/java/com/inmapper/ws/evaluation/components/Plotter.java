package com.inmapper.ws.evaluation.components;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Plotter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Plotter.class);
    
    public static class PlotData {
        private final String title;
        private final List<Double> data;
        
        public PlotData(String title, List<Double> data) {
            this.title = title;
            this.data = data;
        }
        
    }
    
    public void save(File file, String title, List<PlotData> dataList) {
        JFreeChart chart = createChart(title, dataList);
        try {
            ChartUtilities.saveChartAsPNG(file, chart, 300, 300);
        } catch (IOException e) {
            LOGGER.error("Error saving chart.", e);
        }
    }
    
    public void show(String title, List<PlotData> dataList) {
        JFreeChart chart = createChart(title, dataList);
        
        ChartPanel cpanel = new ChartPanel(chart);
        createFrame(cpanel);
    }
    
    private XYSeriesCollection createSeries(List<PlotData> dataList) {
        XYSeriesCollection series = new XYSeriesCollection();
        
        for (PlotData plot : dataList) {
            XYSeries serie = new XYSeries(plot.title);
            
            for (int i = 0; i < plot.data.size(); i++) {
                serie.add(Integer.valueOf(i), plot.data.get(i));
            }
            series.addSeries(serie);
        }
        return series;
    }
    
    private JFreeChart createChart(String title, List<PlotData> dataList) {
        XYSeriesCollection series = createSeries(dataList);
        JFreeChart chart = ChartFactory.createXYLineChart(title, "Time * Fs", "Value", series, PlotOrientation.VERTICAL, true,
                true, false);
        XYPlot plot = chart.getXYPlot();
        Font titleFont = new Font("Meiryo", Font.PLAIN, 12);
        Font legendFont = new Font("Meiryo", Font.PLAIN, 8);
        
        chart.getLegend().setItemFont(titleFont);
        chart.getTitle().setFont(titleFont);
        
        XYPlot xyp = chart.getXYPlot();
        xyp.getDomainAxis().setLabelFont(titleFont); // X
        xyp.getRangeAxis().setLabelFont(titleFont); // Y
        // xyp.getDomainAxis().setRange(-610, 610);
        // xyp.getRangeAxis().setRange(-610, 610);
        xyp.getDomainAxis().setTickLabelFont(legendFont);
        xyp.getRangeAxis().setTickLabelFont(legendFont);
        xyp.getDomainAxis().setVerticalTickLabels(true);
        // xyp.getDomainAxis().setFixedAutoRange(100);
        // xyp.getRangeAxis().setFixedAutoRange(100);
        
        // fill and outline
        XYLineAndShapeRenderer r = (XYLineAndShapeRenderer) plot.getRenderer();
        // r.setSeriesOutlinePaint(0, Color.RED);
        // r.setSeriesOutlinePaint(1, Color.BLUE);
        r.setSeriesShapesFilled(0, false);
        r.setSeriesShapesFilled(1, false);
        
        return chart;
    }
    
    private void createFrame(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 500, 500);
        frame.setTitle("Data Plot");
        frame.setVisible(true);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}
