package com.jevonplus.designpatterndemo.abstractfactory;

public class FactoryCollect {
    private static final int VEHICLE_FACTORY = 1;
    private static final int COLOR_FACTORY = 2;
    public static AbstractFactory getFactory(int factoryType){
        AbstractFactory tempFactory = null;
        switch (factoryType){
            case VEHICLE_FACTORY:
                tempFactory = new VehicleFactory();
                break;
            case COLOR_FACTORY:
                tempFactory = new ColorFactory();
                break;
        }
        return tempFactory;
    }
}
