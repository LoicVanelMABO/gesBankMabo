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

    public OperationDto() {
    }

    public OperationDto(long compteId, double amount) {
        this.compteId = compteId;
        this.amount = amount;
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
