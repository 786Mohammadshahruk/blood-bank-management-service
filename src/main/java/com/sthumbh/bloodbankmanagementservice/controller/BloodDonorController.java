package com.sthumbh.bloodbankmanagementservice.controller;

import com.sthumbh.bloodbankmanagementservice.dto.DonorDto;
import com.sthumbh.bloodbankmanagementservice.model.response.MetaData;
import com.sthumbh.bloodbankmanagementservice.model.response.ResourceData;
import com.sthumbh.bloodbankmanagementservice.model.response.ResponseModel;
import com.sthumbh.bloodbankmanagementservice.service.BloodDonorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class BloodDonorController {

    @Autowired
    private BloodDonorService bloodDonorService;


    @PostMapping("/create")
    public ResponseEntity<ResponseModel> addDonor(@RequestBody DonorDto donorDto){
        log.info("inside the addDonor post api.");
        MetaData metaData = MetaData.builder()
                    .status("SUCCESS")
                    .code("200 OK")
                    .message("SUCCESS")
                    .version("v1")
                    .build();
        ResourceData<Object> resourceData = new ResourceData<>();
        resourceData.setData(bloodDonorService.addDonor(donorDto));
        return new ResponseEntity<>(getResponseData(metaData,resourceData),HttpStatus.OK);


//        return new ResponseEntity<>(bloodDonorService.addDonor(donorDto), HttpStatus.CREATED);
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

    @DeleteMapping("/delete/all")
    public String deleteAllDonors(){
        bloodDonorService.deleteAll();
        return "all donors have been deleted.";
    }


    private ResponseModel getResponseData(MetaData metaData, ResourceData resourceData) {
        return ResponseModel.builder()
                .metaData(metaData)
                .resourceData(resourceData)
                .build();
    }

}
