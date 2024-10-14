package com.gesBankMabo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @JsonBackReference
    @OneToMany(mappedBy = "compte",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Operation> operations = new ArrayList<>();
}
