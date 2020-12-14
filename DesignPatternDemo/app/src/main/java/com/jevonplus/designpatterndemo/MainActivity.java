package com.jevonplus.designpatterndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jevonplus.designpatterndemo.abstractfactory.ColorFactory;
import com.jevonplus.designpatterndemo.abstractfactory.VehicleFactory;
import com.jevonplus.designpatterndemo.builder.Employee;
import com.jevonplus.designpatterndemo.prototype.NormalEmployeeDorm;
import com.jevonplus.designpatterndemo.singleton.ColorFactoryManager;
import com.jevonplus.designpatterndemo.singleton.VehicleFactoryManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    private DesignService mDesignService;
    private static final int VEHICLE_FACTORY = 1;
    private static final int COLOR_FACTORY = 2;
    //Factory Method
    private Button mFactoryMethod;
    private RadioGroup mFactoryGroup;
    private int mVehicleType = -1;

    //Abstract Factory
    private RadioGroup mFactoryVehicleGroup,mFactoryColorGroup;
    private Button mAbsFactory;
    private int mColorType = -1;

    //Singleton
    private EditText mSingletonName, mSingletonAge;
    private RadioGroup mSingletonId,mSingletonSex;
    private Button mSingletonAppoint, mSingletonQuery;
    private int mSignletonFactoryId,mSignletonSex;

    //Builder
    private EditText mBuilderName, mBuilderAge, mBuilderPosition;
    private RadioGroup mBuilderId,mBuilderSex;
    private Button mBuilderCreate;

    //prototypt
    private Button mPrototypeQuery,mPrototypeAdd;
    private EditText mPrototypeGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,DesignService.class);
        this.bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
        //Factory Method
        mFactoryMethod = (Button)findViewById(R.id.custom_btn);
        mFactoryMethod.setOnClickListener(mFactoryMethodListener);
        mFactoryGroup = (RadioGroup) findViewById(R.id.factory_method_group);
        mFactoryGroup.setOnCheckedChangeListener(this);
        //Abstract Factory
        mFactoryVehicleGroup = (RadioGroup)findViewById(R.id.abstract_factory_vehicle_group);
        mFactoryVehicleGroup.setOnCheckedChangeListener(this);
        mFactoryColorGroup = (RadioGroup)findViewById(R.id.abstract_factory_color_group);
        mFactoryColorGroup.setOnCheckedChangeListener(this);
        mAbsFactory = (Button)findViewById(R.id.absfactory_btn);
        mAbsFactory.setOnClickListener(mAbsFactoryListener);
        //Singleton
        mSingletonId = (RadioGroup)findViewById(R.id.singleton_id_group);
        mSingletonId.setOnCheckedChangeListener(this);
        mSingletonName = (EditText)findViewById(R.id.singleton_name);
        mSingletonAge = (EditText)findViewById(R.id.singleton_age);
        mSingletonSex = (RadioGroup)findViewById(R.id.singleton_sex_group);
        mSingletonSex.setOnCheckedChangeListener(this);
        mSingletonAppoint = (Button)findViewById(R.id.singleton_appoint);
        mSingletonAppoint.setOnClickListener(mSingletonAppointListener);
        mSingletonQuery = (Button)findViewById(R.id.singleton_query);
        mSingletonQuery.setOnClickListener(mSingletonQueryListener);
        //Builder
        mBuilderId = (RadioGroup)findViewById(R.id.builder_id_group);
        mBuilderId.setOnCheckedChangeListener(this);
        mBuilderName = (EditText)findViewById(R.id.builder_name);
        mBuilderAge = (EditText)findViewById(R.id.builder_age);
        mBuilderSex = (RadioGroup)findViewById(R.id.builder_sex_group);
        mBuilderSex.setOnCheckedChangeListener(this);
        mBuilderPosition = (EditText)findViewById(R.id.builder_position);
        mBuilderCreate = (Button)findViewById(R.id.builder_create);
        mBuilderCreate.setOnClickListener(mBudilerCreateListener);
        //Prototype
        mPrototypeQuery = (Button)findViewById(R.id.prototype_btn);
        mPrototypeQuery.setOnClickListener(mPrototypeQueryListener);
        mPrototypeAdd = (Button)findViewById(R.id.prototype_add_goods);
        mPrototypeAdd.setOnClickListener(mPrototypeAddListener);
        mPrototypeGoods = (EditText)findViewById(R.id.prototype_goods);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDesignService = ((DesignService.DesignServiceBinder)service).getDesignService();
            Log.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //Factory Method button listener
    View.OnClickListener mFactoryMethodListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String custom;
            if(mDesignService.customVehicleToFactory(mVehicleType) == null){
                custom = "No this style Vehicle";
            } else {
                custom = mDesignService.customVehicleToFactory(mVehicleType).produce();
            }
            Toast.makeText(MainActivity.this,custom,Toast.LENGTH_SHORT).show();
        }
    };
    //Abstract Factory button listener
    View.OnClickListener mAbsFactoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StringBuffer sb = new StringBuffer();
            Log.d(TAG, "mAbsFactoryListener mColorType = " + mColorType);
            if(mColorType > 0){
                ColorFactory factory = (ColorFactory)mDesignService.getFactory(COLOR_FACTORY);
                sb.append(factory.getColor(mColorType).fill());
            }

            Log.d(TAG, "mAbsFactoryListener vehicleType = " + mVehicleType);
            if(mVehicleType > 0){
                VehicleFactory factory = (VehicleFactory)mDesignService.getFactory(VEHICLE_FACTORY);
                sb.append(factory.getVehicle(mVehicleType).produce());
            }

            if(sb.length() == 0) {
                sb.append("default WhiteTruck");
            }
            Toast.makeText(MainActivity.this,sb.toString(),Toast.LENGTH_SHORT).show();
        }
    };

    //Singleton
    View.OnClickListener mSingletonAppointListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = null;
            int age;
            try {
                name = mSingletonName.getText().toString();
                age = Integer.parseInt(mSingletonAge.getText().toString());
            }catch (Exception e) {
                mSignletonFactoryId = 1;
                age = 0;
            }

            if(mSignletonFactoryId == 1) {
                ColorFactoryManager.getInstance().setName(name);
                ColorFactoryManager.getInstance().setAge(age);
                if(mSignletonSex == 1){
                    ColorFactoryManager.getInstance().setSex("男");
                }else if(mSignletonSex == 2){
                    ColorFactoryManager.getInstance().setSex("女");
                }
            } else {
                ColorFactoryManager.getInstance().setName(name);
                ColorFactoryManager.getInstance().setAge(age);
                if(mSignletonSex == 1){
                    ColorFactoryManager.getInstance().setSex("男");
                }else if(mSignletonSex == 2){
                    ColorFactoryManager.getInstance().setSex("女");
                }
            }
            Log.d(TAG, "set appoint finish");
            Toast.makeText(MainActivity.this,"任命完成",Toast.LENGTH_SHORT).show();
        }
    };
    View.OnClickListener mSingletonQueryListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            StringBuffer sb = new StringBuffer();
            sb.append("我是");
            if(mSignletonFactoryId == 1) {
                sb.append(ColorFactoryManager.getInstance().getName());
                sb.append(",年龄");
                sb.append(ColorFactoryManager.getInstance().getAge());
                sb.append(",性别");
                sb.append(ColorFactoryManager.getInstance().getSex());
            } else {
                sb.append(VehicleFactoryManager.getInstance().getName());
                sb.append(",年龄");
                sb.append(VehicleFactoryManager.getInstance().getAge());
                sb.append(",性别");
                sb.append(VehicleFactoryManager.getInstance().getSex());
            }
            Toast.makeText(MainActivity.this,sb.toString(),Toast.LENGTH_SHORT).show();
        }
    };

    //Builder
    View.OnClickListener mBudilerCreateListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            int age = -1;
            int department = 1;
            String sex = null;
            if((mBuilderAge.getText().toString() == null) ||
                    mBuilderAge.getText().toString().equals("")) {
                age = 30;
            } else {
                age = Integer.parseInt(mBuilderAge.getText().toString());
            }
            if(mSignletonSex == 1){
                sex = "男";
            }else {
                sex = "女";
            }
            Employee ee = new Employee.Builder()
                    .setName(mBuilderName.getText().toString())
                    .setAge(age)
                    .setSex(sex)
                    .setPosition(mBuilderPosition.getText().toString())
                    .setDepartment(mSignletonFactoryId)
                    .build();
            StringBuffer sb = new StringBuffer();
            int i = ee.getDepartment();
            if(i == 1){
                sb.append("车辆厂");
            } else if(i == 2) {
                sb.append("颜色厂");
            }
            sb.append(ee.getName());
            sb.append("年龄：" + ee.getAge());
            sb.append("性别：" + ee.getSex());
            sb.append("职位：" + ee.getPosition());
            Toast.makeText(MainActivity.this,sb.toString(),Toast.LENGTH_SHORT).show();
        }
    };

    //prototype
    View.OnClickListener mPrototypeQueryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalEmployeeDorm mDorm = new NormalEmployeeDorm();
            Log.d(TAG, "mPrototypeQueryListener mDorm = " + mDorm);
            StringBuffer sb = new StringBuffer();
            sb.append("姓名： ");
            sb.append(mDorm.getName());
            sb.append(" 级别：");
            sb.append(mDorm.getLevel());
            sb.append(" 宿舍面积：");
            sb.append(mDorm.getDormSize());
            sb.append(" 室内物品： ");
            ArrayList<String> mList = mDorm.getGoodsList();
            Log.d(TAG, "mPrototypeQueryListener mList SIZE = " + mList.size());
            for(String goods:mList){
                sb.append(goods + ", ");
            }
            Toast.makeText(MainActivity.this,sb.toString(),Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener mPrototypeAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalEmployeeDorm mDorm = new NormalEmployeeDorm();
            StringBuffer sb = new StringBuffer();
            NormalEmployeeDorm mNewDorm = (NormalEmployeeDorm) mDorm.clone();
            String item = mPrototypeGoods.getText().toString();
            if(item == null || item.equals("")) {
                sb.append(mDorm.getName());
                sb.append("没有添加任何东西");
            } else {
                mNewDorm.setName("张三");
                sb.append(mNewDorm.getName());
                mNewDorm.addGoods(item);
                sb.append("现在有");
                ArrayList<String> mList = mNewDorm.getGoodsList();
                for(String goods:mList){
                    sb.append(goods + ", ");
                }
            }
            Toast.makeText(MainActivity.this,sb.toString(),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.factory_method_car_btn:
            case R.id.abstract_factory_car_btn:
                mVehicleType = 1;
                break;
            case R.id.factory_method_truck_btn:
            case R.id.abstract_factory_truck_btn:
                mVehicleType = 2;
                break;
            case R.id.factory_method_bus_btn:
            case R.id.abstract_factory_bus_btn:
                mVehicleType = 3;
                break;
            case R.id.abstract_factory_red_btn:
                mColorType = 1;
                break;
            case R.id.abstract_factory_white_btn:
                mColorType = 2;
                break;
            case R.id.abstract_factory_blue_btn:
                mColorType = 3;
                break;
            case R.id.singleton_vehicle_btn:
            case R.id.builder_vehicle_btn:
                mSignletonFactoryId = 2;
                break;
            case R.id.singleton_color_btn:
            case R.id.builder_color_btn:
                mSignletonFactoryId = 1;
                break;
            case R.id.singleton_man_btn:
            case R.id.builder_man_btn:
                mSignletonSex = 1;
                break;
            case R.id.singleton_women_btn:
            case R.id.builder_women_btn:
                mSignletonSex = 2;
                break;

        }
    }
}
