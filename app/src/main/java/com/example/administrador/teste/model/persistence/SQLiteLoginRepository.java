package com.example.administrador.teste.model.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.teste.model.entities.Login;
import com.example.administrador.teste.util.AppUtil;

import java.util.List;

/**
 * Created by CaioZanon on 30/07/2015.
 */
public class SQLiteLoginRepository implements LoginRepository {

    // SINGLETON
    private static SQLiteLoginRepository singletonInstance;

    private SQLiteLoginRepository(){

        super();
    }

    public static SQLiteLoginRepository getInstance() {
        if(SQLiteLoginRepository.singletonInstance == null){
            SQLiteLoginRepository.singletonInstance = new SQLiteLoginRepository();
        }
        return SQLiteLoginRepository.singletonInstance;
    }


    @Override
    public Login getUsers() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, null, null, null, null, LoginContract.ID);

        Login login = LoginContract.bindUser(cursor);
        db.close();
        helper.close();

        return login;
    }
}
