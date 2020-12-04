package com.jevonplus.designpatterndemo.factorymethod;

public class Bus implements Vehicle {
    @Override
    public String produce() {
        return "Bus";
    }
}
