package com.sthumbh.bloodbankmanagementservice.mapper;

import com.sthumbh.bloodbankmanagementservice.dto.DonorDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodDonor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DonorMapper {

    public final Function<BloodDonor,DonorDto> MAP_TO_DONOR_DTO = (bloodDonor) ->{

        DonorDto donorDto = new DonorDto();
        donorDto.setId(bloodDonor.getId());
        donorDto.setDonorName(bloodDonor.getName());
        donorDto.setBloodGroup(bloodDonor.getBloodGroup());
        donorDto.setDonateTo(bloodDonor.getDonateTo());
        donorDto.setReceiveFrom(bloodDonor.getReceiveFrom());
        return donorDto;
    };

    public static  void MAP_TO_BLOOD_DONOR(DonorDto donorDto, BloodDonor bloodDonor){

        bloodDonor.setName(donorDto.getDonorName());
        bloodDonor.setBloodGroup(donorDto.getBloodGroup());

    }

}
