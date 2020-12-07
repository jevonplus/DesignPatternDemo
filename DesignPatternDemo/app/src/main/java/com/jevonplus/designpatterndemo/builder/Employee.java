package com.jevonplus.designpatterndemo.builder;
import com.jevonplus.designpatterndemo.singleton.Person;

public class Employee extends Person {
    private String mPosition;
    private int mDepartment;

    private Employee() {

    }
    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getAge() {
        return mAge;
    }

    @Override
    public void setAge(int age) {

    }

    @Override
    public String getSex() {
        return mSex;
    }

    @Override
    public void setSex(String sex) {

    }

    public String getPosition(){
        return mPosition;
    }

    public int getDepartment() {
        return mDepartment;
    }

    public static class Builder {
       private String mName = "unKnow";
       private String mSex = "男";
       private int mAge = 30;
        private String mPosition = "工人";
        private int mDepartment = 1;
        public Builder() {
        }

        public Employee build() {
            Employee ee = new Employee();
            ee.mName = mName;
            ee.mSex = mSex;
            ee.mAge = mAge;
            ee.mPosition = mPosition;
            ee.mDepartment = mDepartment;
            return ee;
        }

        public Builder setName(String name) {
            if(name == null || name.equals("")) {
                return this;
            }
            mName = name;
            return this;
        }
        public Builder setAge(int age) {
            mAge = age;
            return this;
        }
        public Builder setSex(String sex) {
            if(sex == null || sex.equals("")) {
                return this;
            }
            mSex = sex;
            return this;
        }
        public Builder setPosition(String position){
            if(position == null || position.equals("")) {
                return  this;
            }
            mPosition = position;
            return this;
        }
        public Builder setDepartment(int department) {
            mDepartment = department;
            return this;
        }
    }
}
