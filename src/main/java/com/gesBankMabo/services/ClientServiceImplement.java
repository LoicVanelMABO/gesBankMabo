package com.gesBankMabo.services;

import com.gesBankMabo.dto.ClientDto;
import com.gesBankMabo.entities.Client;
import com.gesBankMabo.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

public class ClientServiceImplement implements ClientService{
    final  ClientRepository clientRepository;
    ClientServiceImplement(final ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public void createNewClient(ClientDto clientDto) {
        Client client = new Client();
        //mets les attributs
        client.setNom(clientDto.getNom());
        client.setBirthday(clientDto.getBirthday());
        client.setAddresse(clientDto.getAddresse());
        client.setEmail(client.getEmail());
        client.setPrenom(clientDto.getPrenom());
        client.setTel(clientDto.getTel());

        this.clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Optional<Client> findOne(long id) {
        return this.clientRepository.findById(id);
    }
}
