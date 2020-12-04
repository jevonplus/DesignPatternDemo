package com.jevonplus.designpatterndemo.colorfactory;

public class Blue implements Color {
    private static final String COLOUR = "Blue";
    @Override
    public String fill() {
        return COLOUR;
    }
}
