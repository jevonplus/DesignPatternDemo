package com.jevonplus.designpatterndemo.AbstractFactory;

import com.jevonplus.designpatterndemo.colorfactory.Blue;
import com.jevonplus.designpatterndemo.colorfactory.Color;
import com.jevonplus.designpatterndemo.colorfactory.Red;
import com.jevonplus.designpatterndemo.colorfactory.White;
import com.jevonplus.designpatterndemo.factorymethod.Vehicle;

public class ColorFactory extends AbstractFactory {
    public static final int RED_TYPE = 1;
    public static final int WHITE_TYPE = 2;
    public static final int BLUS_TYPE = 3;
    @Override
    public Vehicle getVehicle(int vehicleType) {
        return null;
    }

    @Override
    public Color getColor(int color) {
            Color temp = null;
            switch (color) {
                case RED_TYPE:
                    temp = new Red();
                    break;
                case WHITE_TYPE:
                    temp = new White();
                    break;
                case BLUS_TYPE:
                    temp = new Blue();
                    break;
                default:
                    temp = new White();
                    break;
            }
            return temp;
        }
}
