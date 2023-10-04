package com.sthumbh.bloodbankmanagementservice.controller;

import com.sthumbh.bloodbankmanagementservice.dto.BloodRequestDto;
import com.sthumbh.bloodbankmanagementservice.dto.BloodStockRequestDto;
import com.sthumbh.bloodbankmanagementservice.dto.BloodStockResponseDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockDetails;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockRequest;
import com.sthumbh.bloodbankmanagementservice.exception.UnknownBloodException;
import com.sthumbh.bloodbankmanagementservice.model.response.MetaData;
import com.sthumbh.bloodbankmanagementservice.model.response.ResourceData;
import com.sthumbh.bloodbankmanagementservice.model.response.ResponseModel;
import com.sthumbh.bloodbankmanagementservice.model.response.StatusCodes;
import com.sthumbh.bloodbankmanagementservice.service.BloodDonorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/bbms")
public class BloodDonorController {

    @Autowired
    private BloodDonorService bloodDonorService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseModel> addBloodDetails(@RequestBody List<BloodRequestDto> bloodRequestDto) throws UnknownBloodException {
        log.info("inside the addDonor post api.");
        ResourceData<List<BloodStockDetails>> resourceData = new ResourceData<>();
        resourceData.setData(bloodDonorService.addBloodDetails(bloodRequestDto));
        return new ResponseEntity<>(getResponseData(getMetaData(), resourceData), HttpStatus.OK);
    }

    private MetaData getMetaData() {
        return MetaData.builder()
                .status(StatusCodes.SUCCESS_STATUS)
                .code(StatusCodes.SUCCESS_CODE)
                .message("SUCCESS")
                .version("v1")
                .build();
    }


    @PostMapping(path = "/add-bloods-stock")
    public ResponseEntity<ResponseModel> addBloods(@RequestBody List<BloodStockRequestDto> bloodStockRequest) throws UnknownBloodException {
        ResourceData<String> resourceData = new ResourceData<>();
        resourceData.setData(bloodDonorService.addBloodStockDetails(bloodStockRequest));
        return new ResponseEntity<>(getResponseData(getMetaData(), resourceData), HttpStatus.OK);
    }


    @GetMapping(name = "/getBloodStocks")
    public ResponseEntity<ResponseModel> getBloodStokes(@RequestParam(name = "stateCode") String stateCode,
                                                        @RequestParam(name = "districtCode") String districtCode,
                                                        @RequestParam(name = "bloodType") String bloodType
    ) {
        ResourceData<BloodStockResponseDto> resourceData = new ResourceData<>();
        resourceData.setData(bloodDonorService.getBloodStokes(stateCode, districtCode, bloodType));
        return new ResponseEntity<>(getResponseData(getMetaData(), resourceData), HttpStatus.OK);
    }


    @GetMapping("/fetch/all")
    public List<BloodRequestDto> fetchAllDonors() {
        return bloodDonorService.fetchAllDonors();
    }

    @GetMapping("/fetch/by/{Id}")
    public ResponseEntity<BloodRequestDto> fetchUserById(@PathVariable("Id") Long Id,
                                                         @RequestParam(name = "donateBloodTo", required = false) Boolean donateBloodTo,
                                                         @RequestParam(name = "receiveBloodFrom", required = false) Boolean receiveBloodFrom) {
        return bloodDonorService.fetchDonorById(Id);
    }

    @DeleteMapping("/delete/all")
    public String deleteAllDonors() {
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
