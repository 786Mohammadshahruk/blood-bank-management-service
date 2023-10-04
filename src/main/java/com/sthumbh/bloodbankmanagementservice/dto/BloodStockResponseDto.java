package com.sthumbh.bloodbankmanagementservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BloodStockResponseDto {

    private String bloodBankName;
    private String organizationType;
    private String category;
    private String bloodType;
    private LocalDateTime lastUpdated;
}
