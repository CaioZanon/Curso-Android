package com.example.administrador.teste.model.persistence;

import com.example.administrador.teste.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 21/07/2015.
 */
public class MemoryClientRepository implements ClientRepository {

    private static MemoryClientRepository singletonInstance;    // instancia unica do objeto.

    private List<Client> clients;

    private MemoryClientRepository(){       // construtor que bloqueia mais de uma instanciação.
        super();
        clients = new ArrayList<>();
    }




    public static ClientRepository getInstance() {
        if(MemoryClientRepository.singletonInstance == null){
            MemoryClientRepository.singletonInstance = new MemoryClientRepository();
        }
        return MemoryClientRepository.singletonInstance;
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public void delete(Client client) {
        clients.remove(client);
    }
}
