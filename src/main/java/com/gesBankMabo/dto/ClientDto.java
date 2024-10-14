package com.gesBankMabo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String nom;
    private String prenom;
    private Date birthday;
    private String email;
    private String addresse;
    private String tel;
}
