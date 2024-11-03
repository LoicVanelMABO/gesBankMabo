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
@DiscriminatorValue("1")
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitialize"})
public class CompteCourant extends CompteBancaire implements Serializable {
    private double decouvert;

    /*public CompteCourant() {
    }

    public CompteCourant(long id, double balance, String numCompte, String devis, AccountStatus status, Date createdAt, Client client, Collection<Operation> operations, double decouvert) {
        super(id, balance, numCompte, devis, status, createdAt, client, operations);
        this.decouvert = decouvert;
    }*/

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
