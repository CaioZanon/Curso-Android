package com.example.administrador.teste.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.teste.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 23/07/2015.
 */
public class ClientContract  {

    public static final String TABLE = "client";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String CIDADE = "cidade";
    public static final String ESTADO = "estado";
    public static final String LOGRADOURO = "logradouro";
    public static final String TIPOLOGRADOURO = "tipologradouro";
    public static final String CEP = "cep";
    public static final String BAIRRO = "bairro";


    public static final String[] COLUMNS = { ID, NAME, AGE, PHONE, ADDRESS , CIDADE,ESTADO, LOGRADOURO , TIPOLOGRADOURO, CEP , BAIRRO};

    public static String getCreateTable(){
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
            sql.append(" ( ");
                sql.append( ID + " INTEGER PRIMARY KEY, " );
                sql.append( NAME + " TEXT, " );
                sql.append( AGE + " INTEGER, " );
                sql.append( PHONE + " TEXT, " );
                sql.append( ADDRESS + " TEXT, " );
                sql.append(CIDADE + " TEXT, ");
                sql.append(ESTADO +  " TEXT, ");
                sql.append(LOGRADOURO + " TEXT, ");
                sql.append(TIPOLOGRADOURO + " TEXT, ");
                sql.append(CEP + " TEXT, ");
                sql.append(BAIRRO + " TEXT ");
            sql.append(" ); ");

        return sql.toString();
    }

    public static ContentValues getContentValues(Client client){
        ContentValues values = new ContentValues();

        values.put(ClientContract.ID, client.getId());
        values.put(ClientContract.NAME, client.getName());
        values.put(ClientContract.AGE, client.getAge());
        values.put(ClientContract.ADDRESS, client.getAddress());
        values.put(ClientContract.PHONE, client.getPhone());
        values.put(ClientContract.CIDADE, client.getCidade());
        values.put(ClientContract.ESTADO, client.getEstado());
        values.put(ClientContract.LOGRADOURO, client.getLogradouro());
        values.put(ClientContract.TIPOLOGRADOURO, client.getTipoDeLogradouro());
        values.put(ClientContract.CEP, client.getCep());
        values.put(ClientContract.BAIRRO, client.getBairro());

        return values;
    }

    public static Client bindCursor(Cursor cursor){
        if(!cursor.isBeforeFirst() || cursor.moveToNext() ){
            Client client = new Client();

            client.setId(cursor.getInt(cursor.getColumnIndex(ClientContract.ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.AGE)));
            client.setPhone(cursor.getString(cursor.getColumnIndex(ClientContract.PHONE)));
            client.setAddress(cursor.getString(cursor.getColumnIndex(ClientContract.ADDRESS)));

            client.setCidade(cursor.getString(cursor.getColumnIndex(ClientContract.CIDADE)));
            client.setEstado(cursor.getString(cursor.getColumnIndex(ClientContract.ESTADO)));
            client.setLogradouro(cursor.getString(cursor.getColumnIndex(ClientContract.LOGRADOURO)));
            client.setTipoDeLogradouro(cursor.getString(cursor.getColumnIndex(ClientContract.TIPOLOGRADOURO)));
            client.setCep(cursor.getString(cursor.getColumnIndex(ClientContract.CEP)));
            client.setBairro(cursor.getString(cursor.getColumnIndex(ClientContract.BAIRRO)));
            return client;
        }

        return null;
    }

    public static List<Client> bindList(Cursor cursor){

        final List<Client> clientsOrders = new ArrayList<Client>();

        while (cursor.moveToNext()){
            clientsOrders.add(bindCursor(cursor));
        }

        return clientsOrders;
    }
}
