package com.example.boundservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Boolean  isBound = false;
    private ExampleService eService;

    private TextView textView;
    private DisplayRandomAsyncTask displayRandomAsyncTask;



    //Need create ServiceConnection Interface

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            ExampleService.LocalBinder binder = (ExampleService.LocalBinder) service;

            eService = binder.getService();

            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.textView);

        displayRandomAsyncTask = new DisplayRandomAsyncTask();

        displayRandomAsyncTask.execute();




    }


    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, ExampleService.class);

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


    }

    @Override
    protected void onStop() {
        super.onStop();

        if (isBound && eService != null ) {

            unbindService(serviceConnection);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != displayRandomAsyncTask) {

            if (!displayRandomAsyncTask.isCancelled()) {

                displayRandomAsyncTask.cancel(true);
            }


        }

    }

    private class DisplayRandomAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i =0; i<10 ; i++) {

                if (isBound && eService !=null) {

                    publishProgress(eService.getRandom());

                }
                SystemClock.sleep(1000);

            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            textView.setText(String.valueOf(values[0]));

        }
    }





}