package com.sthumbh.bloodbankmanagementservice.service;

import com.sthumbh.bloodbankmanagementservice.Enum.BloodEnum;
import com.sthumbh.bloodbankmanagementservice.dto.DonorDto;
import com.sthumbh.bloodbankmanagementservice.entitiy.BloodDonor;
import com.sthumbh.bloodbankmanagementservice.mapper.DonorMapper;
import com.sthumbh.bloodbankmanagementservice.repository.BloodDonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BloodDonorServiceImpl implements BloodDonorService{

    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    @Autowired
    private DonorMapper donorMapper;

//    private static final Set<String> blood_groups = Set.of(
//            "A+","B+","O+","AB+","A-","B-","O-","AB-"
//    );

    @Override
    public DonorDto addDonor(DonorDto donorDto) {

        BloodDonor bloodDonor = new BloodDonor();
        DonorMapper.MAP_TO_BLOOD_DONOR(donorDto,bloodDonor);

        String bloodGroup = bloodDonor.getBloodGroup().toUpperCase();

//        if(bloodEnum.contains(bloodDonor.getBloodGroup())){
            if (bloodGroup.equals(BloodEnum.A_POSITIVE.name())){
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add(BloodEnum.A_POSITIVE.name());
                canDonateTo.add(BloodEnum.AB_POSITIVE.name());

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add(BloodEnum.A_POSITIVE.name());
                canReceiveFrom.add(BloodEnum.A_NEGITIVE.name());
                canReceiveFrom.add(BloodEnum.A_NEGITIVE.name());
                canReceiveFrom.add(BloodEnum.O_POSITIVE.name());

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);
            } else if (bloodGroup.equals(BloodEnum.B_POSITIVE.name())) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add(BloodEnum.B_POSITIVE.name());
                canDonateTo.add(BloodEnum.AB_POSITIVE.name());

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add(BloodEnum.B_POSITIVE.name());
                canReceiveFrom.add(BloodEnum.B_NEGITIVE.name());
                canReceiveFrom.add(BloodEnum.O_NEGITIVE.name());
                canReceiveFrom.add(BloodEnum.O_POSITIVE.name());

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodGroup.equals(BloodEnum.O_POSITIVE.name())) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add(BloodEnum.O_POSITIVE.name());
                canDonateTo.add(BloodEnum.AB_POSITIVE.name());
                canDonateTo.add(BloodEnum.A_POSITIVE.name());
                canDonateTo.add(BloodEnum.B_POSITIVE.name());

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add(BloodEnum.O_NEGITIVE.name());
                canReceiveFrom.add(BloodEnum.O_POSITIVE.name());

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodGroup.equals(BloodEnum.AB_POSITIVE.name())) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add(BloodEnum.AB_POSITIVE.name());


                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("Everyone");


                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodGroup.equals(BloodEnum.A_NEGITIVE.name())) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add(BloodEnum.A_NEGITIVE.name());
                canDonateTo.add(BloodEnum.AB_POSITIVE.name());
                canDonateTo.add(BloodEnum.A_POSITIVE.name());
                canDonateTo.add(BloodEnum.AB_NEGITIVE.name());

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add(BloodEnum.A_NEGITIVE.name());
                canReceiveFrom.add(BloodEnum.O_NEGITIVE.name());

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodGroup.equals("O-")) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add((BloodEnum.O_NEGITIVE.name()));


                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add((BloodEnum.O_NEGITIVE.name()));


                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodGroup.equals(BloodEnum.B_NEGITIVE.name())) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add(BloodEnum.B_NEGITIVE.name());
                canDonateTo.add(BloodEnum.AB_POSITIVE.name());
                canDonateTo.add(BloodEnum.B_POSITIVE.name());
                canDonateTo.add(BloodEnum.AB_NEGITIVE.name());

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add(BloodEnum.B_NEGITIVE.name());
                canReceiveFrom.add((BloodEnum.O_NEGITIVE.name()));

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodGroup.equals(BloodEnum.AB_NEGITIVE.name())) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add(BloodEnum.AB_POSITIVE.name());
                canDonateTo.add(BloodEnum.AB_NEGITIVE.name());

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add(BloodEnum.A_NEGITIVE.name());
                canReceiveFrom.add((BloodEnum.O_NEGITIVE.name()));
                canReceiveFrom.add(BloodEnum.AB_NEGITIVE.name());
                canReceiveFrom.add(BloodEnum.B_NEGITIVE.name());

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            return donorMapper.MAP_TO_DONOR_DTO.apply(bloodDonorRepository.save(bloodDonor));

        }
//        else throw new RuntimeException("This is not a valid Blood Group");
//
//    }

    @Override
    public void deleteAll() {
        bloodDonorRepository.deleteAll();
    }

    @Override
    public List<DonorDto> fetchAllDonors() {
        return bloodDonorRepository.findAll().stream().map(donorMapper.MAP_TO_DONOR_DTO).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<DonorDto> fetchDonorById(Long Id) {
        Optional<BloodDonor> bloodDonor = bloodDonorRepository.findById(Id);
        if (bloodDonor.isPresent()){
            return new ResponseEntity<>(donorMapper.MAP_TO_DONOR_DTO.apply(bloodDonor.get()), HttpStatus.OK);
        }
        else throw new RuntimeException("User not found with this id : "+Id);

    }


}
