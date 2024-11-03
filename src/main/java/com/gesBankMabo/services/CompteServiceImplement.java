package com.gesBankMabo.services;

import com.gesBankMabo.dto.CompteDto;
import com.gesBankMabo.entities.Client;
import com.gesBankMabo.entities.CompteBancaire;
import com.gesBankMabo.entities.CompteCourant;
import com.gesBankMabo.entities.CompteEpagne;
import com.gesBankMabo.enums.AccountStatus;
import com.gesBankMabo.repositories.ClientRepository;
import com.gesBankMabo.repositories.CompteBancaireRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompteServiceImplement implements CompteService{
    private final CompteBancaireRepository compteBancaireRepository;
    private final ClientRepository clientRepository;

    public CompteServiceImplement(final CompteBancaireRepository compteBancaireRepository, final ClientRepository clientRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void createCompte(CompteDto compteDto) {
        //je verifie si un compte est le clien existe
        Optional<Client> clientOptional = this.clientRepository.findById(compteDto.getClientId());
        if (clientOptional.isPresent() && (compteDto.getDecouvert() > 0 && compteDto.getTauxInteret() == 0)){
            CompteCourant compteCourant = new CompteCourant();
            compteCourant.setDecouvert(compteDto.getDecouvert());
            compteCourant.setCreatedAt(new Date());
            compteCourant.setBalance(compteDto.getBalance());
            compteCourant.setClient(clientOptional.get());
            compteCourant.setDevis(compteDto.getDevis());
            compteCourant.setNumCompte(generateAccoundNumber());

            this.compteBancaireRepository.save(compteCourant);
        }

        if (clientOptional.isPresent() && (compteDto.getDecouvert() == 0 && compteDto.getTauxInteret() > 0)){
            CompteEpagne compteEpagne = new CompteEpagne();
            compteEpagne.setTauxInteret(compteDto.getTauxInteret());
            compteEpagne.setCreatedAt(new Date());
            compteEpagne.setBalance(compteDto.getBalance());
            compteEpagne.setClient(clientOptional.get());
            compteEpagne.setDevis(compteDto.getDevis());
            compteEpagne.setStatus(AccountStatus.ACTIVATTED);
            compteEpagne.setNumCompte(generateAccoundNumber());

            this.compteBancaireRepository.save(compteEpagne);
        }
    }
    //Generer de facon auto un numéro de compte
    public static String generateAccoundNumber(){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        //les 4 first chiffre sont 0
        for(int i=0; i<4; i++){
            sb.append("0");
        }
        //les 4 suivant sont compris entre 0 et 1
        for(int i=0; i<4; i++){
            sb.append(r.nextInt(2));
        }
        //les 10 derniers sont aléatoires
        for(int i=0; i<10; i++){
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public List<CompteEpagne> findCompteEpargne() {
        List <CompteEpagne> listEpargne = new ArrayList<>();
        for(CompteBancaire c: compteBancaireRepository.findAll()){
            if(c instanceof CompteEpagne){
                listEpargne.add((CompteEpagne) c);
            }
        }

        return listEpargne;
    }

    @Override
    public List<CompteCourant> findCompteCourant() {
        List <CompteCourant> listCourant = new ArrayList<>();
        for(CompteBancaire c: compteBancaireRepository.findAll()){
            if(c instanceof CompteCourant){
                listCourant.add((CompteCourant) c);
            }
        }

        return listCourant;
    }

    @Override
    public CompteBancaire findOne(String numCompte) {
        Optional<CompteBancaire> compteBancaire = this.compteBancaireRepository.findByNumCompte(numCompte);
        return compteBancaire.get();
    }

    @Override
    public boolean activerCompte(String numCompte) {
        Optional<CompteBancaire> compte = this.compteBancaireRepository.findByNumCompte(numCompte);
        if(compte.isPresent() && compte.get().getStatus().equals(AccountStatus.SUSPENDED)){
            CompteBancaire c = compte.get();
            c.setStatus(AccountStatus.ACTIVATTED);
            this.compteBancaireRepository.save(c);

            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean suspendreCompte(String numCompte) {
        Optional<CompteBancaire> compte = this.compteBancaireRepository.findByNumCompte(numCompte);
        if(compte.isPresent() && compte.get().getStatus().equals(AccountStatus.ACTIVATTED)){
            CompteBancaire c = compte.get();
            c.setStatus(AccountStatus.SUSPENDED);
            this.compteBancaireRepository.save(c);

            return true;
        }else {
            return false;
        }
    }
}
