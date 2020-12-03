package com.jevonplus.designpatterndemo.AbstractFactory;

import com.jevonplus.designpatterndemo.colorfactory.Color;
import com.jevonplus.designpatterndemo.factorymethod.Bus;
import com.jevonplus.designpatterndemo.factorymethod.Car;
import com.jevonplus.designpatterndemo.factorymethod.Truck;
import com.jevonplus.designpatterndemo.factorymethod.Vehicle;

public class VehicleFactory extends AbstractFactory{
    public static final int CAR_TYPE = 1;
    public static final int TRUCK_TYPE = 2;
    public static final int BUS_TYPE = 3;
    @Override
    public Vehicle getVehicle(int vehicleType) {
        Vehicle temp = null;
        switch (vehicleType) {
            case CAR_TYPE:
                temp = new Car();
                break;
            case TRUCK_TYPE:
                temp = new Truck();
                break;
            case BUS_TYPE:
                temp = new Bus();
                break;
             default:
                 temp = new Car();
                 break;
        }
        return temp;
    }

    @Override
    public Color getColor(int color) {
        return null;
    }
}
