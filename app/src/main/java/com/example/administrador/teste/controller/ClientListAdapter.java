package com.example.administrador.teste.controller;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.R;

import java.util.List;

/**
 * Created by Administrador on 20/07/2015.
 */
public class ClientListAdapter extends BaseAdapter {

    private List<Client> clientList;
    private Activity context;

    public ClientListAdapter(Activity context, List<Client> clientList){
        this.clientList = clientList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return clientList.size();
    }

    @Override
    public Client getItem(int position) {
        return clientList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = context.getLayoutInflater().inflate(R.layout.client_list_item, parent, false);
        Client client = getItem(position);

        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewName.setText(client.getName() );

        TextView textViewAge = (TextView) view.findViewById(R.id.textViewAge);
        textViewAge.setText(client.getAge().toString() );

        return view;
    }

    public void setClients(List<Client> clients){
        this.clientList = clients;
    }
}
