package com.example.administrador.teste.model.persistence;

import android.database.Cursor;

import com.example.administrador.teste.model.entities.Login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaioZanon on 30/07/2015.
 */
public class LoginContract {

    public static final String TABLE = "users";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static final String[] COLUMNS = { ID, USERNAME ,PASSWORD};

    public static String getCreateTable(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append( ID + " INTEGER PRIMARY KEY, " );
        sql.append( USERNAME + " TEXT, " );
        sql.append( PASSWORD + " TEXT " );
        sql.append(" ); ");

        return sql.toString();
    }


    public static String setDataTable(){
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(USERNAME + ", ");
        sql.append(PASSWORD);
        sql.append(" ) ");
        sql.append(" VALUES ");
        sql.append(" ( ");
        sql.append( " 'admin', ");
        sql.append( " 'admin' ");
        sql.append(" ); ");

        return sql.toString();
    }


    public static Login bindCursor(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext() ){
            Login login = new Login();
            login.setId(cursor.getInt(cursor.getColumnIndex(LoginContract.ID)));
            login.setUsername(cursor.getString(cursor.getColumnIndex(LoginContract.USERNAME)));
            login.setPassword(cursor.getString(cursor.getColumnIndex(LoginContract.PASSWORD)));
            return login;
        }

        return null;
    }

    public static Login bindUser(Cursor cursor){
            return bindCursor(cursor);
    }


}
