package com.gesBankMabo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {
    private long compteId;
    private double amount;
    private String numCompte;
    private String numCompteSource;
    private String numCompteDestinataire;

    public OperationDto() {
    }

    public OperationDto(long compteId, double amount, String numCompte, String numCompteSource, String numCompteDestinataire) {
        this.compteId = compteId;
        this.amount = amount;
        this.numCompte = numCompte;
        this.numCompteSource = numCompteSource;
        this.numCompteDestinataire = numCompteDestinataire;
    }

    public String getNumCompteSource() {
        return numCompteSource;
    }

    public void setNumCompteSource(String numCompteSource) {
        this.numCompteSource = numCompteSource;
    }

    public String getNumCompteDestinataire() {
        return numCompteDestinataire;
    }

    public void setNumCompteDestinataire(String numCompteDestinataire) {
        this.numCompteDestinataire = numCompteDestinataire;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public long getCompteId() {
        return compteId;
    }

    public void setCompteId(long compteId) {
        this.compteId = compteId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
