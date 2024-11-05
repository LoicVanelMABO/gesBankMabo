package com.gesBankMabo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gesBankMabo.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class CompteBancaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private  double balance;

    @Column(nullable = false)
    private String numCompte;

    @Column(nullable = false)
    private String devis = "€";

    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false)
    private Date createdAt = new Date();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @JsonManagedReference
    @OneToMany(mappedBy = "compte",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Operation> operations = new ArrayList<>();

    public CompteBancaire() {
    }

    public CompteBancaire(long id, double balance, String numCompte, String devis, AccountStatus status, Date createdAt, Client client, Collection<Operation> operations) {
        this.id = id;
        this.balance = balance;
        this.numCompte = numCompte;
        this.devis = devis;
        this.status = status;
        this.createdAt = createdAt;
        this.client = client;
        this.operations = operations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public String getDevis() {
        return devis;
    }

    public void setDevis(String devis) {
        this.devis = devis;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Collection<Operation> operations) {
        this.operations = operations;
    }
}
