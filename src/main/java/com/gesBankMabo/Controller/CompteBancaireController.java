package com.gesBankMabo.Controller;

import com.gesBankMabo.dto.CompteDto;
import com.gesBankMabo.entities.CompteBancaire;
import com.gesBankMabo.entities.CompteCourant;
import com.gesBankMabo.entities.CompteEpagne;
import com.gesBankMabo.services.CompteService;
import com.gesBankMabo.services.CompteServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "account")
public class CompteBancaireController {
    private final CompteServiceImplement compteService;

    public CompteBancaireController(CompteServiceImplement compteService) {
        this.compteService = compteService;
    }

    @PostMapping
    void createAccount(@RequestBody CompteDto compteDto){
        this.compteService.createCompte(compteDto);
    }

    @GetMapping("/type/{type}")
    List<?> accounts(@PathVariable("type") String type){
        if(type.equals("CC")){
            return this.compteService.findCompteCourant();
        }
        if(type.equals("CE")){
            return this.compteService.findCompteEpargne();
        }
        return null;
    }

    @GetMapping("/{numCompte}/{type}")
    ResponseEntity<?> findAccount(@PathVariable("numCompte") String numCompte, @PathVariable("type") String type){
        CompteBancaire compteBancaire = this.compteService.findOne(numCompte);
        if(type.equals("CC") && (compteBancaire instanceof CompteCourant)){
            return ResponseEntity.ok((CompteCourant)compteBancaire);
        }
        if(type.equals("CE") && (compteBancaire instanceof CompteEpagne)){
            return ResponseEntity.ok((CompteBancaire)compteBancaire);
        }
        return null;
    }

    @GetMapping("/active/{numCompte}")
    boolean activeCompte(@PathVariable("numCompte") String numCompte){
        return this.compteService.activerCompte(numCompte);
    }

    @GetMapping("/suspendre/{numCompte}")
    boolean suspendreCompte(@PathVariable("numCompte") String numCompte){
        return this.compteService.suspendreCompte(numCompte);
    }

}
