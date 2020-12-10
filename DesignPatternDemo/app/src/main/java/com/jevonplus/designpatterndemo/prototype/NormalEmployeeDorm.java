package com.jevonplus.designpatterndemo.prototype;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NormalEmployeeDorm implements Cloneable {
    private static String mName;
    private static int mDormSize;
    private static int mLevel;
    private static ArrayList<String> mGoodsList = new ArrayList<>();

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
    protected Object clone() throws CloneNotSupportedException {
        NormalEmployeeDorm ned = null;
        try{
            super.clone();
        }catch (CloneNotSupportedException e){

        }
        ned.mName = this.mName;
        ned.mDormSize = this.mDormSize;
        ned.mLevel = this.mLevel;
        ned.mGoodsList = (ArrayList<String>) this.mGoodsList.clone();
        return ned;
    }
}
