package com.inmapper.ws.evaluation;

import com.inmapper.ws.evaluation.components.DataAnalysis;
import com.inmapper.ws.evaluation.components.Plotter;
import com.inmapper.ws.evaluation.components.SessionAuditor;

public class Evaluations {
    
    private final DataAnalysis analysis = new DataAnalysis(new SessionAuditor(), new Plotter());
    
    public static void main(String[] args) {
        new Evaluations().start();
    }
    
    private void start() {
        // 60 step mapping test
        this.analysis.analyze("ios-mapping", "1384645335327-9d21f629-9555-4071-ac22-68176db4c5ab");
    }
}
