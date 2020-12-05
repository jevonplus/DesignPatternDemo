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
import android.widget.Toast;

import com.jevonplus.designpatterndemo.abstractfactory.ColorFactory;
import com.jevonplus.designpatterndemo.abstractfactory.VehicleFactory;
import com.jevonplus.designpatterndemo.builder.Employee;
import com.jevonplus.designpatterndemo.singleton.ColorFactoryManager;
import com.jevonplus.designpatterndemo.singleton.VehicleFactoryManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DesignService mDesignService;
    private static final int VEHICLE_FACTORY = 1;
    private static final int COLOR_FACTORY = 2;
    //Factory Method
    private Button mFactoryMethod;
    private EditText mFactoryMethodEdit;
    //Abstract Factory
    private EditText mAbsFactoryColorEdit,mAsbFactoryVehicleEdit;
    private Button mAbsFactory;

    //Singleton
    private EditText mSingletonId, mSingletonName, mSingletonAge, mSingletonSex;
    private Button mSingletonAppoint, mSingletonQuery;

    //Builder
    private EditText mBuilderId, mBuilderName, mBuilderAge, mBuilderSex, mBuilderPosition;
    private Button mBuilderCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,DesignService.class);
        this.bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
        //Factory Method
        mFactoryMethod = (Button)findViewById(R.id.custom_btn);
        mFactoryMethodEdit = (EditText)findViewById(R.id.custom_type_edit);
        mFactoryMethod.setOnClickListener(mFactoryMethodListener);
        //Abstract Factory
        mAbsFactoryColorEdit = (EditText)findViewById(R.id.color_edit) ;
        mAsbFactoryVehicleEdit = (EditText)findViewById(R.id.vehicle_edit);
        mAbsFactory = (Button)findViewById(R.id.absfactory_btn);
        mAbsFactory.setOnClickListener(mAbsFactoryListener);
        //Singleton
        mSingletonId = (EditText)findViewById(R.id.singleton_id);
        mSingletonName = (EditText)findViewById(R.id.singleton_name);
        mSingletonAge = (EditText)findViewById(R.id.singleton_age);
        mSingletonSex = (EditText)findViewById(R.id.singleton_sex);
        mSingletonAppoint = (Button)findViewById(R.id.singleton_appoint);
        mSingletonAppoint.setOnClickListener(mSingletonAppointListener);
        mSingletonQuery = (Button)findViewById(R.id.singleton_query);
        mSingletonQuery.setOnClickListener(mSingletonQueryListener);
        //Builder
        mBuilderId = (EditText)findViewById(R.id.builder_id);
        mBuilderName = (EditText)findViewById(R.id.builder_name);
        mBuilderAge = (EditText)findViewById(R.id.builder_age);
        mBuilderSex = (EditText)findViewById(R.id.builder_sex);
        mBuilderPosition = (EditText)findViewById(R.id.builder_position);
        mBuilderCreate = (Button)findViewById(R.id.builder_create);
        mBuilderCreate.setOnClickListener(mBudilerCreateListener);
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
            int type = Integer.parseInt(mFactoryMethodEdit.getText().toString());
            Log.d(TAG, "mFactoryMethod type = " + type + " mDesignService = " + mDesignService);
            String custom;
            if(mDesignService.customVehicleToFactory(type) == null){
                custom = "No this style Vehicle";
            } else {
                custom = mDesignService.customVehicleToFactory(type).produce();
            }
            Toast.makeText(MainActivity.this,custom,Toast.LENGTH_SHORT).show();
        }
    };
    //Abstract Factory button listener
    View.OnClickListener mAbsFactoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            StringBuffer sb = new StringBuffer();
            int colortType = -1;
            int vehicleType = -1;
            try {
                colortType = Integer.parseInt(mAbsFactoryColorEdit.getText().toString());
            }catch (Exception e) {
                colortType = 1;
            }

            Log.d(TAG, "mAbsFactoryListener colortType = " + colortType);
            if(colortType > 0){
                ColorFactory factory = (ColorFactory)mDesignService.getFactory(COLOR_FACTORY);
                sb.append(factory.getColor(colortType).fill());
            }

            try {
                vehicleType = Integer.parseInt(mAsbFactoryVehicleEdit.getText().toString());
            }catch (Exception e) {
                vehicleType = 1;
            }

            Log.d(TAG, "mAbsFactoryListener vehicleType = " + vehicleType);
            if(vehicleType > 0){
                VehicleFactory factory = (VehicleFactory)mDesignService.getFactory(VEHICLE_FACTORY);
                sb.append(factory.getVehicle(vehicleType).produce());
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
            int id = -1;
            String name = null;
            String sex = "女";
            int age;
            try {
                id = Integer.parseInt(mSingletonId.getText().toString());
                name = mSingletonName.getText().toString();
                age = Integer.parseInt(mSingletonAge.getText().toString());
                sex = mSingletonSex.getText().toString();
            }catch (Exception e) {
                id = 1;
                age = 30;
            }

            if(id == 1) {
                ColorFactoryManager.getInstance().setName(name);
                ColorFactoryManager.getInstance().setAge(age);
                ColorFactoryManager.getInstance().setSex(sex);
            } else {
                VehicleFactoryManager.getInstance().setName(name);
                VehicleFactoryManager.getInstance().setAge(age);
                VehicleFactoryManager.getInstance().setSex(sex);
            }
            Log.d(TAG, "set appoint finish");
            Toast.makeText(MainActivity.this,"任命完成",Toast.LENGTH_SHORT).show();
        }
    };
    View.OnClickListener mSingletonQueryListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            StringBuffer sb = new StringBuffer();
            int id = -1;
            try {
                id = Integer.parseInt(mSingletonId.getText().toString());
            }catch (Exception e) {
                id = 1;
            }
            sb.append("我是");
            if(id == 1) {
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
            Employee ee = new Employee.Builder()
                    .setName(mBuilderName.getText().toString())
                    .setAge(Integer.parseInt(mBuilderAge.getText().toString()))
                    .setSex(mBuilderSex.getText().toString())
                    .setPosition(mBuilderPosition.getText().toString())
                    .setDepartment(Integer.parseInt(mBuilderId.getText().toString()))
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
}
