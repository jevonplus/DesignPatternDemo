package com.jevonplus.designpatterndemo.factorymethod;

public class Truck implements Vehicle {
    @Override
    public String produce() {
        return "Truck";
    }
}
