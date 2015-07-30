package com.example.administrador.teste.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.teste.R;
import com.example.administrador.teste.model.entities.Login;
import com.example.administrador.teste.util.FormHelper;

import java.util.List;

/**
 * Created by Administrador on 20/07/2015.
 */
public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText EditTextuser;
    EditText EditTextpassword;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        bindLoginService();

    }

    private Login bindLoginUsers() {
        return Login.getUsers();
    }

    private void bindLoginService(){
        buttonLogin = (Button) findViewById(R.id.btnlogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bindFields();
                if(FormHelper.requiredValidate(LoginActivity.this,EditTextuser , EditTextpassword )) {
                    Login login = bindLoginUsers();
                    if (EditTextuser.getText().toString().equals(login.getUsername().toString()) && EditTextpassword.getText().toString().equals(login.getPassword().toString())) {
                        Intent goToPrincipal = new Intent(LoginActivity.this, ClientListActivity.class);
                        startActivity(goToPrincipal);
                    }else
                        Toast.makeText(LoginActivity.this, getString(R.string.msgErrorUsernamePasswordInvalido), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void bindFields() {
        EditTextuser = (EditText) findViewById(R.id.editTextUserName);
        EditTextpassword = (EditText) findViewById(R.id.editTextPassword);
    }
}
