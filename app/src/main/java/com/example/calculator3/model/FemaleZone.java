package com.example.calculator3.model;

public class FemaleZone extends Zones{
    public FemaleZone() {
        super(new Double[]{Double.NEGATIVE_INFINITY, 19.1},
                new Double[]{19.1, 25.8},
                new Double[]{25.8, 27.3},
                new Double[]{27.3, 32.3},
                new Double[]{32.3,Double.POSITIVE_INFINITY});
    }
}
