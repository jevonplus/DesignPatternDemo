package com.jevonplus.designpatterndemo.colorfactory;

public class White implements Color {
    private static final String COLOUR = "White";
    @Override
    public String fill() {
        return COLOUR;
    }
}
