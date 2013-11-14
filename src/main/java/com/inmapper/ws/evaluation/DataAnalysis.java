package com.inmapper.ws.evaluation;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.inmapper.ws.algorithm.LowPassFilter;
import com.inmapper.ws.model.to.MobilePointTo;
import com.inmapper.ws.model.to.MobileSessionTo;
import com.inmapper.ws.service.SessionAuditor;

public class DataAnalysis {
    
    private static final String TEST_TOKEN = "1-40e306ad-e923-499a-b0d5-84928089bada";
    private static final String LIVING_TOKEN = "2-e707ef2a-62fe-44ad-97b1-a84112dc5e82";
    private static final String LIVING2_TOKEN = "3-db54efb8-2526-4969-a5e1-f089d7a796d3";
    
    private MobileSessionTo session;
    private LowPassFilter filter;
    
    public static void main(String[] args) {
        new DataAnalysis().start();
    }
    
    private void start() {
        // plotSession(TEST_TOKEN);
        // plotSession(LIVING_TOKEN);
        plotSession(LIVING2_TOKEN);
    }
    
    private void plotSession(String session) {
        this.session = new SessionAuditor().loadSession(session);
        this.filter = new LowPassFilter();
        
        List<MobilePointTo> filteredPoints = this.filter.filter(this.session.getPositions());
        Function<MobilePointTo, Double> pointToVariation = getPointToVariationConversion();
        
        List<Double> variations = Lists.transform(this.session.getPositions(), pointToVariation);
        List<Double> filteredVariations = Lists.transform(filteredPoints, pointToVariation);
        
        new Plotter("Variations").addAxis(variations, "Raw variations").addAxis(filteredVariations, "Filtered variations").show();
        new Plotter("Variations").addAxis(filteredVariations, "Filtered variations").show();
        new Plotter("Variations").addAxis(variations, "Raw variations").show();
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
