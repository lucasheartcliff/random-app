package com.example.randomapp.model;

import android.annotation.SuppressLint;

public abstract class Zones {
    private Double[] lowWeight;
    private Double[] normalWeight;
    private Double[] littleOverWeight;
    private Double[] overWeight;
    private Double[] severalOverWeight;

    public Zones(Double[] lowWeight, Double[] normalWeight, Double[] lilOverWeight, Double[] overWeight, Double[] severalOverWeight){
        this.lowWeight = lowWeight;
        this.normalWeight = normalWeight;
        this.littleOverWeight = lilOverWeight;
        this.overWeight = overWeight;
        this.severalOverWeight = severalOverWeight;
    }


    @SuppressLint("NewApi")
    public ZoneSituation buildSituation(Double imc){
        Double[][] zones = {lowWeight,normalWeight,littleOverWeight,overWeight,severalOverWeight};

        for (int i = 0; i < zones.length; i++) {
            Double[] zone = zones[i];

            Double start = zone[0];
            Double end = zone[1];

            ZoneSituation zoneSituation = ZoneSituation.getZoneByIndex(i).get();

            if(start <= imc && imc <=end) return zoneSituation;
        }
        return null;
    }
}
