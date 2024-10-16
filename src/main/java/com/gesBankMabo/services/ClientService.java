package com.gesBankMabo.services;

import com.gesBankMabo.dto.ClientDto;
import com.gesBankMabo.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService{
    void createNewClient(ClientDto clientDto);

    List<Client> findAll();

    Optional<Client> findOne(long id);
}
