package com.sthumbh.bloodbankmanagementservice.mapper;

import com.sthumbh.bloodbankmanagementservice.dto.BloodRequestDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodStockDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DonorMapper {

    public final Function<BloodStockDetails, BloodRequestDto> MAP_TO_DONOR_DTO = (bloodStockDetails) ->{

        BloodRequestDto bloodRequestDto = new BloodRequestDto();
        //bloodRequestDto.setId(bloodStockDetails.getId());

        bloodRequestDto.setBloodType(bloodStockDetails.getBloodType());
        bloodRequestDto.setDonateTo(bloodStockDetails.getDonateTo());
        bloodRequestDto.setReceiveFrom(bloodStockDetails.getReceiveFrom());
        return bloodRequestDto;
    };

    public static  void MAP_TO_BLOOD_DONOR(BloodRequestDto bloodRequestDto, BloodStockDetails bloodStockDetails){


        bloodStockDetails.setBloodType(bloodRequestDto.getBloodType());

    }

}
