package com.example.administrador.teste.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrador.teste.R;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindLoginService();

    }

    private void bindLoginService(){
        buttonLogin = (Button) findViewById(R.id.btnlogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent goToPrincipal = new Intent(LoginActivity.this, ClientListActivity.class);
                startActivity(goToPrincipal);
            }
        });
    }
}
