package com.jevonplus.designpatterndemo.factorymethod;

public class Car implements Vehicle {
    @Override
    public String produce() {
        return "Car";
    }
}
