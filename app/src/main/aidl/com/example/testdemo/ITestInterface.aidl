// ITestInterface.aidl
package com.example.testdemo;

// Declare any non-default types here with import statements
import com.example.testdemo.model.Person;

interface ITestInterface {

    void connect(String msg);

    void disConnect(String msg);

    Person getData(in Person src);
}

