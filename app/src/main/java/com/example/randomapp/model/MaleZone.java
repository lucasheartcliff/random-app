package com.example.randomapp.model;

public class MaleZone extends Zones{
    public MaleZone() {
        super(new Double[]{Double.NEGATIVE_INFINITY, 20.7},
                new Double[]{20.7, 26.4},
                new Double[]{26.4, 27.8},
                new Double[]{27.8, 31.1},
                new Double[]{31.1,Double.POSITIVE_INFINITY});
    }
}
