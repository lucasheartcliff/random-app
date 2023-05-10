package com.example.randomapp.service;

import com.example.randomapp.model.FemaleZone;
import com.example.randomapp.model.FormModel;
import com.example.randomapp.model.MaleZone;
import com.example.randomapp.model.Sex;
import com.example.randomapp.model.ZoneSituation;
import com.example.randomapp.model.Zones;

public class IMCService {

    public ZoneSituation calculateIMC(FormModel model){
        if(!model.hasValidated()) throw new RuntimeException("The form values are not validated");

        Sex sex = model.getSex();
        Zones zones = sex== Sex.MALE? new MaleZone(): new FemaleZone();

        Double height =model.getHeight();
        Double weight = model.getWeight();

        Double imc = weight/ Math.pow(height,2);

        return zones.buildSituation(imc);
    }
}
