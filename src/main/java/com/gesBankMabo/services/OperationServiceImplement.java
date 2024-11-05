package com.gesBankMabo.services;

import com.gesBankMabo.dto.OperationDto;
import com.gesBankMabo.entities.CompteBancaire;
import com.gesBankMabo.entities.Operation;
import com.gesBankMabo.enums.AccountStatus;
import com.gesBankMabo.enums.TypeOperation;
import com.gesBankMabo.repositories.CompteBancaireRepository;
import com.gesBankMabo.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OperationServiceImplement implements OperationService{
    private final CompteBancaireRepository compteBancaireRepository;
    private final OperationRepository operationRepository;

    public OperationServiceImplement(CompteBancaireRepository compteBancaireRepository, OperationRepository operationRepository) {
        this.compteBancaireRepository = compteBancaireRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public void createOperation(OperationDto operationDto) {

    }

    @Override
    public List<Operation> findOperation() {
        return List.of();
    }

    @Override
    public Optional<Operation> findOne(String numCompte) {
        return Optional.empty();
    }

    @Override
    public CompteBancaire effectuerVersement(OperationDto operationDto) {
        Optional<CompteBancaire> compteBancaire = this.compteBancaireRepository.findByNumCompte(operationDto.getNumCompte());
        if (compteBancaire.isPresent()){
            CompteBancaire compte = compteBancaire.get();
            if(compte.getStatus().equals(AccountStatus.ACTIVATTED)){
                Operation operation = new Operation();
                operation.setDateOperation(new Date());
                operation.setAmount(operationDto.getAmount());
                operation.setCompte(compte);
                operation.setTypeOperation(TypeOperation.CREDIT);
                operation.setNumOperation(generateAccoundNumber());

                Operation operationSaved = this.operationRepository.save(operation);

                compte.setBalance(compte.getBalance() + operationDto.getAmount());
                CompteBancaire compteSaved = this.compteBancaireRepository.save(compte);
                return compteSaved;
            }
            else{
                throw new RuntimeException("C'est un compte suspendu; Opération impossible...");
            }

        }else{
            throw new RuntimeException("Ce compte n'existe pas");
        }
    }

    @Override
    public CompteBancaire effectuerRetrait(OperationDto operationDto) {
        Optional<CompteBancaire> compteBancaire = this.compteBancaireRepository.findByNumCompte(operationDto.getNumCompte());
        if (compteBancaire.isPresent()){
            CompteBancaire compte = compteBancaire.get();
            if(compte.getStatus().equals(AccountStatus.ACTIVATTED) && compte.getBalance() > operationDto.getAmount()){

                Operation operation = new Operation();
                operation.setDateOperation(new Date());
                operation.setAmount(operationDto.getAmount());
                operation.setCompte(compte);
                operation.setTypeOperation(TypeOperation.DEBIT);
                operation.setNumOperation(generateAccoundNumber());
                Operation operationSaved = this.operationRepository.save(operation);

                compte.setBalance(compte.getBalance() - operationDto.getAmount());
                CompteBancaire compteSaved = this.compteBancaireRepository.save(compte);
                return  compteSaved;
            }
            else{
                throw new RuntimeException("C'est un compte suspendu ou solde insuffisant ; Opération impossible...");
            }

        }else{
            throw new RuntimeException("Ce compte n'existe pas");
        }
    }

    @Override
    public Boolean effectuerVirement(OperationDto operationDto) {
        String numCompteSource = operationDto.getNumCompteSource();
        //DEBIT
        OperationDto dtoSource = new OperationDto();
        dtoSource.setAmount(operationDto.getAmount());
        dtoSource.setNumCompte(numCompteSource);
        CompteBancaire compteBancaireSource = this.effectuerRetrait(dtoSource);

        if(compteBancaireSource != null){
            //CREDIT
            String numCompteDestinataire = operationDto.getNumCompteDestinataire();
            OperationDto dtoDestinataire = new OperationDto();
            dtoDestinataire.setAmount(operationDto.getAmount());
            dtoDestinataire.setNumCompte(operationDto.getNumCompteDestinataire());
            CompteBancaire compteBancaireDestinataire = this.effectuerVersement(dtoDestinataire);
            if(compteBancaireDestinataire != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Operation> findAllOperationByClient(String numCompte) {
        List<Operation> zList = new ArrayList<>();
        for(Operation o : this.operationRepository.findAll()){
            if(o.getCompte().getNumCompte().equals(numCompte)){
                zList.add(o);
            }
        }
        return zList;
    }

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

}
