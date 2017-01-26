package com.astontech.bo.interfaces;

/**
 * Created by kylebutz1 on 11/1/2016.
 */
public class Budweiser implements IAlcohol{

    private double ABV;
    private String name;
    private int TempF;

    public double getABV() {
        return ABV;
    }

    public void setABV(double ABV) {
        this.ABV = ABV;
    }

    public int getTempF() {
        return TempF;
    }

    public void setTempF(int tempF) {
        TempF = tempF;
    }

    @Override
    public double ABV() {
        return ABV;
    }

    @Override
    public boolean isDelicious() {
        if (TempF > 45){
            return false;
        }  else
            return true;
    }
}
