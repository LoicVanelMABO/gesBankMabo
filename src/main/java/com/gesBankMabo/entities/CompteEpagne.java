package com.gesBankMabo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gesBankMabo.enums.AccountStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("2")
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitialize"})
public class CompteEpagne extends CompteBancaire implements Serializable {
    private double TauxInteret;

   /* public CompteEpagne(long id, double balance, String numCompte, String devis, AccountStatus status, Date createdAt, Client client, Collection<Operation> operations) {
        super(id, balance, numCompte, devis, status, createdAt, client, operations);
    }

    public CompteEpagne() {
    }*/

    public double getTauxInteret() {
        return TauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        TauxInteret = tauxInteret;
    }
}
