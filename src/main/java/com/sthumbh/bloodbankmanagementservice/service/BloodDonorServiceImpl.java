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

    private static final Set<String> blood_groups = Set.of(
            "A+","B+","O+","AB+","A-","B-","O-","AB-"
    );

    @Override
    public DonorDto addDonor(DonorDto donorDto) {

        BloodDonor bloodDonor = new BloodDonor();
        DonorMapper.MAP_TO_BLOOD_DONOR(donorDto,bloodDonor);

        if(blood_groups.contains(bloodDonor.getBloodGroup())){
            if (bloodDonor.getBloodGroup().equals(BloodEnum.A_POSITIVE.name())){
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add(BloodEnum.A_POSITIVE.name());
                canDonateTo.add("AB+");

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("A+");
                canReceiveFrom.add("A-");
                canReceiveFrom.add("O-");
                canReceiveFrom.add("O+");

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);
            } else if (bloodDonor.getBloodGroup().equals("B+")) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add("B+");
                canDonateTo.add("AB+");

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("B+");
                canReceiveFrom.add("B-");
                canReceiveFrom.add("O-");
                canReceiveFrom.add("O+");

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodDonor.getBloodGroup().equals("O+")) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add("O+");
                canDonateTo.add("AB+");
                canDonateTo.add("A+");
                canDonateTo.add("B+");

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("O-");
                canReceiveFrom.add("O+");

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodDonor.getBloodGroup().equals("AB+")) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add("AB+");


                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("Everyone");


                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodDonor.getBloodGroup().equals("A-")) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add("A-");
                canDonateTo.add("AB+");
                canDonateTo.add("A+");
                canDonateTo.add("AB-");

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("A-");
                canReceiveFrom.add("O-");

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodDonor.getBloodGroup().equals("O-")) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add("O-");


                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("O-");


                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodDonor.getBloodGroup().equals("B-")) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add("B-");
                canDonateTo.add("AB+");
                canDonateTo.add("B+");
                canDonateTo.add("AB-");

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("B-");
                canReceiveFrom.add("O-");

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            else if (bloodDonor.getBloodGroup().equals("AB-")) {
                List<String> canDonateTo = new ArrayList<>();
                canDonateTo.add("AB+");
                canDonateTo.add("AB-");

                List<String> canReceiveFrom = new ArrayList<>();
                canReceiveFrom.add("A-");
                canReceiveFrom.add("O-");
                canReceiveFrom.add("AB-");
                canReceiveFrom.add("B-");

                bloodDonor.setDonateTo(canDonateTo);
                bloodDonor.setReceiveFrom(canReceiveFrom);

            }
            return donorMapper.MAP_TO_DONOR_DTO.apply(bloodDonorRepository.save(bloodDonor));

        }
        else throw new RuntimeException("This is not a valid Blood Group");

    }

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
