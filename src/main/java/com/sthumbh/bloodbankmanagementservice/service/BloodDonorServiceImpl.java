package com.sthumbh.bloodbankmanagementservice.service;

import com.sthumbh.bloodbankmanagementservice.dto.BloodRequestDto;
import com.sthumbh.bloodbankmanagementservice.dto.BloodStockRequestDto;
import com.sthumbh.bloodbankmanagementservice.dto.BloodStockResponseDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockDetails;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockRequest;
import com.sthumbh.bloodbankmanagementservice.exception.UnknownBloodException;
import com.sthumbh.bloodbankmanagementservice.mapper.DonorMapper;
import com.sthumbh.bloodbankmanagementservice.repository.BloodDonorRepository;
import com.sthumbh.bloodbankmanagementservice.repository.BloodStockRepository;
import com.sthumbh.bloodbankmanagementservice.utils.BloodTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BloodDonorServiceImpl implements BloodDonorService {

    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    @Autowired
    private BloodStockRepository bloodStockRepository;

    @Autowired
    private DonorMapper donorMapper;

    @Override
    public List<BloodStockDetails> addBloodDetails(List<BloodRequestDto> bloodRequestDtoList) throws UnknownBloodException {
        List<BloodStockDetails> saveDataList = new ArrayList<>();
        for (BloodRequestDto bloodRequestDto : bloodRequestDtoList) {
            if (!BloodTypeUtils.getBloodsType().contains(bloodRequestDto.getBloodType())) {
                throw new UnknownBloodException("Blood Type Does Not Exist");
            }
            validateDonateBloodTo(bloodRequestDto.getDonateTo());
            validateReceivedBloodFrom(bloodRequestDto);

            BloodStockDetails bloodStockDetails = buildBloodStockDetails(bloodRequestDto);
            BloodStockDetails savedBloodStockDetails = bloodDonorRepository.save(bloodStockDetails);
            saveDataList.add(savedBloodStockDetails);
        }
        return saveDataList;
    }

    private void validateReceivedBloodFrom(BloodRequestDto bloodRequestDto) throws UnknownBloodException {
        List<String> bloodTypes = BloodTypeUtils.getBloodsType();
        for (String bloodType : bloodRequestDto.getReceiveFrom()) {
            if (bloodType.equalsIgnoreCase("EveryOne")) {
                continue;
            }
            if (!bloodTypes.contains(bloodType)) {
                throw new UnknownBloodException("Blood Type Does Not Exist");
            }
        }
    }

    private void validateDonateBloodTo(String[] bloodRequestDto) throws UnknownBloodException {
        List<String> bloodTypes = BloodTypeUtils.getBloodsType();
        for (String bloodType : bloodRequestDto) {
            if (!bloodTypes.contains(bloodType)) {
                throw new UnknownBloodException("Blood Type Does Not Exist");
            }
        }
    }

    private BloodStockDetails buildBloodStockDetails(BloodRequestDto bloodRequestDto) {
        return BloodStockDetails.builder()
                .bloodType(bloodRequestDto.getBloodType())
                .receiveFrom(bloodRequestDto.getReceiveFrom())
                .donateTo(bloodRequestDto.getDonateTo())
                .build();
    }


    @Override
    public void deleteAll() {
        bloodDonorRepository.deleteAll();
    }

    @Override
    public List<BloodRequestDto> fetchAllDonors() {
        return bloodDonorRepository.findAll().stream().map(donorMapper.MAP_TO_DONOR_DTO).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<BloodRequestDto> fetchDonorById(Long Id) {
        Optional<BloodStockDetails> bloodDonor = bloodDonorRepository.findById(Id);
        if (bloodDonor.isPresent()) {
            return new ResponseEntity<>(donorMapper.MAP_TO_DONOR_DTO.apply(bloodDonor.get()), HttpStatus.OK);
        } else throw new RuntimeException("User not found with this id : " + Id);

    }

    @Override
    public String addBloodStockDetails(List<BloodStockRequestDto> bloodStockRequest) {
        List<BloodStockRequest> bloodStockRequestList = getBloodStockRequestData(bloodStockRequest);
        bloodStockRequestList.stream().forEach(i -> bloodStockRepository.save(i));
        return "Successfully Added";
    }

    @Override
    public BloodStockResponseDto getBloodStokes(String stateCode, String districtCode, String bloodType) {

        return null;
    }

    private List<BloodStockRequest> getBloodStockRequestData(List<BloodStockRequestDto> bloodStockRequest) {
        List<BloodStockRequest> list = new ArrayList<>();
        bloodStockRequest.stream().forEach(i -> {

            BloodStockRequest bloodStockRequest1 = new BloodStockRequest();
            bloodStockRequest1.setBloodType(i.getBloodType());
            bloodStockRequest1.setCategory(i.getCategory());
            bloodStockRequest1.setLastUpdated(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            bloodStockRequest1.setOrganizationType(i.getOrganizationType());
            bloodStockRequest1.setDistrictCode(i.getDistrictCode());
            bloodStockRequest1.setStateCode(i.getStateCode());
            list.add(bloodStockRequest1);
        });
        return list;
    }


}
