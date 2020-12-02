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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DesignService mDesignService;
    private Button mFactoryMethod;
    private EditText mFactoryMethodEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFactoryMethod = (Button)findViewById(R.id.custom_btn);
        mFactoryMethodEdit = (EditText)findViewById(R.id.custom_type_edit);
        Intent intent = new Intent(this,DesignService.class);
        this.bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
        mFactoryMethod.setOnClickListener(new View.OnClickListener() {
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
        });
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
}
