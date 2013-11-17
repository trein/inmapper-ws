package com.inmapper.ws.evaluation.components;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.inmapper.ws.algorithm.filter.Filter;
import com.inmapper.ws.algorithm.filter.LowPassFirstOrderFilter;
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
        File dataFile = FileGenerator.existentFileForData(session.getRoomId(), session.getToken());
        File imageFile = FileGenerator.existentFileForData(session.getRoomId(), session.getToken());
        this.auditor.saveSession(dataFile, session);
        this.plotter.save(imageFile, "Raw Data", createData(session));
    }
    
    private List<PlotData> createData(MobileSessionTo session) {
        Function<MobilePointTo, Double> pointToVariation = getPointToVariationConversion();
        List<MobilePointTo> measurements = session.getPositions();
        List<Double> variations = Lists.transform(measurements, pointToVariation);
        
        return Lists.newArrayList(new Plotter.PlotData("raw mesurements", variations));
    }
    
    public void analyze(String operation, String sessionName) {
        File dataFile = FileGenerator.existentFileForData(operation, sessionName);
        MobileSessionTo session = new SessionAuditor().loadSession(dataFile);
        Filter filter = new LowPassFirstOrderFilter();
        
        List<MobilePointTo> measurements = session.getPositions();
        List<MobilePointTo> filteredPoints = filter.filter(measurements);
        Function<MobilePointTo, Double> pointToVariation = getPointToVariationConversion();
        
        List<Double> variations = Lists.transform(measurements, pointToVariation);
        List<Double> filteredVariations = Lists.transform(filteredPoints, pointToVariation);
        
        this.plotter.show("Variations", createData(session));
    }
    
    private Function<MobilePointTo, Double> getPointToVariationConversion() {
        Function<MobilePointTo, Double> pointToVariation = new Function<MobilePointTo, Double>() {
            
            @Override
            public Double apply(MobilePointTo point) {
                return point.getVariation();
            }
        };
        return pointToVariation;
    }
    
}
