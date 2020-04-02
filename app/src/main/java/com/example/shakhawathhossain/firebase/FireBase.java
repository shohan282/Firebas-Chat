package com.example.shakhawathhossain.firebase;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Button;

import com.firebase.client.Firebase;

import java.util.Locale;

/**
 * Created by Shakhawath Hossain on 25/3/2020.
 */

public class FireBase extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);

    }


}