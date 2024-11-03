package com.gesBankMabo.Controller;

import com.gesBankMabo.dto.ClientDto;
import com.gesBankMabo.entities.Client;
import com.gesBankMabo.services.ClientService;
import com.gesBankMabo.services.ClientServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "v1")
public class ClientController {
    @Autowired
    private final ClientServiceImplement clientService;

    public ClientController(ClientServiceImplement clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    void createClient(@RequestBody ClientDto clientDto){
        this.clientService.createNewClient(clientDto);
    }

    @GetMapping("/clients")
    List<Client> listClients(){
        return this.clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    Optional<Client> oneClient(@PathVariable("id") long id){
        return this.clientService.findOne(id);
    }
}