package com.jevonplus.designpatterndemo.prototype;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NormalEmployeeDorm implements Cloneable {
    private static final String TAG = "NormalEmployeeDorm";
    private static String mName = "unknow";
    private static int mDormSize = 100;
    private static int mLevel = 5;
    private  ArrayList<String> mGoodsList = new ArrayList<>();

    public NormalEmployeeDorm() {
        addGoods("单人床");
        addGoods("餐桌");
    }
    public void setName(String name) {
        mName = name;
    }
    public String getName(){
        return mName;
    }
    public void setDormSize(int size) {
        mDormSize = size;
    }
    public int getDormSize() {
        return mDormSize;
    }
    public void setmLevel(int level){
        mLevel = level;
    }
    public int getLevel(){
        return mLevel;
    }
    public void addGoods(String goods){
        mGoodsList.add(goods);
    }
    public ArrayList<String> getGoodsList(){
        return mGoodsList;
    }
    @NonNull
    @Override
    public Object clone()  {
        NormalEmployeeDorm ned = null;
        try{
            ned = (NormalEmployeeDorm)super.clone();
        }catch (CloneNotSupportedException e){
        }
        ned.mName = this.mName;
        ned.mDormSize = this.mDormSize;
        ned.mLevel = this.mLevel;
        ned.mGoodsList = (ArrayList<String>) this.mGoodsList.clone();
        return ned;
    }
}
