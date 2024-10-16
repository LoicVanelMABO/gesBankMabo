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
}
