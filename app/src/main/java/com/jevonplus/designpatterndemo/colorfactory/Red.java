package com.jevonplus.designpatterndemo.colorfactory;

public class Red implements Color {
    private static final String COLOUR = "Red";
    @Override
    public String fill() {
        return COLOUR;
    }
}
