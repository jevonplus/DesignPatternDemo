package com.jevonplus.designpatterndemo.singleton;

public class VehicleFactoryManager extends Person {
    private static VehicleFactoryManager mInstance;
    private VehicleFactoryManager(){
    }

    public static synchronized VehicleFactoryManager getInstance() {
        if(mInstance == null) {
            mInstance = new VehicleFactoryManager();
        }
        return mInstance;
    }
    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {
        mName = name;
    }

    @Override
    public int getAge() {
        return mAge;
    }

    @Override
    public void setAge(int age) {
        mAge = age;
    }

    @Override
    public String getSex() {
        return mSex;
    }

    @Override
    public void setSex(String sex) {
        mSex = sex;
    }
}
