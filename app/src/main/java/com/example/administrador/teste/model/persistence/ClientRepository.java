package com.example.administrador.teste.model.persistence;

import com.example.administrador.teste.model.entities.Client;

import java.util.List;

/**
 * Created by Administrador on 21/07/2015.
 */
public interface ClientRepository {

    public void save(Client client); // SAVE / EDIT

    public List<Client> getAll();

    public void delete(Client client);




}
