package com.sthumbh.bloodbankmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorDto {

    private Long Id;
    private String donorName;
    private String bloodGroup;
    private List<String> donateTo;
    private List<String> receiveFrom;

}
