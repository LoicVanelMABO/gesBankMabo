package com.gesBankMabo.services;

import com.gesBankMabo.dto.ClientDto;
import com.gesBankMabo.dto.CompteDto;
import com.gesBankMabo.entities.Client;
import com.gesBankMabo.entities.CompteBancaire;
import com.gesBankMabo.entities.CompteCourant;
import com.gesBankMabo.entities.CompteEpagne;

import java.util.List;
import java.util.Optional;

public interface CompteService {
    void createCompte(CompteDto compteDto);

    List<CompteEpagne> findCompteEpargne();
    List<CompteCourant> findCompteCourant();

    Optional<CompteBancaire> findOne(String numCompte);
}
