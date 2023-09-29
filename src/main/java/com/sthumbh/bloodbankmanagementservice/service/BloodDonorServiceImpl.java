package com.sthumbh.bloodbankmanagementservice.service;

import com.sthumbh.bloodbankmanagementservice.dto.BloodRequestDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockDetails;
import com.sthumbh.bloodbankmanagementservice.exception.UnknownBloodException;
import com.sthumbh.bloodbankmanagementservice.mapper.DonorMapper;
import com.sthumbh.bloodbankmanagementservice.repository.BloodDonorRepository;
import com.sthumbh.bloodbankmanagementservice.utils.BloodTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BloodDonorServiceImpl implements BloodDonorService {

    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    @Autowired
    private DonorMapper donorMapper;

    @Override
    public  List<BloodStockDetails> addBloodDetails(List<BloodRequestDto> bloodRequestDtoList) throws UnknownBloodException {
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


}
