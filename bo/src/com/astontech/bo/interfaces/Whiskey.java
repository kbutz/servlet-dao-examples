package com.astontech.bo.interfaces;

/**
 * Created by kylebutz1 on 11/1/2016.
 */
public class Whiskey implements IAlcohol {

    private double ABV = 40.0;
    private boolean isBudweiser = false;

    @Override
    public double ABV() {
        return ABV;
    }

    @Override
    public boolean isDelicious() {
        if (isBudweiser) {
            return false;
        } else
            return true;
    }
}
