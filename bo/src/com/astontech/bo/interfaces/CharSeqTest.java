package com.astontech.bo.interfaces;

import java.util.stream.IntStream;

/**
 * Created by kylebutz1 on 11/1/2016.
 */
public class CharSeqTest implements CharSequence {

    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public CharSeqTest(String s) {
        this.s = s;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public IntStream chars() {
        return null;
    }

    @Override
    public IntStream codePoints() {
        return null;
    }
}
