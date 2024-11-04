package com.gesBankMabo.Controller;

import com.gesBankMabo.dto.CompteDto;
import com.gesBankMabo.dto.OperationDto;
import com.gesBankMabo.entities.Operation;
import com.gesBankMabo.services.OperationService;
import com.gesBankMabo.services.OperationServiceImplement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationServiceImplement operationService;

    public OperationController(OperationServiceImplement operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/versement")
    void effectuerVersement(@RequestBody OperationDto operationDto){
        this.operationService.effectuerVersement(operationDto);
    }

    @PostMapping("/retrait")
    void effectuerRetrait(@RequestBody OperationDto operationDto){
        this.operationService.effectuerRetrait(operationDto);
    }

    @PostMapping("/virement")
    void effectuerVirement(@RequestBody OperationDto operationDto){
        this.operationService.effectuerVirement(operationDto);
    }

    @GetMapping("/client/{numCompte}")
    List<Operation> findAllOperationByClient(@PathVariable("numCompte") String numCompte, @RequestBody OperationDto operationDto){
        return this.operationService.findAllOperationByClient(numCompte);
    }
}
