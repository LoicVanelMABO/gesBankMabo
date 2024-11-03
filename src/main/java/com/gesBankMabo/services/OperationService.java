package com.gesBankMabo.services;

import com.gesBankMabo.dto.OperationDto;
import com.gesBankMabo.entities.CompteBancaire;
import com.gesBankMabo.entities.Operation;

import java.util.List;
import java.util.Optional;

public interface OperationService {
    void createOperation(OperationDto operationDto);

    List<Operation> findOperation();

    Optional<Operation> findOne(String numCompte);

    CompteBancaire effectuerVersement(OperationDto operationDto);
    CompteBancaire effectuerRetrait(OperationDto operationDto);
    Boolean effectuerVirement(OperationDto operationDto);

    List<Operation> findAllOperationByClient(String numCompte);
}
