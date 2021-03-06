package com.inmapper.ws.evaluation.components;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.inmapper.ws.algorithm.filtering.Filter;
import com.inmapper.ws.algorithm.filtering.LowPassFirstOrderFilter;
import com.inmapper.ws.algorithm.filtering.LowPassSecondOrderFilter;
import com.inmapper.ws.evaluation.components.Plotter.PlotData;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;

@Component
public class DataAnalysis {
    
    private final SessionAuditor auditor;
    private final Plotter plotter;
    
    @Autowired
    public DataAnalysis(SessionAuditor auditor, Plotter plotter) {
        this.auditor = auditor;
        this.plotter = plotter;
    }
    
    public void recordSession(MobileSessionTo session) {
        File dataFile = FileGenerator.newFileForData(session.getRoomId(), session.getToken());
        File imageFile = FileGenerator.newFileForImage(session.getRoomId(), session.getToken());
        List<MobilePointTo> positions = session.getPositions();
        
        this.auditor.saveSession(dataFile, session);
        this.plotter.save(imageFile, "Raw Data", createData(new String[] { "raw data" }, new List[] { positions }));
    }
    
    public void analyze(String operation, String sessionName) {
        File dataFile = FileGenerator.existentFileForData(operation, sessionName);
        MobileSessionTo session = this.auditor.loadSession(dataFile);
        Filter firstFilter = new LowPassFirstOrderFilter();
        Filter secondFilter = new LowPassSecondOrderFilter();
        
        List<MobilePointTo> measurements = session.getPositions();
        List<MobilePointTo> filteredFirstPoints = firstFilter.filter(measurements);
        List<MobilePointTo> filteredSecondPoints = secondFilter.filter(measurements);
        
        // this.plotter.show("Variations", createData(new String[] { "raw data",
        // "first order filter", "second order filter" },
        // new List[] { measurements, filteredFirstPoints, filteredSecondPoints }));
        // this.plotter.show("Headings", createHeadingData(new String[] { "raw data",
        // "first order filter", "second order filter" },
        // new List[] { measurements, filteredFirstPoints, filteredSecondPoints }));
        this.plotter.show("Accelerometers Magnitude", createData(new String[] { "raw data", "second order filter" }, new List[] {
                measurements, filteredSecondPoints }));
        this.plotter.show("Heading Value", createHeadingData(new String[] { "raw data", "second order filter" }, new List[] {
                measurements, filteredSecondPoints }));
    }
    
    @SuppressWarnings("unchecked")
    private List<PlotData> createData(String[] titles, List<?>[] pointsArray) {
        List<PlotData> plotData = Lists.newArrayList();
        Function<MobilePointTo, Double> pointToVariation = getPointToVariationConversion();
        
        for (int index = 0; index < pointsArray.length; index++) {
            String title = titles[index];
            List<MobilePointTo> measurements = (List<MobilePointTo>) pointsArray[index];
            List<Double> variations = Lists.transform(measurements, pointToVariation);
            plotData.add(new Plotter.PlotData(title, variations));
        }
        return plotData;
    }
    
    @SuppressWarnings("unchecked")
    private List<PlotData> createHeadingData(String[] titles, List<?>[] pointsArray) {
        List<PlotData> plotData = Lists.newArrayList();
        Function<MobilePointTo, Double> pointToVariation = getPointToHeadingConversion();
        
        for (int index = 0; index < pointsArray.length; index++) {
            String title = titles[index];
            List<MobilePointTo> measurements = (List<MobilePointTo>) pointsArray[index];
            List<Double> variations = Lists.transform(measurements, pointToVariation);
            plotData.add(new Plotter.PlotData(title, variations));
        }
        return plotData;
    }
    
    private Function<MobilePointTo, Double> getPointToVariationConversion() {
        Function<MobilePointTo, Double> pointToVariation = new Function<MobilePointTo, Double>() {
            
            @Override
            public Double apply(MobilePointTo point) {
                return point.getAccelerationNorm();
            }
        };
        return pointToVariation;
    }
    
    private Function<MobilePointTo, Double> getPointToHeadingConversion() {
        Function<MobilePointTo, Double> pointToVariation = new Function<MobilePointTo, Double>() {
            
            @Override
            public Double apply(MobilePointTo point) {
                return point.getHeading();
            }
        };
        return pointToVariation;
    }
    
}
