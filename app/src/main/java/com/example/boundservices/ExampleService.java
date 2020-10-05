package com.example.boundservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;


//Need to extends class Service
//After add method override onBind
public class ExampleService extends Service {

    private IBinder binder = new LocalBinder();
    private Random random = new Random();



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    //Need to create inner class to return class ExampleService
    public class LocalBinder extends Binder {

        ExampleService getService() {

            return  ExampleService.this;

        }

    }

    public int getRandom() {

        return random.nextInt();
    }





}
