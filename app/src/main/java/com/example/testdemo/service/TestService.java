package com.example.testdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.testdemo.ITestInterface;
import com.example.testdemo.JsonUtil;
import com.example.testdemo.model.Person;

public class TestService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("fff", "------TestService-------------onBind-------------");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.i("fff", "------TestService-------------onDestroy-------------");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private final ITestInterface.Stub mBinder = new ITestInterface.Stub() {

        @Override
        public void connect(String msg) throws RemoteException {
            Log.i("fff", "------TestService---mBinder--------connect------" + msg);
        }

        @Override
        public void disConnect(String msg) throws RemoteException {
            Log.i("fff", "------TestService---mBinder--------disConnect------" + msg);
        }

        @Override
        public Person getData(Person src) throws RemoteException {
            Log.i("fff", "------TestService---mBinder--------getData------" + JsonUtil.toJson(src));
            src.setName("处理过了：" + src.getName());
            return src;
        }
    };
}
