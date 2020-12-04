package com.jevonplus.designpatterndemo.singleton;

public class ColorFactoryManager extends Person {
    private static ColorFactoryManager mInstance;
    private ColorFactoryManager(){
    }

    public static synchronized ColorFactoryManager getInstance() {
        if(mInstance == null) {
            mInstance = new ColorFactoryManager();
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
