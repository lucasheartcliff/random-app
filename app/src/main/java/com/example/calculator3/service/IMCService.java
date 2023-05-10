package com.example.calculator3.service;

import com.example.calculator3.model.FemaleZone;
import com.example.calculator3.model.FormModel;
import com.example.calculator3.model.MaleZone;
import com.example.calculator3.model.Sex;
import com.example.calculator3.model.ZoneSituation;
import com.example.calculator3.model.Zones;

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
