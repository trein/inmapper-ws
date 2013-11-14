package com.inmapper.ws.evaluation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;

public class Plotter {
    
    private static final Color[] COLORS = { Color.newColor("CA3D05"), Color.SKYBLUE, Color.BEIGE, Color.CORAL };
    
    private static final int MIN_RANGE = 7;
    private static final int MAX_RANGE = 14;
    
    private final List<List<? extends Number>> datum = Lists.newArrayList();
    private final List<Plot> lines = Lists.newArrayList();
    private final String title;
    
    public Plotter(String title) {
        this.title = title;
    }
    
    public Plotter addAxis(List<Double> data, String title) {
        Color color = COLORS[this.datum.size()];
        Line line = Plots.newLine(DataUtil.scaleWithinRange(MIN_RANGE, MAX_RANGE, Doubles.toArray(data)), color, title);
        line.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line.addShapeMarkers(Shape.DIAMOND, color, 4);
        line.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 2);
        this.lines.add(line);
        this.datum.add(data);
        return this;
    }
    
    protected String createChart() {
        LineChart chart = GCharts.newLineChart(this.lines);
        chart.setSize(600, 450);
        chart.setTitle(this.title, Color.GRAY, 14);
        // chart.addHorizontalRangeMarker(40, 60, Color.newColor(Color.RED, 30));
        // chart.addVerticalRangeMarker(70, 90, Color.newColor(Color.GREEN, 30));
        
        AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.GRAY, 12, AxisTextAlignment.CENTER);
        
        AxisLabels yAxis = AxisLabelsFactory.newNumericRangeAxisLabels(MIN_RANGE, MAX_RANGE);
        yAxis.setAxisStyle(axisStyle);
        AxisLabels yAxis2 = AxisLabelsFactory.newAxisLabels("Values");
        yAxis2.setAxisStyle(AxisStyle.newAxisStyle(Color.GRAY, 14, AxisTextAlignment.CENTER));
        
        AxisLabels xAxis = AxisLabelsFactory.newNumericRangeAxisLabels(0, getXBounds());
        xAxis.setAxisStyle(axisStyle);
        AxisLabels xAxis2 = AxisLabelsFactory.newAxisLabels("Sample Number");
        xAxis2.setAxisStyle(AxisStyle.newAxisStyle(Color.GRAY, 14, AxisTextAlignment.CENTER));
        
        chart.addYAxisLabels(yAxis);
        // chart.addYAxisLabels(yAxis2);
        // chart.addXAxisLabels(xAxis);
        // chart.addXAxisLabels(xAxis2);
        chart.setGrid(10, 10, 3, 2);
        chart.setMargins(10, 10, 10, 10);
        
        return chart.toURLString();
    }
    
    private int getXBounds() {
        return this.datum.get(0).size();
    }
    
    public void show() {
        URL url = null;
        BufferedImage image = null;
        
        try {
            url = new URL(createChart());
            image = ImageIO.read(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JLabel label = new JLabel(new ImageIcon(image));
        
        createFrame(label);
    }
    
    private void createFrame(JLabel label) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label);
        f.pack();
        f.setLocation(200, 200);
        f.setVisible(true);
    }
}
