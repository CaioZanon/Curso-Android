package com.example.administrador.teste.model.service;

import com.example.administrador.teste.R;
import com.example.administrador.teste.model.entities.ClientAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.net.HttpURLConnection.*;

/**
 * Created by CaioZanon on 27/07/2015.
 */
public final class CepService {


    private static  final String URL = "http://correiosapi.apphb.com/cep/";

    private  CepService(){
        super();
    }

    public static ClientAddress getAddressBy (String cep){

        ClientAddress address = null;

        try {

            URL url =new URL(URL+cep);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode != HTTP_OK){
                if(responseCode == HTTP_NOT_FOUND) {
                    throw new IOException("Error code:" + responseCode);
                }
            }

            InputStream inputStream = conn.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();

            address = objectMapper.readValue(inputStream, ClientAddress.class);

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  address;
    }


}
