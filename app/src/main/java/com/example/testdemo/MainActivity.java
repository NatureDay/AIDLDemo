package com.example.testdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testdemo.model.Person;
import com.example.testdemo.service.TestService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ITestInterface mTestService;

    private boolean mIsBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);

        bindService();
    }

    private void bindService() {
        Intent intent = new Intent(this, TestService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unBundService() {
        unbindService(mServiceConnection);
    }

    @Override
    protected void onDestroy() {
        unBundService();
        super.onDestroy();
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mTestService = ITestInterface.Stub.asInterface(service);
            mIsBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mTestService = null;
            mIsBind = false;
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one: {
                if (mTestService != null) {
                    try {
                        mTestService.connect("客户端来请求拉");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case R.id.btn_two: {
                if (mTestService != null) {
                    try {
                        mTestService.disConnect("客户端断开链接啦");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case R.id.btn_three: {
                if (mTestService != null) {
                    try {
                        Person person = mTestService.getData(new Person("张三"));
                        Log.i("fff", "------client------getData-----" + JsonUtil.toJson(person));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }
}
