package com.sthumbh.bloodbankmanagementservice.dto;

import lombok.Data;

@Data
public class BloodRequestDto {
    private String bloodType;
    private String[] donateTo;
    private String[] receiveFrom;

}
