package com.gesBankMabo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteDto {
    private String devis;
    private double balance;
    private double tauxInteret;
    private double decouvert;
    private long clientId;

    public CompteDto() {
    }

    public CompteDto(String devis, double balance, double tauxInteret, double decouvert, long clientId) {
        this.devis = devis;
        this.balance = balance;
        this.tauxInteret = tauxInteret;
        this.decouvert = decouvert;
        this.clientId = clientId;
    }

    public String getDevis() {
        return devis;
    }

    public void setDevis(String devis) {
        this.devis = devis;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
