package com.jevonplus.designpatterndemo.factorymethod;

public class VehicleFactory {
    public static final int CAR_TYPE = 1;
    public static final int TRUCK_TYPE = 2;
    public static final int BUS_TYPE = 3;
    public Vehicle customVehicle(int vehicleType) {
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
        }
        return temp;
    }
}
