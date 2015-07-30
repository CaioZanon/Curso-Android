package com.example.administrador.teste;

import android.app.Application;

import com.example.administrador.teste.util.AppUtil;

/**
 * Created by Administrador on 23/07/2015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        AppUtil.CONTEXT = getApplicationContext();
        super.onCreate();
    }
}
