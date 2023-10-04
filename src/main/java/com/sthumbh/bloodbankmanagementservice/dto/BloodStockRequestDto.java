package com.sthumbh.bloodbankmanagementservice.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BloodStockRequestDto {
    private String bloodType;
    private String organizationType;
    private String category;
    private String stateCode;
    private String districtCode;
}
