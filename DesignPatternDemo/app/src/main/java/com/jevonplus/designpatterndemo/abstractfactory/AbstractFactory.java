package com.jevonplus.designpatterndemo.abstractfactory;


import com.jevonplus.designpatterndemo.colorfactory.Color;
import com.jevonplus.designpatterndemo.factorymethod.Vehicle;

public abstract class AbstractFactory {
 public abstract Vehicle getVehicle(int vehicleType);
 public abstract Color getColor(int color);
}
