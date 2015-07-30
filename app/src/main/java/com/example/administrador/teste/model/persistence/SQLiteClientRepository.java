package com.example.administrador.teste.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrador.teste.model.persistence.ClientContract.getContentValues;

/**
 * Created by Administrador on 23/07/2015.
 */
public class SQLiteClientRepository implements ClientRepository {

    // SINGLETON
    private static SQLiteClientRepository singletonInstance;    // instancia unica do objeto.

    private SQLiteClientRepository(){       // construtor que bloqueia mais de uma instanciação.
        super();
    }

    public static SQLiteClientRepository getInstance() {
        if(SQLiteClientRepository.singletonInstance == null){
            SQLiteClientRepository.singletonInstance = new SQLiteClientRepository();
        }
        return SQLiteClientRepository.singletonInstance;
    }
    // ==============================

    @Override
    public void save(Client client) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);        // ABRE A CONEXAO COM O BANCO.
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = ClientContract.getContentValues(client);

        if(client.getId() == null){
            db.insert(ClientContract.TABLE, null, values);          // INSERIR NO BD.
        }else{
            String where = ClientContract.ID + " = ? ";         // TEM QUE SER TUDO STRING;
            String[] args = {client.getId().toString()};        // ARGUMENTOS DO WHERE.

            db.update(ClientContract.TABLE, values, where, args );          // UPDATE NO BD.
        }

        db.close();
        helper.close();
    }

    @Override
    public List<Client> getAll() {

        //List<Client> clients = new ArrayList<>();

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        // FAZ A BUSCA NAS COLUNAS RECUPERANDO, OS VALORES, ATRAVEZ DO CURSOR.
        Cursor cursor = db.query(ClientContract.TABLE, ClientContract.COLUMNS, null, null, null, null, ClientContract.NAME);

        List<Client> clients = ClientContract.bindList(cursor);

        db.close();
        helper.close();

        return clients;     // RETORNA A LISTA.
    }

    @Override
    public void delete(Client client) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = ClientContract.ID + " = ? ";
        String[] args = {client.getId().toString()};   // ARGUMENTOS DO WHERE.

        db.delete(ClientContract.TABLE, where, args );

        db.close();
        helper.close();
    }
}
