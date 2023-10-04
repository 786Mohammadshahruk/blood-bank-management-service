package com.sthumbh.bloodbankmanagementservice.service;


import com.sthumbh.bloodbankmanagementservice.dto.BloodRequestDto;
import com.sthumbh.bloodbankmanagementservice.dto.BloodStockRequestDto;
import com.sthumbh.bloodbankmanagementservice.dto.BloodStockResponseDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockDetails;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockRequest;
import com.sthumbh.bloodbankmanagementservice.exception.UnknownBloodException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BloodDonorService {

    List<BloodStockDetails> addBloodDetails(List<BloodRequestDto> bloodRequestDto) throws UnknownBloodException;
    void deleteAll();
    List<BloodRequestDto> fetchAllDonors();
    ResponseEntity<BloodRequestDto> fetchDonorById(Long Id);

    String addBloodStockDetails(List<BloodStockRequestDto> bloodStockRequest);

    BloodStockResponseDto getBloodStokes(String stateCode, String districtCode, String bloodType);
}
