package com.jevonplus.designpatterndemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.jevonplus.designpatterndemo.abstractfactory.AbstractFactory;
import com.jevonplus.designpatterndemo.abstractfactory.FactoryCollect;
import com.jevonplus.designpatterndemo.factorymethod.Vehicle;
import com.jevonplus.designpatterndemo.factorymethod.VehicleFactory;

public class DesignService extends Service {
    private DesignServiceBinder mBinder = new DesignServiceBinder();
    private VehicleFactory mVehicleFactory;

    public void onCreate(){
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        return super.onStartCommand(intent,flags,startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class DesignServiceBinder extends Binder {
        public DesignService getDesignService(){
            return DesignService.this;
        }
    }

    public Vehicle customVehicleToFactory(int type) {
        mVehicleFactory = new VehicleFactory();
        return mVehicleFactory.customVehicle(type);
    }

    public AbstractFactory getFactory(int factoryType) {
        return FactoryCollect.getFactory(factoryType);
    }

}
