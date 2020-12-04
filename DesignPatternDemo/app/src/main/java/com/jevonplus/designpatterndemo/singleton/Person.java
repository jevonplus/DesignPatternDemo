package com.jevonplus.designpatterndemo.singleton;

public abstract class Person {
    public String mName = "unknow";
    public int mAge = 0;
    public String mSex = "unknow";

    public abstract String getName();
    public abstract void setName(String name);
    public abstract int getAge();
    public abstract void setAge(int age);
    public abstract String getSex();
    public abstract void setSex(String sex);
}
