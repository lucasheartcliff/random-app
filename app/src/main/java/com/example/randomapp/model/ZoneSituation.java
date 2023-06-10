package com.example.randomapp.model;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ZoneSituation {
    LOW("abaixo do peso"),
    NORMAL("peso normal"),
    LIL_OVER("levemente acima do peso"),
    OVER("acima do peso"),
    SEVERAL_OVER("muito acima do peso");

    private final String situation;
    private static final Map<Integer, ZoneSituation> map = new HashMap<Integer, ZoneSituation>() {{
        put(0, LOW);
        put(1, NORMAL);
        put(2, LIL_OVER);
        put(3, OVER);
        put(4, SEVERAL_OVER);
    }};

    ZoneSituation(String text) {
        this.situation = text;
    }

    public String getSituation(){
        return situation;
    }

    @SuppressLint("NewApi")
    public static Optional<ZoneSituation> getZoneByIndex(int idx) {
        return Optional.ofNullable(map.getOrDefault(idx, null));
    }


}
