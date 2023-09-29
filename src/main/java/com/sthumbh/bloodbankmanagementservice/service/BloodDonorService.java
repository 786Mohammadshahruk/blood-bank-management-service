package com.sthumbh.bloodbankmanagementservice.service;


import com.sthumbh.bloodbankmanagementservice.dto.BloodRequestDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockDetails;
import com.sthumbh.bloodbankmanagementservice.exception.UnknownBloodException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BloodDonorService {

    List<BloodStockDetails> addBloodDetails(List<BloodRequestDto> bloodRequestDto) throws UnknownBloodException;
    void deleteAll();
    List<BloodRequestDto> fetchAllDonors();
    ResponseEntity<BloodRequestDto> fetchDonorById(Long Id);

}
