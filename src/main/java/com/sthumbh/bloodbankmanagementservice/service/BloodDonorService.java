package com.sthumbh.bloodbankmanagementservice.service;


import com.sthumbh.bloodbankmanagementservice.dto.DonorDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodDonor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BloodDonorService {

    DonorDto addDonor(DonorDto donorDto);
    void deleteAll();
    List<DonorDto> fetchAllDonors();
    ResponseEntity<DonorDto> fetchDonorById(Long Id);

}
