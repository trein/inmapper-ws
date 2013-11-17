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
        // guilly's 60 step mapping test
        this.analysis.analyze("ios-mapping", "1384645335327-9d21f629-9555-4071-ac22-68176db4c5ab");
        
        // navjot's 16 step test
        this.analysis.analyze("ios-steps", "1384645705006-ed232730-16c0-4777-9b72-c50601dbac80");
        
        // navjot's 20 step test
        // this.analysis.analyze("ios-steps", "1384642987877-432233f4-1cff-4e5e-a675-6737b1b464ba");
    }
}
