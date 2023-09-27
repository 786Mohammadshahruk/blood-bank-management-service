package com.sthumbh.bloodbankmanagementservice.controller;

import com.sthumbh.bloodbankmanagementservice.dto.DonorDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodDonor;
import com.sthumbh.bloodbankmanagementservice.service.BloodDonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BloodDonorController {

    @Autowired
    private BloodDonorService bloodDonorService;


    @PostMapping("/create")
    public ResponseEntity<DonorDto> addDonor(@RequestBody DonorDto donorDto){
        return new ResponseEntity<>(bloodDonorService.addDonor(donorDto), HttpStatus.CREATED);
    }


    @GetMapping("/fetch/all")
    public List<DonorDto> fetchAllDonors(){
        return bloodDonorService.fetchAllDonors();
    }

    @GetMapping("/fetch/by/{Id}")
    public ResponseEntity<DonorDto> fetchUserById(@PathVariable("Id") Long Id,
                                                  @RequestParam(name = "donateBloodTo",required = false)Boolean donateBloodTo,
                                                  @RequestParam(name = "receiveBloodFrom",required = false)Boolean receiveBloodFrom)
    {
        return bloodDonorService.fetchDonorById(Id);
    }

//    @DeleteMapping("/delete/all")
//    public String deleteAllDonors(){
//        bloodDonorService.deleteAll();
//        return "all donors have been deleted.";
//    }

}
